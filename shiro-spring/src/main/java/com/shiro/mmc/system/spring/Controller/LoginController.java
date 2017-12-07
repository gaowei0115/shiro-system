package com.shiro.mmc.system.spring.Controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @packageName：com.shiro.mmc.system.spring.Controller
 * @desrciption: login控制层
 * @author: gaowei
 * @date： 2017-12-07 17:04
 * @history: (version) author date desc
 */
@Controller
@RequestMapping
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    /**
     * 跳转到登录页面
     * @return
     */
    @RequestMapping(value = "/toLogin", method = {RequestMethod.GET})
    public String toLogin(){
        return "login";
    }

    /**
     * 执行登录
     * @return
     */
    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public String login(@RequestParam String username, @RequestParam String password) {
        log.debug("用户登录 username: {}", username);

        Subject subject = SecurityUtils.getSubject();

        // 设置会话过期时间
        Session session = subject.getSession();
        log.debug("登录用户session sessionId:{}", session.getId());
        session.setTimeout(1000 * 100);
        if (!subject.isAuthenticated()) {
            log.debug("用户执行登录认证....");
            UsernamePasswordToken upToken = new UsernamePasswordToken(username, password);
            upToken.setRememberMe(true);
            try {
                subject.login(upToken);
            } catch (Exception e) {
                log.error(String.format("%s用户登录异常", username), e);
            }
        }
        return "redirect:/toIndex";
    }
}
