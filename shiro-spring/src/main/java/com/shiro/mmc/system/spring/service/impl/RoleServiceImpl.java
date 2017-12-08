package com.shiro.mmc.system.spring.service.impl;

import com.shiro.mmc.system.spring.dao.RoleDao;
import com.shiro.mmc.system.spring.entity.RoleEntity;
import com.shiro.mmc.system.spring.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @packageName：com.shiro.mmc.system.spring.service.impl
 * @desrciption: 角色管理Service实现类
 * @author: gaowei
 * @date： 2017-12-08 11:22
 * @history: (version) author date desc
 */
@Service("roleServiceImpl")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public RoleEntity saveRole(RoleEntity role) {
        return roleDao.saveRole(role);
    }

    @Override
    public int deleteRole(Long roleId) {
        return roleDao.deleteRole(roleId);
    }

    @Override
    public void relationRoleToPermission(Long roleId, Set<Long> permissionIds) {
        roleDao.relationRoleToPermission(roleId, permissionIds);
    }

    @Override
    public void unRelationRoleToPermission(Long roleId, Set<Long> permissionIds) {
        roleDao.unRelationRoleToPermission(roleId, permissionIds);
    }
}
