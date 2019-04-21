package com.test;


import com.dao.IAccountDao;
import com.dao.IUserDao;
import com.domain.Account;
import com.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/3/11 0011 15:15
 * @Version 1.0
 */
public class AccountTest {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private IAccountDao accountDao;
    private IUserDao userDao;
    @Before
    public void init() throws IOException {
        //加载配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建工厂
        factory = new SqlSessionFactoryBuilder().build(in);
        //生产session对象(true：自动提交事务)
        session = factory.openSession(true);
        //创建代理对象
        accountDao = session.getMapper(IAccountDao.class);
        userDao = session.getMapper(IUserDao.class);
    }

    /**
     * 只查询账户不查询用户懒加载
     */
    @Test
    public void findAccAll(){
        List<Account> accs = accountDao.findAccAll();
        for (Account acc : accs) {
            System.out.println(acc);
         //  System.out.println(acc.getUser()); //获取用户信息，懒加载就会开始加载
        }
    }

    /**
     * 只查用户不查账户懒加载
     */
    @Test
    public void findUserAll(){
        List<User> users = userDao.findUserAll();
        for (User user : users) {
            System.out.println(user);
            System.out.println(user.getAccounts());
        }
    }

    /**
     * 结束执行
     * @throws IOException
     */
    @After
    public void destory() throws IOException {
        //手动提交事务
        // session.commit();
        //关闭流
        session.close();
        in.close();
    }

}
