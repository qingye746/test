package com.qingye.test;

import com.qingye.dao.AccountDao;
import com.qingye.domain.Account;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


/**
 * @Author: qingye
 * @Date: 2019/3/24 0024 10:09
 * @Version 1.0
 */
public class MybatisTest {
    @Test
    public void run1() throws IOException {
        //加载配置文件
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建工厂
        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(is);
        //创建session对象
        SqlSession sqlSession = sf.openSession();
        //获取代理对象
        AccountDao mapper = sqlSession.getMapper(AccountDao.class);
        List<Account> list = mapper.findAll();
        for (Account account : list) {
            System.out.println(account);
        }
        sqlSession.close();
        is.close();
    }
    @Test
    public void run2() throws IOException {
        Account account = new Account();
        account.setName("清叶");
        account.setMoney(2222f);
        //获取资源
        InputStream is = Resources.getResourceAsStream("sqlMapConfig.xml");
        //创建工厂
        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(is);
        //创建session对象
        SqlSession sqlSession = sf.openSession();
        //创建代理对象
        AccountDao mapper = sqlSession.getMapper(AccountDao.class);
        //调用方法
        mapper.saveAccount(account);
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
        is.close();
    }
}
