package com.yichen.my.shop.web.admin.web.controller;

import com.yichen.my.shop.commons.constant.ConstantUtils;
import com.yichen.my.shop.commons.utils.CookieUtils;
import com.yichen.my.shop.domain.TbUser;
import com.yichen.my.shop.web.admin.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController{

    @Autowired
    TbUserService tbUserService;

    @RequestMapping(value = {"", "login"}, method = RequestMethod.GET)
    public String login() {

        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestParam(value = "email", required = true) String email,
                        @RequestParam(value = "password", required = true)String password,
                        @RequestParam(value = "isRemember", required = false) String isRemember,
                        HttpServletRequest request,
                        HttpServletResponse response,
                        Model model) {
        TbUser user = tbUserService.login(email, password);
        if( user == null) {
            model.addAttribute("message", "用户名或密码错误，请重试");
            return login();
        } else {
            if( "on".equals(isRemember) ) {
                CookieUtils.setCookie(request, response, "email", email);
                CookieUtils.setCookie(request, response, "password", password);
                CookieUtils.setCookie(request, response, "isRemember", isRemember);
            } else {
                CookieUtils.deleteCookie(request, response, "email");
                CookieUtils.deleteCookie(request, response, "password");
                CookieUtils.deleteCookie(request, response, "isRemember");
            }
            request.getSession().setAttribute(ConstantUtils.SESSION_USER, user);
            return "redirect:main";
        }
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
//        CookieUtils.deleteCookie(request, response, "email");
//        CookieUtils.deleteCookie(request, response, "password");
//        CookieUtils.deleteCookie(request, response, "isRemember");
        return login();
    }
}