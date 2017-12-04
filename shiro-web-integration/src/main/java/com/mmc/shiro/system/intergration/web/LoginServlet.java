package com.mmc.shiro.system.intergration.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @packageName：com.mmc.shiro.system.intergration.web
 * @desrciption: loginServlet
 * @author: gaowei
 * @date： 2017-12-04 16:16
 * @history: (version) author date desc
 */
@WebServlet(name = "loginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String error = null;
        String userName = req.getParameter("username");
        String password = req.getParameter("password");
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken upToken = new UsernamePasswordToken(userName, password);
        upToken.setRememberMe(true);
        try {
            logger.debug("执行登录username:{}, password:{}", userName, password);
            subject.login(upToken);
        } catch (UnknownAccountException e) {
            error = "用户名或者密码错误";
            logger.error(error, e);
        } catch (IncorrectCredentialsException e) {
            error = "用户名或者密码错误";
            logger.error(error, e);
        } catch (Exception e) {
            error = "其它错误信息";
            logger.error(error, e);
        }

        if(error != null) {//出错了，返回登录页面
            req.setAttribute("error", error);
            req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
        } else {//登录成功
            req.getRequestDispatcher("/WEB-INF/jsp/loginSuccess.jsp").forward(req, resp);
        }

    }

}
