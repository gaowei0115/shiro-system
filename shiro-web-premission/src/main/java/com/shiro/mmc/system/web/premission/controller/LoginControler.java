package com.shiro.mmc.system.web.premission.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * @packageName：com.shiro.mmc.system.web.premission.controller
 * @desrciption: LoginController
 * @author: gaowei
 * @date： 2017/12/4 21:44
 * @history: (version) author date desc
 */
@Controller
@RequestMapping
public class LoginControler {

    private static final Logger log = LoggerFactory.getLogger(LoginControler.class);

    /**
     *
     * @return
     */
    @RequestMapping(value = "/toLogin", method = RequestMethod.GET)
    public String toLogin() {
        return "login";
    }

    /**
     * 用户登录操作
     * @return
     */
    @RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
    public String login(HttpServletRequest request, HttpServletResponse response) {

        // 返回提示消息
        String message = "用户登录验证成功";

        String loginCode = request.getParameter("loginCode");
        String password = request.getParameter("password");

        log.debug("用户{}执行登录...", loginCode);

        Subject subject = SecurityUtils.getSubject();

        // 获取会话Session
        Session session = subject.getSession();
        Serializable sessionId = session.getId();
        String host = session.getHost();
        log.debug("sessionId:{}, host:{}", sessionId, host);
        // 会话过期时间
        long sessionTimeout = session.getTimeout();
        log.debug("session过期时间：{}", sessionTimeout);

        // 设置会话过期
        long timeout = 1000 * 10;
        log.debug("设置会话过期时间 {}", timeout);
        session.setTimeout(timeout);


        // 验证是否已认证
        if (!subject.isAuthenticated()) {
            log.debug("用户登录执行认证...");
            UsernamePasswordToken upToken = new UsernamePasswordToken(loginCode, password);
            upToken.setRememberMe(true);
            try {
                subject.login(upToken);
            } catch (Exception e) {
                log.debug("用户登录异常", e);
                message = String.format("用户登录失败, cause:%s", e.getMessage());
            }
        }
        request.setAttribute("message", message);
        return "redirect:/toIndex";
    }

}
