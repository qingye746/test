package com.qingye.service.impl;

import com.qingye.dao.RoleDao;
import com.qingye.domain.Permission;
import com.qingye.domain.Role;
import com.qingye.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/4/4 0004 9:38
 * @Version 1.0
 */
@Service
public class RolesServiceImpl  implements RolesService{
    @Autowired
    private RoleDao roleDao;
    @Override
    public List<Role> findRoleAll() throws Exception {
        return roleDao.findRoleAll();
    }

    @Override
    public void addRoles(Role role) throws Exception {
        roleDao.addRoles(role);
    }

    @Override
    public Role findRoleById(String id) throws Exception {
        return roleDao.findRoleByRoleId(id);
    }

    @Override
    public void delRoleById(String id) throws Exception {
        roleDao.delRoleById(id);
    }

    @Override
    public void delroleper(String id) throws Exception {
        roleDao.delroleper(id);
    }

    @Override
    public void delroleUser(String id) throws  Exception {
        roleDao.delroleUser(id);
    }

    @Override
    public List<Permission> findRollAndOtherAllPermission(String roleId) throws Exception {
        return roleDao.findRollAndOtherAllPermission(roleId);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] pIds) throws Exception {
        for (String pId : pIds) {
            roleDao.addPermissionToRole(roleId,pId);
        }
    }

    @Override
    public List<Permission> findRollAndAllPermission(String roleId) throws Exception {
        return roleDao.findRollAndAllPermission(roleId);
    }

    @Override
    public void delroleperById(String roleId, String[] pIds) {
        for (String pId : pIds) {
            roleDao.delroleperById(roleId,pId);
        }
    }


}
