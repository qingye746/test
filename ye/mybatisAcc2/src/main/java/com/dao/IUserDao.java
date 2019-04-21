package com.dao;

import com.domain.User;

import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/3/12 0012 15:38
 * @Version 1.0
 */
public interface IUserDao {

    /**
     * 查询用户信息
     * @return
     */

    User findById(Integer id);


    /**
     * 查询所有用户信息
     * @return
     */
    List<User> findUserAll();
}
