package com.qingye.dao.Impl;

import com.qingye.dao.AccountDao;
import com.qingye.domain.Account;
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
@Service("accountService")
public class AccountDaoImpl implements AccountDao {

    private QueryRunner runner;

    public void setRunner(QueryRunner runner) {
        this.runner = runner;
    }

    public void saveAcc(Account account) {
        try {
            runner.update("insert into account (name,money) values(?,?)",
                account.getName(),account.getMoney());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAcc(Account account) {

        try {
            runner.update("update  account set name =?,money =? where id = ?",
                   account.getName(),account.getMoney(),account.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAcc(Integer id) {
        try {
            runner.update("delete from account where id = ?",id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Account findById(Integer id) {
        try {
            return  runner.query("Select * from account where id = ?"
                       ,new BeanHandler<Account>(Account.class),id);
        } catch (SQLException e) {
            throw  new RuntimeException();
        }
    }

    public List<Account> findAll() {

        try {
            return  runner.query("Select * from account",
                    new BeanListHandler<Account>(Account.class));
        } catch (SQLException e) {
            throw  new RuntimeException();
        }
    }
}
