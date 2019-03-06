package com.boco.config;

import com.boco.config.entity.Config;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 配置处理接口
 *
 * @author ChenLiang
 * @create 2019 01 24
 */
public interface IConfigHandler {

    Map<String, String> CONFIG_MAP = new ConcurrentHashMap<>();


    /**
     * 根据配置名称获取单条配置
     * @param configName
     * @return
     */
    Config getConfig(String configName);

    List<Config> getConfigList();

    Map<String,Config> getConfigMap();

    boolean setConfig(Config config);

    Config setConfigAndReturn(Config newConfig);
}
