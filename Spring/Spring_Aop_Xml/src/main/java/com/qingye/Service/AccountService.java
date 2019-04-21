package com.qingye.Service;

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

    /**
     * 转账
     * @param sourceName  转出账户名
     * @param targetName  转进账户名
     * @param money       转出金额
     */
    void transfer(String sourceName, String targetName, float money);
}
