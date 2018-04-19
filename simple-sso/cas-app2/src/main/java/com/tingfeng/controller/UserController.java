package com.tingfeng.controller;

import com.tingfeng.cas.config.CasConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * 用户登出
     *
     * @param request
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {

        // session失效
        request.getSession().invalidate();

        return "redirect:" + CasConfig.CAS_SERVER_LOGOUT_PATH;
    }

    /**
     * 用户登出，并重定向回来
     *
     * @param request
     * @return
     */
    @RequestMapping("/logout2")
    public String logout2(HttpServletRequest request) {

        // session失效
        request.getSession().invalidate();

        return "redirect:" + CasConfig.CAS_SERVER_LOGOUT_PATH + "?service="+CasConfig.APP_LOGOUT_PATH;
    }

    /**
     * 登出成功回调
     * @return
     */
    @ResponseBody
    @RequestMapping("/logout/success")
    public String logoutPage(){
        return "登出成功，跳转登出页面";
    }
}
