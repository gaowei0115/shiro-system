package com.shiro.mmc.system.spring.test;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @packageName：com.shiro.mmc.system.spring.test
 * @desrciption:
 * @author: gaowei
 * @date： 2017-12-08 13:43
 * @history: (version) author date desc
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class BaseTest {

    /**
     * logger
     */
    protected static final Logger log = LoggerFactory.getLogger(BaseTest.class);
}
