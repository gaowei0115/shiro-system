package com.shiro.mmc.system.spring.dao.impl;

import com.shiro.mmc.system.spring.dao.RoleDao;
import com.shiro.mmc.system.spring.entity.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

/**
 * @packageName：com.shiro.mmc.system.spring.dao.impl
 * @desrciption: 角色管理Dao实现类
 * @author: gaowei
 * @date： 2017-12-08 10:44
 * @history: (version) author date desc
 */
@Repository("roleDaoImpl")
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public RoleEntity saveRole(RoleEntity role) {
        final String sql = "insert into sys_roles(role, description, isEffect) values(?,?,?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement psst = connection.prepareStatement(sql, new String[]{"id"});
                psst.setString(1, role.getRole());
                psst.setString(2, role.getDescription());
                psst.setString(3, role.getIsEffect());
                return psst;
            }
        }, keyHolder);
        role.setId(keyHolder.getKey().longValue());
        return role;
    }

    @Override
    public int deleteRole(Long roleId) {
        //首先把和role关联的相关表数据删掉
        String sql = "delete from sys_users_roles where role_id=?";
        jdbcTemplate.update(sql, roleId);

        sql = "delete from sys_roles where id=?";
       return jdbcTemplate.update(sql, roleId);
    }

    @Override
    public void relationRoleToPermission(Long roleId, Set<Long> permissionIds) {
        if(permissionIds == null || permissionIds.size() == 0) {
            return;
        }
        String sql = "insert into sys_roles_permissions(role_id, permission_id) values(?,?)";
        for(Long permissionId : permissionIds) {
            if(!exists(roleId, permissionId)) {
                jdbcTemplate.update(sql, roleId, permissionId);
            }
        }
    }

    @Override
    public void unRelationRoleToPermission(Long roleId, Set<Long> permissionIds) {
        if(permissionIds == null || permissionIds.size() == 0) {
            return;
        }
        String sql = "delete from sys_roles_permissions where role_id=? and permission_id=?";
        for(Long permissionId : permissionIds) {
            if(exists(roleId, permissionId)) {
                jdbcTemplate.update(sql, roleId, permissionId);
            }
        }
    }

    /**
     * 验证
     * @param roleId
     * @param permissionId
     * @return
     */
    private boolean exists(Long roleId, Long permissionId) {
        String sql = "select count(1) from sys_roles_permissions where role_id=? and permission_id=?";
        return jdbcTemplate.queryForObject(sql, Integer.class, roleId, permissionId) != 0;
    }
}
