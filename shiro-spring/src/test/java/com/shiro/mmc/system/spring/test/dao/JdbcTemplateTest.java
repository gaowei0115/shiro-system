package com.shiro.mmc.system.spring.test.dao;

import com.shiro.mmc.system.spring.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @packageName：com.shiro.mmc.system.spring.test.dao
 * @desrciption:
 * @author: gaowei
 * @date： 2017-12-08 13:45
 * @history: (version) author date desc
 */
public class JdbcTemplateTest extends BaseTest {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void test() {
        log.debug("获取jdbcTemplate操作对象 {}", jdbcTemplate);
    }
}
