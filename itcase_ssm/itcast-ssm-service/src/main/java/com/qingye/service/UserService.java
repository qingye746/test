package com.qingye.service;

import com.qingye.domain.Role;
import com.qingye.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/4/3 0003 10:20
 * @Version 1.0
 */
public interface UserService extends UserDetailsService {
    /**
     * 分页查询所有用户
     * @param page
     * @param size
     * @return
     */
    List<UserInfo> findUserAll(int page,int size) throws Exception;

    /**
     * 保存用户
     * @param userInfo
     */
    void saveUser(UserInfo userInfo) throws Exception;

    /**
     * 通过id查询用户
     * @param id
     */
    UserInfo findById(String id) throws Exception;

    /**
     * 通过UserID查询用户没有的角色信息
     * @param userId
     * @return
     */
    List<Role> findOtherRolesByUserId(String userId) throws Exception;

    /**
     * 添加用户的角色
     * @param userId
     * @param roleIds
     */
    void addRoleToUser(String userId, String[] roleIds) throws Exception;
}
