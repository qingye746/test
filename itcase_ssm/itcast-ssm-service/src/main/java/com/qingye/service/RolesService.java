package com.qingye.service;

import com.qingye.domain.Permission;
import com.qingye.domain.Role;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/4/4 0004 9:37
 * @Version 1.0
 */


public interface RolesService {
    /**
     * 查询所有角色
     * @return
     * @throws Exception
     */
    List<Role> findRoleAll() throws Exception;

    /**
     * 添加用户
     * @param role
     * @throws Exception
     */
    void addRoles(Role role) throws  Exception;

    /**
     * 根据ID查询角色
     * @return
     * @throws Exception
     */
    Role findRoleById(String id) throws Exception;

    /**
     * 删除角色
     * @param id
     * @throws Exception
     */
    void delRoleById(String id) throws  Exception;

    /**
     *  删除角色和权限的关联
     * @param id
     * @throws Exception
     */
    void delroleper(String id) throws Exception;

    /**
     * 删除角色和用户的关联
     * @param id
     */
    void delroleUser(String id) throws Exception;

    /**
     * 查询所有角色没有的权限
     * @param roleId
     * @return
     * @throws Exception
     */
    List<Permission> findRollAndOtherAllPermission(String roleId) throws Exception;

    /**
     *角色歌权限关联
     * @param roleId
     * @param pIds
     */
    void addPermissionToRole(String roleId, String[] pIds) throws Exception;

    /**
     * 查询所有角色的权限
     * @param roleId
     * @return
     * @throws Exception
     */
    List<Permission> findRollAndAllPermission(String roleId) throws Exception;

    /**
     * 根据ID删除权限
     * @param roleId
     * @param pIds
     */
    void delroleperById(String roleId, String[] pIds);
}
