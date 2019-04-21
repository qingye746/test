package com.dao;



import com.domain.Account;

import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/3/11 0011 15:00
 * @Version 1.0
 */
public interface IAccountDao {

    /**
     * 查询用户的所有账户信息
     * @return
     */

    List<Account> findAccAll();


}
