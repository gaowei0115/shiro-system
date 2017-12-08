package com.shiro.mmc.system.spring.dao;

import com.shiro.mmc.system.spring.entity.UserEntity;

import java.util.Set;

/**
 * @packageName：com.shiro.mmc.system.spring.dao
 * @desrciption: 用户管理Dao
 * @author: gaowei
 * @date： 2017-12-08 10:52
 * @history: (version) author date desc
 */
public interface UserDao {

    /**
     * 保存用户
     * @param user
     * @return
     */
    UserEntity saveUser(UserEntity user);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    int updateUser(UserEntity user);

    /**
     * 删除用户
     * @param userId
     * @return
     */
    int deleteUser(Long userId);

    /**
     * 建立用户与角色关系
     * @param userId
     * @param roleIds
     */
    void relationUserToRole(Long userId, Set<Long> roleIds);

    /**
     * 解除用户与角色关系
     * @param userId
     * @param roleIds
     */
    void unRelationUserToRole(Long userId, Set<Long> roleIds);

    /**
     * 查询用户信息根据userId
     * @param userId
     * @return
     */
    UserEntity queryUserById(Long userId);

    /**
     * 查询用户信息根据userName
     * @param userName
     * @return
     */
    UserEntity queryUserByName(String userName);

    /**
     * 查询用户角色
     * @param userName
     * @return
     */
    Set<String> queryUserRoles(String userName);

    /**
     * 查询用户权限
     * @param userName
     * @return
     */
    Set<String> queryUserPermissions(String userName);
}
