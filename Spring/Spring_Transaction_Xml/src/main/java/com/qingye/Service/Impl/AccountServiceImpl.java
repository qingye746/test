package com.qingye.Service.Impl;

import com.qingye.Service.AccountService;
import com.qingye.dao.AccountDao;
import com.qingye.domain.Account;

import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/3/16 0016 17:26
 * @Version 1.0
 */

public class AccountServiceImpl implements AccountService {
    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void saveAcc(Account account) {

        accountDao.saveAcc(account);
    }

    public void updateAcc(Account account) {
        accountDao.updateAcc(account);
    }

    public void deleteAcc(Integer id) {
        accountDao.deleteAcc(id);
    }

    public Account findById(Integer id) {
        return accountDao.findById(id);
    }

    public List<Account> findAll() {
        return accountDao.findAll();
    }

    public void transfer(String sourceName, String targetName, float money) {
        Account outAcc = accountDao.findByName(sourceName);
        Account getAcc = accountDao.findByName(targetName);
        if(outAcc.getMoney()>=money){
            outAcc.setMoney(outAcc.getMoney() - money);
            getAcc.setMoney(getAcc.getMoney() + money);
        }else {
            throw new RuntimeException("账户余额不足");
        }


        accountDao.updateAcc(outAcc);
        accountDao.updateAcc(getAcc);
    }
}
