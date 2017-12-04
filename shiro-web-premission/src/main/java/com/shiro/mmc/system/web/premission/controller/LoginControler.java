package com.shiro.mmc.system.web.premission.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

}
