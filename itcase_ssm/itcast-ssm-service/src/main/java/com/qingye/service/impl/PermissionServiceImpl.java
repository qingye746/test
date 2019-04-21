package com.qingye.service.impl;

import com.qingye.dao.PermissionDao;
import com.qingye.domain.Permission;
import com.qingye.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/4/4 0004 10:32
 * @Version 1.0
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionDao permissionDao;

    @Override
    public List<Permission> findAll() throws Exception {
        return permissionDao.findPermissionAll();
    }

    @Override
    public void permissionAdd(Permission permission) throws Exception {
        permissionDao.permissionAdd(permission);
    }

    @Override
    public Permission findById(String id) throws Exception {
        return permissionDao.findById(id);
    }
}