package com.shiro.mmc.system.spring.dao;

import com.shiro.mmc.system.spring.entity.PermissionEntity;

/**
 * @packageName：com.shiro.mmc.system.spring.dao
 * @desrciption: 权限管理Dao
 * @author: gaowei
 * @date： 2017-12-08 10:26
 * @history: (version) author date desc
 */
public interface PermissionDao {

    /**
     * 保存权限
     * @param permission
     * @return
     */
    PermissionEntity savePermission(PermissionEntity permission);

    /**
     * 删除权限
     * @param permissionId
     * @return
     */
    int deletePermission(Long permissionId);

}
