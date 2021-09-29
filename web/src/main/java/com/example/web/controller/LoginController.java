package com.example.web.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class LoginController {

    @RequestMapping("/tologin")
    public String tologin(){
        return "/login";
    }

    @RequestMapping("/login")
    public String login(String username, String password, Model model){

        if("".equals(username) || "".equals(password)){
            model.addAttribute("msg","请输入用户名和密码");
            return "/login";
        }

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken userToken = new UsernamePasswordToken(username,password);

        try{
            subject.login(userToken);
            return "index";
        }catch (UnknownAccountException ex){
            model.addAttribute("msg","用户名或密码不正确");
            return "/login";
        }
    }
}
