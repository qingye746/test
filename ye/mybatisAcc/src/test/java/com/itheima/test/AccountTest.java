package com.itheima.test;
import com.itheima.dao.IAccountDao;
import com.itheima.domain.Account;
import com.itheima.domain.Role;
import com.itheima.domain.User;
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
    private IAccountDao roleDao;
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
        roleDao = session.getMapper(IAccountDao.class);
    }

    /**
     * 查询所有账户用户信息
     */
    @Test
    public void findAll(){
        List<Account> accountUsers = accountDao.findAll();
        for (Account accountUser : accountUsers) {
            System.out.println(accountUser);
            System.out.println(accountUser.getUser());
        }
    }

    /**
     * 查询所有用户账户信息通过User类
     */
    @Test
    public void findAccAll(){
        List<User> accAll = accountDao.findAccAll();
        for (User user : accAll) {
            System.out.println(user);
        }
    }

    /**
     * 查询所有对象分配的所有用户信息
     */
    @Test
    public void findRoleAll(){
        List<Role> roleAll = roleDao.findRoleAll();
        for (Role role : roleAll) {
            System.out.println(role);
        }
    }

    /**
     * 查询所有用户所拥有的角色
     */
    @Test
    public void findRoleAllByUser(){
        List<User> users = roleDao.findRoleAllByUser();
        for (User user : users) {
            System.out.println(user);
        }
    }
    @After
    public void destory() throws IOException {
        //手动提交事务
        // session.commit();
        //关闭流
        session.close();
        in.close();
    }

}
