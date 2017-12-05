package com.mmc.shiro.system.authorization;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;

/**
 * @packageName：com.mmc.shiro.system.authorization
 * @desrciption: 测试基类
 * @author: gaowei
 * @date： 2017-12-05 14:31
 * @history: (version) author date desc
 */
public class BaseTest {

    @After
    public void tearDown() {
        // 退出绑定
        ThreadContext.unbindSubject();
    }

    /**
     * 登录操作
     * @param config
     *          ini配置文件
     * @param userName
     *          登录用户名称
     * @param password
     *          登录用户密码
     */
    protected void login(String config, String userName, String password) {
        // 创建SecurityManager工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(config);
        // 从工厂中获取对象
        SecurityManager securityManager = factory.getInstance();
        // 将验证管理器绑定到SecurityUtils
        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken upToken = new UsernamePasswordToken(userName, password);
        subject.login(upToken);
    }

    /**
     * 获取验证主题对象
     * @return
     */
    protected Subject getSubject() {
        return SecurityUtils.getSubject();
    }
}
