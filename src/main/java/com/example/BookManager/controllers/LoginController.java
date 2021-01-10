package com.example.BookManager.controllers;

import com.example.BookManager.biz.LoginBiz;
import com.example.BookManager.service.UserService;
import com.example.BookManager.model.User;

import com.example.BookManager.utils.CookieUtils;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @Autowired
    private LoginBiz loginBiz;

    @Autowired
    private UserService userService;

    @RequestMapping(path={"/users/register"},method = RequestMethod.GET)
    public String register(){
        return "login/register";
    }

    @RequestMapping(path={"/users/register/do"},method = {RequestMethod.POST})
    public String doRegister(
            Model model,
            HttpServletResponse response,
            @RequestParam("email")String email,
            @RequestParam("password")String password
    ){
        try{
            String ticket = loginBiz.login(email,password);
            CookieUtils.writeCookie("t",ticket,response);
            return "redirect:/index";
        } catch (Exception e){
            model.addAttribute("error",e.getMessage());
            return "404";
        }
    }

    @RequestMapping(path={"/users/logout/do"},method = {RequestMethod.GET})
    public String doLogOut
            (@CookieValue("t")String t){

        loginBiz.logout(t);
        return "redirect:/index";
    }

}
