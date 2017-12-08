package com.shiro.mmc.system.spring.service.impl;

import com.shiro.mmc.system.spring.dao.PermissionDao;
import com.shiro.mmc.system.spring.entity.PermissionEntity;
import com.shiro.mmc.system.spring.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @packageName：com.shiro.mmc.system.spring.service.impl
 * @desrciption: 权限管理Service实现类
 * @author: gaowei
 * @date： 2017-12-08 11:19
 * @history: (version) author date desc
 */
@Service("permissionServiceImpl")
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public PermissionEntity savePermission(PermissionEntity permission) {
        return permissionDao.savePermission(permission);
    }

    @Override
    public int deletePermission(Long permissionId) {
        return permissionDao.deletePermission(permissionId);
    }
}
