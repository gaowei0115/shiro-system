package com.mmc.shiro.system.intergration.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @packageName：com.mmc.shiro.system.intergration.web
 * @desrciption: roleServlet
 * @author: gaowei
 * @date： 2017-12-04 17:34
 * @history: (version) author date desc
 */
@WebServlet(name = "roleServlet", value = "/role")
public class RoleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/jsp/hasRole.jsp").forward(req, resp);
    }
}
