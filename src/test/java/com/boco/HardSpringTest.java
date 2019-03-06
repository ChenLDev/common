package com.boco;

import com.boco.dao.mapper.ConfigMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author ChenLiang
 * @create 2019 02 12
 *
 * 通过ClassPathXmlApplicationContext加载配置文件，硬编码获取bean来测试
 */
public class HardSpringTest {
    private static ApplicationContext actx = new ClassPathXmlApplicationContext("classpath:spring-db.xml");

    @Test
    public void testMapper() {
        ConfigMapper dsf = (ConfigMapper) actx.getBean("configMapper");
        dsf.getAllConfig();
    }
}
