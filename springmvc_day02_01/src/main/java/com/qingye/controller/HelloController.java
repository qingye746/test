package com.qingye.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: qingye
 * @Date: 2019/3/21 0021 19:51
 * @Version 1.0
 * 控制器
 */

@Controller
public class HelloController {
    @RequestMapping(path = "/hello")
    public String sayHello(){
        System.out.println("hello SpringMVC");
        return "Success";
    }
}
