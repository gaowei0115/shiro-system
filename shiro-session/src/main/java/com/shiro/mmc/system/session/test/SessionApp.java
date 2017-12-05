package com.shiro.mmc.system.session.test;

import org.apache.shiro.session.Session;
import org.junit.Test;

import java.io.Serializable;

/**
 * @packageName：com.shiro.mmc.system.session.test
 * @desrciption:
 * @author: gaowei
 * @date： 2017-12-05 15:17
 * @history: (version) author date desc
 */
public class SessionApp extends BaseAppTest{

    @Test
    public void test() {
        login("classpath:shiro.ini", "admin", "123456");

        Session session = getSubject().getSession();
        Serializable sessionId = session.getId();
        String host = session.getHost();

        System.out.println("sessionId:" + sessionId + ", host:" + host);

        // 会话过期时间
        long timeout = session.getTimeout();
        System.out.println("before timeout:" + timeout);

        session.setTimeout(10000*10);
        timeout = session.getTimeout();
        System.out.println("after timeout:" + timeout);

        System.out.println("session start time " + session.getStartTimestamp());

        try {
            Thread.sleep(10000L);
        } catch (Exception e) {

        }
        // 更新session会话时间
        session.touch();

        // 销毁会话session
        session.stop();
        System.out.println("session last time " + session.getStartTimestamp());
    }
}
