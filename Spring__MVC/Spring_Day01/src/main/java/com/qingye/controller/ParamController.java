package com.qingye.controller;

import com.qingye.domain.Account;
import com.qingye.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: qingye
 * @Date: 2019/3/21 0021 20:56
 * @Version 1.0
 */
@Controller
@RequestMapping("/param")
public class ParamController {

    /**
     * 请求参数绑定入门
     * @return
     */
    @RequestMapping("/testParam")
    public String testParam(String username,String password,Integer money){
        System.out.println("用户名:"+username);
        System.out.println("密码:"+password);
        System.out.println("金额:"+money);
        return "Success";
    }

    /**
     * 把数据封装到JavaBean中
     * @param account
     * @return
     */
    @RequestMapping("/saveAccount")
    public String saveAccount(Account account){
        System.out.println(account);
        return "Success";
    }

    /**
     * 封装数据到User的实体类
     * @param user
     * @return
     */
    @RequestMapping("/saveUser")
    public String saveUser(User user){
        System.out.println(user);
        return "Success";
    }
}
