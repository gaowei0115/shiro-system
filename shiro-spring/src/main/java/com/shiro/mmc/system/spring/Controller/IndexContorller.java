package com.shiro.mmc.system.spring.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @packageName：com.shiro.mmc.system.spring.Controller
 * @desrciption: index控制层
 * @author: gaowei
 * @date： 2017-12-07 17:05
 * @history: (version) author date desc
 */
@Controller
@RequestMapping
public class IndexContorller {

    /**
     * 跳转到主页面
     * @return
     */
    @RequestMapping(value = "/toIndex", method = {RequestMethod.GET})
    public String toIndex() {
        return "index";
    }
}
