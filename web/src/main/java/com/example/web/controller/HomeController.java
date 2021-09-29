package com.example.web.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HomeController {

    @GetMapping("/index")
    public String Index(){



        return "/index";
    }


    @GetMapping("/test1")
    @ResponseBody
    @RequiresPermissions("user:add")
    public String test1(){
        return "授权1";
    }
    @GetMapping("/test2")
    @ResponseBody
    @RequiresPermissions("user:del")
    public String test2(){
        return "授权2";
    }

}
