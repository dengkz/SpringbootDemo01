package com.example.web.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.apache.shiro.subject.Subject;

@Controller
public class ShiroController {

    @ResponseBody
    @RequestMapping("/shiro")
    public String shiro(){
        return "shiro";
    }




    @ResponseBody
    @RequestMapping("/unauth")
    public String unauth(){
        return "未授权";
    }

}
