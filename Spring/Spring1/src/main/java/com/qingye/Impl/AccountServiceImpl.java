package com.qingye.Impl;

import com.qingye.AccountService;
import com.qingye.dao.AccountDao;
import com.qingye.dao.Impl.AccountDaoImpl;
import com.qingye.domain.Account;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/3/16 0016 17:26
 * @Version 1.0
 */
@Repository("accountDao")
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
      return   accountDao.findById(id);
    }

    public List<Account> findAll() {
        return accountDao.findAll();
    }
}
