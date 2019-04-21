package com.qingye.controller;

import com.qingye.domain.Permission;
import com.qingye.domain.Role;
import com.qingye.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/4/4 0004 9:33
 * @Version 1.0
 */
@Controller
@RequestMapping("/role")
public class RolesController {
    @Autowired
    private RolesService rolesService;

    /**
     * 查询所有角色
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findRoleAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Role> roleList = rolesService.findRoleAll();
        mv.addObject("roleList", roleList);
        mv.setViewName("role-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public String saveRole(Role role) throws Exception {
        rolesService.addRoles(role);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true)String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        Role role = rolesService.findRoleById(id);
        mv.addObject("role",role);
        mv.setViewName("role-show");
        return mv;
    }
    @RequestMapping("/delRoleById.do")
    public String delRoleById(@RequestParam(name = "id",required = true) String id) throws Exception {
        rolesService.delroleper(id);
        rolesService.delroleUser(id);
        rolesService.delRoleById(id);

        return "redirect:findAll.do";
    }
    @RequestMapping("/findRollAndOtherAllPermission.do")
    public  ModelAndView findRollAndOtherAllPermission(@RequestParam(name = "id") String roleId)throws Exception{
        ModelAndView mv = new ModelAndView();
        Role role = rolesService.findRoleById(roleId);
        List<Permission> permissionList= rolesService.findRollAndOtherAllPermission(roleId);
        mv.addObject("role",role);
        mv.addObject("permissionList",permissionList);
        mv.setViewName("role-permission-add");
        return mv;
    }
    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam(name = "roleId") String roleId,@RequestParam(name = "PIds") String[] PIds) throws Exception{
        rolesService.addPermissionToRole(roleId,PIds);
        return "redirect:findAll.do";
    }
    @RequestMapping("/findRollAndAllPermission.do")
    public  ModelAndView findRollAndAllPermission(@RequestParam(name = "id")String roleId)throws Exception{
        ModelAndView mv = new ModelAndView();
        Role role = rolesService.findRoleById(roleId);
        List<Permission> permissionList= rolesService.findRollAndAllPermission(roleId);
        mv.addObject("role",role);
        mv.addObject("permissionList",permissionList);
        mv.setViewName("role-permission-del");
        return mv;
    }
    @RequestMapping("/delPermissionToRole.do")
    public String delPermissionToRole(@RequestParam(name = "roleId") String roleId,@RequestParam(name = "PIds") String[] PIds) throws Exception{
        rolesService.delroleperById(roleId,PIds);
        return "redirect:findAll.do";
    }
}
