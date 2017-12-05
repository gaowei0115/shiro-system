package com.shiro.mmc.system.session;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @packageName：com.shiro.mmc.system.session
 * @desrciption: 数据库操作工具类
 * @author: gaowei
 * @date： 2017-12-05 16:48
 * @history: (version) author date desc
 */
public class JDBCTemplateUtils {

    private static JdbcTemplate jdbcTemplate;

    public static JdbcTemplate jdbcTemplate() {
        if(jdbcTemplate == null) {
            jdbcTemplate = createJdbcTemplate();
        }
        return jdbcTemplate;
    }

    private static JdbcTemplate createJdbcTemplate() {

        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/shiro_db?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&failOverReadOnly=false&serverTimezone=GMT ");
        ds.setUsername("root");
        ds.setPassword("root");

        return new JdbcTemplate(ds);
    }

}
