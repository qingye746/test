package com.dao;

import com.domain.Account;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/3/13 0013 9:29
 * @Version 1.0
 */
public interface IAccount {
    /**
     * 查询所有账户
     * @return
     */
    @Select("Select * from account")
    @Results(id = "accountMap",
            value ={
                    @Result(id = true,property ="id",column = "id"),
                    @Result(property = "uid",column = "uid"),
                    @Result(property = "money",column = "money"),
                    @Result(property = "user",column = "uid",
                            one = @One(select = "com.dao.IUserDao.findUserById",fetchType = FetchType.LAZY))
            } )
    List<Account> findAccAll();

    /**
     * 根据用户Id查询账户
     * @param uid
     * @return
     */
    @Select("Select * from account where uid =#{uid}")
    Account findAccById(Integer uid);
}
