package com.qingye.dao;

import com.qingye.domain.Permission;
import com.qingye.domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/4/3 0003 11:19
 * @Version 1.0
 */
public interface RoleDao {
    /**
     * 根据用户Id查询角色
     * @param userId
     * @return
     * @throws Exception
     */
    @Select("Select * from role where id in (select roleId from users_role where userId = #{userId})")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "com.qingye.dao.PermissionDao.findPermissionByRoleId"))
    })
    public List<Role> findRoleByUserId(String userId) throws Exception;

    /**
     * 查询所有角色
     * @return
     * @throws Exception
     */
    @Select("Select * from role")
    List<Role> findRoleAll()throws Exception;

    /**
     * 添加用户
     * @param role
     */
    @Insert("insert into role (roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void addRoles(Role role);

    /**
     * 根据Id查询角色
     * @param roleId
     * @return
     * @throws Exception
     */
    @Select("select * from role where id =#{roleId}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "com.qingye.dao.PermissionDao.findPermissionByRoleId"))
    })
    Role findRoleByRoleId(String roleId) throws Exception;

    /**
     * 删除用户
     * @param roleId
     * @throws Exception
     */
    @Delete("delete from role where id = #{roleId}  ")
    void delRoleById(String roleId) throws Exception;

    /**
     * 删除权限和角色的关联
     * @param id
     */
    @Delete("delete from role_permission where roleid = #{roleId} ")
    void delroleper( String id);

    /**
     * 删除用户的角色关联
     * @param id
     */
    @Delete("delete from users_role where roleid = #{roleId} ")
    void delroleUser(String id);

    /**
     * 查询所有角色没有的权限
     * @param roleId
     * @return
     */
    @Select("select * from permission where id not in(select permissionId from role_permission where roleId = #{roleId})")
    List<Permission> findRollAndOtherAllPermission( String roleId);

    /**
     * 添加角色和权限的关联
     * @param roleId
     * @param permissionId
     */
    @Select("insert into role_permission (permissionId,roleId) values(#{permissionId},#{roleId})")
    void addPermissionToRole(@Param("roleId") String roleId,@Param("permissionId") String permissionId) throws Exception;

    /**
     * 查询所有角色的权限
     * @param roleId
     * @return
     */
    @Select("select * from permission where id  in(select permissionId from role_permission where roleId = #{roleId})")
    List<Permission> findRollAndAllPermission(String roleId);

    /**
     *根据权限Id删除关联
     * @param roleId
     * @param pId
     */
    @Delete("delete from role_permission where permissionid = #{permissionid}")
    void delroleperById(@Param("roleId") String roleId,@Param("permissionid") String pId);
}
