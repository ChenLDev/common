package com.boco.config;

import com.boco.SpringTestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Key;

import static org.junit.Assert.*;

@Slf4j
public class ConfigMonitorTest extends SpringTestCase {

    @Autowired
    private ConfigMonitor configMonitor;

    private static final String KEY = "druid.driverClassName";
    private static final String VAl = "oracle.jdbc.driver.OracleDriver";

    @Test
    public void createNode() {
        configMonitor.createNode(KEY,VAl);
        assertEquals(configMonitor.getNodeData(KEY),VAl);
    }
}