package com.qingye.service.impl;

import com.qingye.dao.impl.UserDao;
import com.qingye.domain.User;
import com.qingye.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: qingye
 * @Date: 2019/3/28 0028 20:13
 * @Version 1.0
 */
@Service("UserServiceImpl")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    public User findUserById(Integer id) {
        return userDao.findById(id);
    }
}
