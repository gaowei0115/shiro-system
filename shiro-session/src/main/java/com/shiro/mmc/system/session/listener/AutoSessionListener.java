package com.shiro.mmc.system.session.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @packageName：com.shiro.mmc.system.session.listener
 * @desrciption:
 * @author: gaowei
 * @date： 2017-12-05 16:42
 * @history: (version) author date desc
 */
public class AutoSessionListener extends SessionListenerAdapter{

    private static final Logger log = LoggerFactory.getLogger(AutoSessionListener.class);

    @Override
    public void onStart(Session session) {
        log.debug("会话创建：{}", session.getId());
    }

}
