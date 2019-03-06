package com.boco.dao.mapper;

import com.boco.SpringTestCase;
import com.boco.dao.entity.Config;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * config持久化测试
 *
 * @author ChenLiang
 * @create 2019 02 11
 */
@Slf4j
public class ConfigMapperTest extends SpringTestCase {
    @Autowired
    private ConfigMapper configMapper;

    @Test
    public void testGetAllConfig() {
        List<Config> allConfig = configMapper.getAllConfig();
        for (Config config : allConfig) {
            log.debug("name is [{}], val is [{}].", config.getName(), config.getVal());
        }
    }

    @Test
    public void testGetAllConfigForPage(){
        PageHelper.startPage(1,5);
        List<Config> allConfig = configMapper.getAllConfig();
        for (Config config : allConfig) {
            log.debug("name is [{}], val is [{}].", config.getName(), config.getVal());
        }
    }
}