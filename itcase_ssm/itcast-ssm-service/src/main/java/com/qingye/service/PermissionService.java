package com.qingye.service;

import com.qingye.domain.Permission;

import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/4/4 0004 10:31
 * @Version 1.0
 */
public interface PermissionService {
    /**
     * 查询所有角色权限
     * @return
     * @throws Exception
     */
    List<Permission> findAll() throws Exception;

    /**
     * 添加角色权限
     * @param permission
     * @throws Exception
     */
    void permissionAdd(Permission permission) throws Exception;

    /**
     * 根据ID查询权限
     * @param id
     * @return
     */
    Permission  findById(String id) throws Exception;
}
