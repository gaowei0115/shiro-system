package com.mmc.shiro.system.authorization;

import org.apache.shiro.authz.AuthorizationException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @packageName：com.mmc.shiro.system.authorization
 * @desrciption:
 * @author: gaowei
 * @date： 2017-12-05 14:28
 * @history: (version) author date desc
 */
public class TestHasRole extends BaseTest{

    @Test
    public void test() {
        login("classpath:shiro-role.ini", "admin", "123456");
        // 验证账户是否有system角色
        Assert.assertTrue(getSubject().hasRole("system"));
        // 验证用户不存在role1角色
//        Assert.assertTrue(getSubject().hasRole("role1"));

        /**
         * 验证同时拥有多个权限
         */
        Assert.assertTrue(getSubject().hasAllRoles(Arrays.asList("system", "user")));
//        Assert.assertTrue(getSubject().hasAllRoles(Arrays.asList("system", "user", "role")));

        /**
         * 验证是否拥有权限，返回对应的结果信息
         */
        boolean[] results = getSubject().hasRoles(Arrays.asList("system", "user", "role0"));
        Assert.assertTrue(results[0]);
        Assert.assertTrue(results[1]);
        Assert.assertTrue(results[2]);
    }

    @Test(expected = AuthorizationException.class)
    public void test01() {
        login("classpath:shiro-role.ini", "admin", "123456");

//        getSubject().checkRole("user");
        /**
         * 存在权限时，抛出异常AuthorizationException
         * 不存在，则不抛出异常
         */
        getSubject().checkRoles("user1");
    }
}
