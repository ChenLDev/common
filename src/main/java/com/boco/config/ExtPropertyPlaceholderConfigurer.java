package com.boco.config;

import com.boco.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

/**
 * 扩展spring配置文件类
 *
 * @author ChenLiang
 * @create 2019 03 06
 */
@Slf4j
public class ExtPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    private static Properties properties;

    private ConfigMonitor configMonitor;

    public void setConfigMonitor(ConfigMonitor configMonitor){
        this.configMonitor = configMonitor;
    }

    @Override
    protected Properties mergeProperties() throws IOException {
        properties = super.mergeProperties();
        if (Constant.TRUE_STR.equals(System.getProperty(Constant.LOAD_ZK))) {
            if (Constant.TRUE_STR.equals(System.getProperty(Constant.LOAD_FIRST))) {
                for (Map.Entry prop : properties.entrySet()) {
                    log.debug("初始化创建配置到zk");
                    configMonitor.createNode((String) prop.getKey(), (String) prop.getValue());
                    log.debug("初始化创建成功 key [{}],val [{}]", prop.getKey(), prop.getValue());
                }
            }
            properties.setProperty(Constant.LOAD_ZK, Constant.TRUE_STR);
            log.debug("加载zk配置开启，开始加载zk配置");
            configMonitor.getAllConfig();
        } else {
            properties.setProperty(Constant.LOAD_ZK, Constant.FALSE_STR);
            log.debug("加载zk配置开启，开始加载zk配置");
        }
        return properties;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        super.postProcessBeanFactory(beanFactory);
    }

    @Override
    protected String convertProperty(String propertyName, String propertyValue) {
        return super.convertProperty(propertyName, propertyValue);
    }

    /**
     * 获取相关应用配置内容
     *
     * @param key
     * @return
     */
    public String getConfigVal(String key) {
        if (properties == null) {
            return null;
        }
        return properties.getProperty(key);
    }

    public static Properties getProperties() {
        return properties;
    }

    public static String removeConfig(String key) {
        String oldVal = (String) properties.remove(key);
        log.debug("config value had del,the key is [{}],the  val is [{}]", key, oldVal);
        return oldVal;
    }

    /**
     * 更新缓存配置
     *
     * @param key
     * @param val
     */
    public static void updateConfig(String key, String val) {
        String oldVal = (String) properties.setProperty(key, val);
        log.debug("config had changed,the old val is [{}],the new val is [{}]", oldVal, val);
    }
}
