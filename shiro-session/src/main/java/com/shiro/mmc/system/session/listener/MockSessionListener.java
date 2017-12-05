package com.shiro.mmc.system.session.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @packageName：com.shiro.mmc.system.session.listener
 * @desrciption: 自定义Session监听器
 * @author: gaowei
 * @date： 2017-12-05 16:40
 * @history: (version) author date desc
 */
public class MockSessionListener implements SessionListener{

    private static final Logger log = LoggerFactory.getLogger(MockSessionListener.class);

    @Override
    public void onStart(Session session) {
        log.debug("会话创建：{}", session.getId() );
    }

    @Override
    public void onStop(Session session) {
        log.debug("会话暂停：{}", session.getId());
    }

    @Override
    public void onExpiration(Session session) {
        log.debug("会话过期：{}", session.getId());
    }
}
