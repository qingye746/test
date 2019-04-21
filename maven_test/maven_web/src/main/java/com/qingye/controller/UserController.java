package com.qingye.controller;

import com.qingye.domain.User;
import com.qingye.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: qingye
 * @Date: 2019/3/28 0028 21:39
 * @Version 1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
  private UserService userService;
    @RequestMapping("/showDetail")
    public String showDetail(Model model){
        User user = userService.findUserById(1);
        model.addAttribute("user", user);
        return "userDetail";
    }
}
