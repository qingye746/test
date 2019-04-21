package com.qingye.dao;

import com.qingye.domain.Role;
import com.qingye.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/4/3 0003 10:31
 * @Version 1.0
 */
public interface UserDao {
    /**
     * 根据用户名查询用户
     * @param username
     * @return
     * @throws Exception
     */
    @Select("select * from users where username = #{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles",column = "id",javaType =java.util.List.class,many = @Many(select = "com.qingye.dao.RoleDao.findRoleByUserId"))
    })
    public UserInfo findByUsername(String username) throws  Exception;

    /**
     * 分页查询所有用户
     * @param page
     * @param size
     * @return
     */
    @Select("select * from users")
    List<UserInfo> findAll(int page ,int size) throws Exception;

    /**
     * 添加保存用户
     * @param userInfo
     */
    @Insert("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void saveUser(UserInfo userInfo) throws Exception;

    /**
     * 通过Id查询用户
     * @param id
     * @return
     * @throws Exception
     */
    @Select("select * from users where id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles",column = "id",javaType =java.util.List.class,many = @Many(select = "com.qingye.dao.RoleDao.findRoleByUserId"))
    })
    UserInfo findById(String id) throws Exception;

    /**
     * 通过UserID查询用户没有的角色信息
     * @param userId
     * @return
     */
    @Select("select * from role where id not in(select roleId from users_role where userId = #{userId})")
    List<Role> findOtherRolesByUserId(String userId) throws Exception;

    /**
     * 用户添加角色Id
     * @param userId
     * @param roleId
     */
    @Insert("insert into users_role(userId,roleId) values (#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId);
}
