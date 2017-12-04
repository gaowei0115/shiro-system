package com.mmc.shiro.system.intergration.web;

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(req, resp);
    }

}
