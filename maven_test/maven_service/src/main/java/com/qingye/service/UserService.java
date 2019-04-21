package com.qingye.service;

import com.qingye.domain.User;

/**
 * @Author: qingye
 * @Date: 2019/3/28 0028 20:12
 * @Version 1.0
 */
public interface UserService {
    /**
     * 根据id寻找用户
     * @param id
     * @return
     */
    public User findUserById(Integer id);
}
