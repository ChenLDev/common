package com.boco.dao.mapper;

import com.boco.dao.entity.Config;

import java.util.List;

/**
 * 配置类映射
 *
 * @author ChenLiang
 * @create 2019 02 11
 */
public interface ConfigMapper {
    /**
     * 获取所有配置类
     * @return
     */
    List<Config> getAllConfig();
}
