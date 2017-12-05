package com.shiro.mmc.system.session.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Test;

/**
 * @packageName：com.shiro.mmc.system.session.test
 * @desrciption:
 * @author: gaowei
 * @date： 2017-12-05 15:18
 * @history: (version) author date desc
 */
public class BaseAppTest {

    @After
    public void tearDown() {
        ThreadContext.unbindSubject();
    }

    /**
     * 登录测试
     * @param config
     * @param userName
     * @param password
     */
    protected void login(String config, String userName, String password) {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(config);
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();
        try {
            UsernamePasswordToken upToken = new UsernamePasswordToken(userName, password);
            subject.login(upToken);
        } catch (Exception e) {
            System.out.println("登录异常");
            System.out.println(e.getMessage());
        }
    }

    public Subject getSubject() {
        return SecurityUtils.getSubject();
    }
}
