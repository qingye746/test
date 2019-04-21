package com.qingye.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: qingye
 * @Date: 2019/3/21 0021 21:43
 * @Version 1.0
 */
@Controller
@RequestMapping("/anno")
public class AnnoController {
    /**
     * 获取到元素内容标签
     * @param username
     * @return
     */
    @RequestMapping("/testRequestParam")
    public String testRequestParam(@RequestParam("name") String username){
        System.out.println(username+"执行了");
        return "Success";
    }

    /**
     * 获取到请求体标签
     * @param body
     * @return
     */
    @RequestMapping("/testRequestBody")
    public String testRequestBody(@RequestBody  String body){
        System.out.println(body);
        return "Success";
    }
}
