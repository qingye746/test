package com.qingye;

import com.qingye.domain.Account;

import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/3/16 0016 17:26
 * @Version 1.0
 */
public interface AccountService {

    /**
     * 保存
     * @param account
     */
    void saveAcc(Account account);

    /**
     * 更新
     * @param account
     */
    void updateAcc(Account account);

    /**
     * 更新
     * @param id
     */
    void deleteAcc(Integer id);

    /**
     * 根据Id查询
     * @param id
     */
    Account findById(Integer id);

    /**
     * 查询所有
     * @return
     */

    List<Account> findAll();
}