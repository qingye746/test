package com.dao;

import com.domain.User;

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

    User findById();
}
