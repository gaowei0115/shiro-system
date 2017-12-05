package com.mmc.shiro.system.authorization;

import org.junit.Assert;
import org.junit.Test;

/**
 * @packageName：com.mmc.shiro.system.authorization
 * @desrciption: 基于角色权限测试
 * @author: gaowei
 * @date： 2017-12-05 14:59
 * @history: (version) author date desc
 */
public class TestRolePremission extends BaseTest{

    /**
     * 角色权限测试
     */
    @Test
    public void test() {
        login("classpath:shiro-permission.ini", "admin", "123456");
        Assert.assertTrue(getSubject().isPermitted("user:create"));
        Assert.assertTrue(getSubject().isPermittedAll("user:create", "user:update", "user:delete", "user:query"));

        Assert.assertTrue(getSubject().isPermitted("user:view"));
    }

    @Test
    public void test01() {
        login("classpath:shiro-permission.ini", "guest", "123");
        Assert.assertTrue(getSubject().isPermitted("user:query"));
    }
}
