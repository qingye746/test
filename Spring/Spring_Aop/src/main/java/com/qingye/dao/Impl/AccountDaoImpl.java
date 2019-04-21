package com.qingye.dao.Impl;

import com.qingye.dao.AccountDao;
import com.qingye.domain.Account;
import com.qingye.utils.ConnUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/3/16 0016 19:05
 * @Version 1.0
 */
@Service("accountDao")
public class AccountDaoImpl implements AccountDao {

    private QueryRunner runner;

    private ConnUtils connUtils;

    public void setConnUtils(ConnUtils connUtils) {
        this.connUtils = connUtils;
    }

    public void setRunner(QueryRunner runner) {
        this.runner = runner;
    }

    public void saveAcc(Account account) {
        try {
            runner.update(connUtils.getThreadConn(),"insert into account (name,money) values(?,?)",
                    account.getName(), account.getMoney());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAcc(Account account) {

        try {
            runner.update(connUtils.getThreadConn(),"update  account set name =?,money =? where id = ?",
                    account.getName(), account.getMoney(), account.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAcc(Integer id) {
        try {
            runner.update(connUtils.getThreadConn(),"delete from account where id = ?", id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Account findById(Integer id) {
        try {
            return runner.query(connUtils.getThreadConn(),"Select * from account where id = ?"
                    , new BeanHandler<Account>(Account.class), id);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public List<Account> findAll() {

        try {
            return runner.query(connUtils.getThreadConn(),"Select * from account",
                    new BeanListHandler<Account>(Account.class));
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public Account findByName(String outAccName) {
        try {
            List<Account> accounts = runner.query(connUtils.getThreadConn(),"Select * from account where name = ?"
                    , new BeanListHandler<Account>(Account.class), outAccName);
            if(accounts==null||accounts.size()==0){
                return null;
            }
            if (accounts.size()>1){
                throw  new RuntimeException("结果集不唯一");
            }
            return accounts.get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
