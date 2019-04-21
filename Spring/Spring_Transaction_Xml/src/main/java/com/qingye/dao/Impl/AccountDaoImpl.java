package com.qingye.dao.Impl;

import com.qingye.dao.AccountDao;
import com.qingye.domain.Account;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/3/16 0016 19:05
 * @Version 1.0
 */

public class AccountDaoImpl extends JdbcDaoSupport implements AccountDao {


    public void saveAcc(Account account) {
        super.getJdbcTemplate().update("insert into account (name,money) values(?,?)"
                , account.getName(), account.getMoney());
    }

    public void updateAcc(Account account) {
        super.getJdbcTemplate().update("UPDATE  account SET name = ?,money = ? WHERE id = ?"
                , account.getName(), account.getMoney(), account.getId());


    }

    public void deleteAcc(Integer id) {
        super.getJdbcTemplate().update("delete from account where id = ?", id);
    }

    public Account findById(Integer id) {
        List<Account> accounts = super.getJdbcTemplate().query("SELECT * FROM account WHERE id = ?"
                , new BeanPropertyRowMapper<Account>(Account.class), id);
        return accounts.isEmpty() ? null : accounts.get(0);
    }

    public List<Account> findAll() {
        List<Account> accounts = super.getJdbcTemplate().query("SELECT * FROM account "
                , new BeanPropertyRowMapper<Account>(Account.class));
        return accounts.isEmpty() ? null : accounts;

    }

    public Account findByName(String AccName) {
        List<Account> accounts = super.getJdbcTemplate().query("SELECT * FROM account WHERE name = ?"
                , new BeanPropertyRowMapper<Account>(Account.class), AccName);
        if (accounts.isEmpty()) {
            return null;
        } else if (accounts.size() == 1) {

            return accounts.get(0);
        }else {
            throw new RuntimeException("结果集不唯一");
        }
    }
}