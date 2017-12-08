package com.shiro.mmc.system.spring.service;

import com.shiro.mmc.system.spring.entity.PermissionEntity;

/**
 * @packageName：com.shiro.mmc.system.spring.service
 * @desrciption: 权限管理Service
 * @author: gaowei
 * @date： 2017-12-08 11:17
 * @history: (version) author date desc
 */
public interface PermissionService {


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
