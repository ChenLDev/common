package com.boco;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * spring测试父类
 *
 * @author ChenLiang
 * @create 2019 02 11
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-*.xml"})
public class SpringTestCase extends AbstractJUnit4SpringContextTests {

}