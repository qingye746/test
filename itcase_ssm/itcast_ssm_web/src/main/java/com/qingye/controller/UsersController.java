package com.qingye.controller;

import com.github.pagehelper.PageInfo;
import com.qingye.dao.UserDao;
import com.qingye.domain.Role;
import com.qingye.domain.UserInfo;
import com.qingye.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/4/3 0003 17:13
 * @Version 1.0
 */
@Controller
@RequestMapping("/user")
public class UsersController {
    @Autowired
    private UserService userService;

    /**
     * 分页查询所有用户
     *
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    @RequestMapping("/findUserAll.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView findUserAll(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "size", required = true, defaultValue = "4") Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<UserInfo> users = userService.findUserAll(page, size);
        PageInfo pageInfo = new PageInfo(users);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("user-page-list");
        return mv;
    }

    /**
     * 添加用户
     *
     * @param userInfo
     * @return
     */
    @RequestMapping("saveUser.do")
    @PreAuthorize("authentication.principal.username == 'qingye'")
    public String saveUser(UserInfo userInfo) throws Exception {
        userService.saveUser(userInfo);
        return "redirect:findUserAll.do";
    }

    /**
     * @param id
     * @throws Exception
     * @return通过id查询用户
     */
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id", required = true) String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(id);
        mv.addObject("user", userInfo);
        mv.setViewName("user-show");
        return mv;
    }

    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id", required = true) String userId) throws Exception {
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(userId);
        List<Role> roleList = userService.findOtherRolesByUserId(userId);
        mv.addObject("user",userInfo);
        mv.addObject("roleList",roleList);
        mv.setViewName("user-role-add");
        return mv;
    }
    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId") String userId,@RequestParam(name = "ids") String[] roleIds) throws Exception{
        userService.addRoleToUser(userId,roleIds);
        return "redirect:findUserAll.do";
    }


}
