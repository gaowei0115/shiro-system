package com.mmc.shiro.system.intergration.web;

import org.apache.shiro.SecurityUtils;
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
 * @desrciption: 登出servlet
 * @author: gaowei
 * @date： 2017-12-04 17:07
 * @history: (version) author date desc
 */
@WebServlet(name = "logoutServlet", value = "/logout")
public class LogoutServlet extends HttpServlet{

    private static final Logger logger = LoggerFactory.getLogger(LogoutServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("执行账户登出");
        SecurityUtils.getSubject().logout();
        req.getRequestDispatcher("WEB-INF/jsp/logoutSuccess.jsp").forward(req, resp);
    }
}
