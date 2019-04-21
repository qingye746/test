package com.qingye.service;

import com.qingye.domain.Account;

import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/3/23 0023 18:38
 * @Version 1.0
 */
public interface AccountService {

    /**
     * 保存账户
     */
    public void  saveAccount(Account account);

    /**
     * 查询所有账户
     * @return
     */
    public List<Account> findAll();
}
