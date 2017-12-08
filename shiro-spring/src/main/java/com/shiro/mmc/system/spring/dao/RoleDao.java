package com.shiro.mmc.system.spring.dao;

import com.shiro.mmc.system.spring.entity.RoleEntity;

import java.util.Set;

/**
 * @packageName：com.shiro.mmc.system.spring.dao
 * @desrciption: 角色管理Dao
 * @author: gaowei
 * @date： 2017-12-08 10:39
 * @history: (version) author date desc
 */
public interface RoleDao {

    /**
     * 创建角色
     * @param role
     * @return
     */
    RoleEntity saveRole(RoleEntity role);

    /**
     * 删除角色
     * @param roleId
     * @return
     */
    int deleteRole(Long roleId);

    /**
     * 建立角色与权限关系
     * @param roleId
     * @param permissionIds
     */
    void relationRoleToPermission(Long roleId, Set<Long> permissionIds);

    /**
     * 接触角色与权限关系
     * @param roleId
     * @param permissionIds
     */
    void unRelationRoleToPermission(Long roleId, Set<Long> permissionIds);
}
