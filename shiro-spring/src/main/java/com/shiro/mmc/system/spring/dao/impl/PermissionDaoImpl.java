package com.shiro.mmc.system.spring.dao.impl;

import com.shiro.mmc.system.spring.dao.PermissionDao;
import com.shiro.mmc.system.spring.entity.PermissionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @packageName：com.shiro.mmc.system.spring.dao.impl
 * @desrciption: 权限Dao实现类
 * @author: gaowei
 * @date： 2017-12-08 10:33
 * @history: (version) author date desc
 */
@Repository("permisiionDaoImpl")
public class PermissionDaoImpl implements PermissionDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public PermissionEntity savePermission(PermissionEntity permission) {
        final String sql = "insert into sys_permissions(permission, description, isEffect) values(?,?,?)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement psst = connection.prepareStatement(sql, new String[]{"id"});
                psst.setString(1, permission.getPermission());
                psst.setString(2, permission.getDescription());
                psst.setString(3, permission.getIsEffect());
                return psst;
            }
        }, keyHolder);
        permission.setId(keyHolder.getKey().longValue());
        return permission;
    }

    @Override
    public int deletePermission(Long permissionId) {
        //首先把与permission关联的相关表的数据删掉
        String sql = "delete from sys_roles_permissions where permission_id=?";
        jdbcTemplate.update(sql, permissionId);

        sql = "delete from sys_permissions where id=?";
        return jdbcTemplate.update(sql, permissionId);
    }
}
