package com.qingye.controller;

import com.qingye.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @Author: qingye
 * @Date: 2019/3/22 0022 16:48
 * @Version 1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/testString")
    public String testString(Model model) {
        // 模拟从数据库中查询的数据
        User user = new User();
        user.setUname("张三");
        user.setAge(20);
        user.setDate(new Date());
        model.addAttribute("user", user);
        return "Success";
    }

    /**
     * 当返回值为void时跳转或重定向
      * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/testVoid")
    public void testVoid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 模拟从数据库中查询的数据
        System.out.println("testVoid方法执行了");
        //  request.getRequestDispatcher("/WEB-INF/pages/Success.jsp").forward(request,response);
        response.sendRedirect(request.getContextPath() + "/index.jsp");
        return;
    }

    @RequestMapping("/modelAndView")
    public ModelAndView modelAndView() {
        // 模拟从数据库中查询的数据
        ModelAndView mv = new ModelAndView();
        User user = new User();
        user.setUname("张三");
        user.setAge(20);
        user.setDate(new Date());
        mv.addObject("user", user);

        //跳转到的页面
        mv.setViewName("Success");
        return mv;
    }

    /**
     * 页面跳转
     * @return
     */
    @RequestMapping("/testFR")
    public String testFR(){

        System.out.println("testFR方法执行了");

        // return "forward:/WEB-INF/pages/Success.jsp";
        return "redirect:/index.jsp";
    }

    @RequestMapping("/testAjax")
    public void testAjax(@RequestBody String body){

        System.out.println("testAjax方法执行了");
        System.out.println(body);
        // return "forward:/WEB-INF/pages/Success.jsp";

    }
}
