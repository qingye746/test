package com.qingye.Service.Impl;

import com.qingye.Service.AccountService;
import com.qingye.dao.AccountDao;
import com.qingye.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/3/16 0016 17:26
 * @Version 1.0
 */
@Service("accountService")
//只读事务
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;

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
    //读写性事务，只读关闭，十事务开启
    @Transactional(propagation= Propagation.REQUIRED,readOnly=false)
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
