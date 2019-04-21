package com.itheima.dao;

import com.itheima.domain.Account;
import com.itheima.domain.Role;
import com.itheima.domain.User;

import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/3/11 0011 15:00
 * @Version 1.0
 */
public interface IAccountDao {
    /**
     * 查询账户所有信息
     * @return
     */
    List<Account> findAll();

    /**
     * 查询单个用户下的所有
     * @return
     */
    List<User> findAccAll();


    /**
     * 查询所有角色
     * @return
     */
    List<Role> findRoleAll();


    /**
     * 查询用户的所用角色
     * @return
     */
    List<User> findRoleAllByUser();

}
