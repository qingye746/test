package com.qingye.controller;


import com.qingye.domain.Account;
import com.qingye.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/3/23 0023 19:24
 * @Version 1.0
 */

@Controller
@RequestMapping("/account")
public class AccountController {
    /**
     * 注入业务层对象
     */
    @Autowired
    private AccountService accountService;

    @RequestMapping("findAll")
    public String findAll(Model model) {
        System.out.println("表现层：查询所有账户...");
        //对象调用方法，整合SpringMVC
        List<Account> all = accountService.findAll();
        model.addAttribute("list",all);
        return "list";
    }

    @RequestMapping("saveAccount")
    public void saveAccount(Account account, HttpServletResponse response, HttpServletRequest request) throws IOException, ServletException {
        System.out.println("业务层：保存账户");
        accountService.saveAccount(account);
        request.getRequestDispatcher("/account/findAll").forward(request,response);
        return ;
    }
}
