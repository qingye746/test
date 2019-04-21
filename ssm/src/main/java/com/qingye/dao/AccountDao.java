package com.qingye.dao;

import com.qingye.domain.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/3/23 0023 18:35
 * @Version 1.0
 */
@Repository
public interface AccountDao {

    /**
     * 保存账户
     */
    @Insert(value = "insert into account(name,money) values(#{name},#{money})")
    public void  saveAccount( Account account);

    /**
     * 查询所有账户
     * @return
     */
    @Select(value = "select * from account")
    public List<Account> findAll();
}
