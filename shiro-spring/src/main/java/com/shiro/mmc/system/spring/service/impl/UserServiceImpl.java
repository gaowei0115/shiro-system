package com.shiro.mmc.system.spring.service.impl;

import com.shiro.mmc.system.spring.common.utils.PasswordHelper;
import com.shiro.mmc.system.spring.dao.UserDao;
import com.shiro.mmc.system.spring.entity.UserEntity;
import com.shiro.mmc.system.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @packageName：com.shiro.mmc.system.spring.service.impl
 * @desrciption: 用户管理Service实现类
 * @author: gaowei
 * @date： 2017-12-08 11:25
 * @history: (version) author date desc
 */
@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    private PasswordHelper passwordHelper = new PasswordHelper();

    @Override
    public UserEntity saveUser(UserEntity user) {
        passwordHelper.encryptPassword(user);
        return userDao.saveUser(user);
    }

    @Override
    public int updateUser(UserEntity user) {
        passwordHelper.encryptPassword(user);
        return userDao.updateUser(user);
    }

    @Override
    public int deleteUser(Long userId) {
        return userDao.deleteUser(userId);
    }

    @Override
    public void relationUserToRole(Long userId, Set<Long> roleIds) {
        userDao.relationUserToRole(userId, roleIds);
    }

    @Override
    public void unRelationUserToRole(Long userId, Set<Long> roleIds) {
        userDao.unRelationUserToRole(userId, roleIds);
    }

    @Override
    public UserEntity queryUserById(Long userId) {
        return userDao.queryUserById(userId);
    }

    @Override
    public UserEntity queryUserByName(String userName) {
        return userDao.queryUserByName(userName);
    }

    @Override
    public Set<String> queryUserRoles(String userName) {
        return userDao.queryUserRoles(userName);
    }

    @Override
    public Set<String> queryUserPermissions(String userName) {
        return userDao.queryUserPermissions(userName);
    }
}
