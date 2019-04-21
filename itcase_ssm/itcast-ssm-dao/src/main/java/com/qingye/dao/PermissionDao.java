package com.qingye.dao;

import com.qingye.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/4/3 0003 20:10
 * @Version 1.0
 */
public interface PermissionDao {
    /**
     * 根据角色ID查询权限
     * @return
     * @throws Exception
     */
    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{id} )")
    public List<Permission> findPermissionByRoleId()throws Exception;

    /**
     * 查询所有角色权限
     * @return
     * @throws Exception
     */
    @Select("select * from permission")
    List<Permission> findPermissionAll() throws Exception;

    /**
     * 添加角色权限
     * @param permission
     * @throws Exception
     */
    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    void permissionAdd(Permission permission) throws Exception;

    /**
     * 根据权限ID查询权限
     * @param id
     * @return
     * @throws Exception
     */
    @Select("select * from permission where id =#{id}")
    Permission findById(String id) throws Exception;
}
