package com.shiro.mmc.system.web.premission.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @packageName：com.shiro.mmc.system.web.premission.controller
 * @desrciption: 主页面Controller
 * @author: gaowei
 * @date： 2017-12-05 13:30
 * @history: (version) author date desc
 */
@Controller
@RequestMapping
public class IndexController {

    /**
     *
     * @return
     */
    @RequestMapping(value = "/toIndex", method = {RequestMethod.GET, RequestMethod.POST})
    public String toIndex() {
        return "index";
    }

    @RequestMapping(value = "/toUserPage", method = {RequestMethod.GET, RequestMethod.POST})
    public String toUserPage() {
        return "user";
    }

    @RequestMapping(value = "/toAdminPage", method = {RequestMethod.GET, RequestMethod.POST})
    public String toAdminPage() {
        return "admin";
    }

    @RequestMapping(value = "/toUnauthorizedPage", method = {RequestMethod.GET, RequestMethod.POST})
    public String toUnauthorizedPage() {
        return "unauthorized";
    }
}
