package com.shiro.mmc.system.spring.dao.impl;

import com.shiro.mmc.system.spring.dao.UserDao;
import com.shiro.mmc.system.spring.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @packageName：com.shiro.mmc.system.spring.dao.impl
 * @desrciption: 用户管理Dao实现类
 * @author: gaowei
 * @date： 2017-12-08 11:02
 * @history: (version) author date desc
 */
@Repository("userDaoImpl")
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserEntity saveUser(UserEntity user) {
        final String sql = "insert into sys_users(username, password, salt, locked) values(?, ?, ?, ?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement psst = connection.prepareStatement(sql, new String[]{"id"});
                psst.setString(1, user.getUsername());
                psst.setString(2, user.getPassword());
                psst.setString(3, user.getSalt());
                psst.setBoolean(4, user.getLocked());
                return psst;
            }
        }, keyHolder);
        user.setId(keyHolder.getKey().longValue());
        return user;
    }

    @Override
    public int updateUser(UserEntity user) {
        final String sql = "update sys_user set username=?, password=?, salt=?, locked=? where id=?";
        int result = jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getSalt(), user.getLocked(), user.getId());
        return result;
    }

    @Override
    public int deleteUser(Long userId) {
        String sql = "delete from sys_users where id=?";
        return jdbcTemplate.update(sql, userId);
    }

    @Override
    public void relationUserToRole(Long userId, Set<Long> roleIds) {
        if(roleIds == null || roleIds.size() == 0) {
            return;
        }
        String sql = "insert into sys_users_roles(user_id, role_id) values(?,?)";
        for(Long roleId : roleIds) {
            if(!exists(userId, roleId)) {
                jdbcTemplate.update(sql, userId, roleId);
            }
        }
    }

    @Override
    public void unRelationUserToRole(Long userId, Set<Long> roleIds) {
        if(roleIds == null || roleIds.size() == 0) {
            return;
        }
        String sql = "delete from sys_users_roles where user_id=? and role_id=?";
        for(Long roleId : roleIds) {
            if(exists(userId, roleId)) {
                jdbcTemplate.update(sql, userId, roleId);
            }
        }
    }

    /**
     *
     * @param userId
     * @param roleId
     * @return
     */
    private boolean exists(Long userId, Long roleId) {
        String sql = "select count(1) from sys_users_roles where user_id=? and role_id=?";
        return jdbcTemplate.queryForObject(sql, Integer.class, userId, roleId) != 0;
    }

    @Override
    public UserEntity queryUserById(Long userId) {
        String sql = "select id, username, password, salt, locked from sys_users where id=?";
        List<UserEntity> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(UserEntity.class), userId);
        if(userList.size() == 0) {
            return null;
        }
        return userList.get(0);
    }

    @Override
    public UserEntity queryUserByName(String userName) {
        String sql = "select id, username, password, salt, locked from sys_users where username=?";
        List<UserEntity> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(UserEntity.class), userName);
        if(userList.size() == 0) {
            return null;
        }
        return userList.get(0);
    }

    @Override
    public Set<String> queryUserRoles(String userName) {
        String sql = "select role from sys_users u, sys_roles r,sys_users_roles ur where u.username=? and u.id=ur.user_id and r.id=ur.role_id";
        return new HashSet(jdbcTemplate.queryForList(sql, String.class, userName));
    }

    @Override
    public Set<String> queryUserPermissions(String userName) {
        String sql = "select permission from sys_users u, sys_roles r, sys_permissions p, sys_users_roles ur, sys_roles_permissions rp where u.username=? and u.id=ur.user_id and r.id=ur.role_id and r.id=rp.role_id and p.id=rp.permission_id";
        return new HashSet(jdbcTemplate.queryForList(sql, String.class, userName));
    }
}
