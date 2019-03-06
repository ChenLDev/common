package com.boco.config.impl;

import com.boco.config.IConfigHandler;
import com.boco.config.entity.Config;

import java.util.List;
import java.util.Map;

/**
 * 配置操作类适配工具
 *
 * @author ChenLiang
 * @create 2019 01 24
 */
public class ConfigHandlerAdaptor implements IConfigHandler {

    @Override
    public Config getConfig(String configName) {
        throw new RuntimeException("not support this method");
    }

    @Override
    public List<Config> getConfigList() {
        throw new RuntimeException("not support this method");
    }

    @Override
    public Map<String, Config> getConfigMap() {
        throw new RuntimeException("not support this method");
    }

    @Override
    public boolean setConfig(Config config) {
        throw new RuntimeException("not support this method");
    }

    @Override
    public Config setConfigAndReturn(Config newConfig) {
        throw new RuntimeException("not support this method");
    }

    /**
     * 更新缓存配置
     * @param key
     * @param val
     */
    public static void updateConfigMap(String key,String val){
        CONFIG_MAP.put(key, val);
    }

}
