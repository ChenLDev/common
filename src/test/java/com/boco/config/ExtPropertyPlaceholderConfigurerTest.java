package com.boco.config;

import com.boco.SpringTestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.Properties;

import static org.junit.Assert.*;

@Slf4j
public class ExtPropertyPlaceholderConfigurerTest extends SpringTestCase {

    @Autowired
    private ExtPropertyPlaceholderConfigurer configurer;

    @Test
    public void testGetConfigVal() {
        assertNotNull(configurer);
    }

    @Test
    public void testGetAllConfigVal() {
        Properties properties = configurer.getProperties();
        for (Map.Entry prop : properties.entrySet()) {
            log.info("key is [{}],val is [{}]", prop.getKey(), prop.getValue());
        }
    }
}