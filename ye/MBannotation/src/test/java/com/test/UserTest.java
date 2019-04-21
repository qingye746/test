package com.test;


import com.dao.IAccount;
import com.dao.IRoleDao;
import com.dao.IUserDao;
import com.dao.RoleIdDao;
import com.domain.Account;
import com.domain.Role;
import com.domain.RoleId;
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
import java.util.Date;
import java.util.List;


/**
 * @Author: qingye
 * @Date: 2019/3/11 0011 15:15
 * @Version 1.0
 */
public class UserTest {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private IUserDao userDao;
    private IAccount accountDao;
    private IRoleDao roleDao;
    private RoleIdDao roleIdDao;
    @Before
    public void init() throws IOException {
        //加载配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建工厂
        factory = new SqlSessionFactoryBuilder().build(in);
        //生产session对象(true：自动提交事务)
        session = factory.openSession(true);
        //创建代理对象

        userDao = session.getMapper(IUserDao.class);
        accountDao = session.getMapper(IAccount.class);
        roleDao = session.getMapper(IRoleDao.class);
        roleIdDao = session.getMapper(RoleIdDao.class);
    }

    /**
     * 延迟查账户
     * 延迟查roleId集合
     * 延迟查role角色信息
     * 延迟加载
     */
    @Test
    public void findUserAll() {
        // List<User> users = userDao.findUserAll();
        List<User> users = session.selectList("findUserAll");
        for (User user : users) {
            System.out.println(user);
            //  System.out.println(user.getAccounts());
            System.out.println(user.getRoleIds());
            /*List<RoleId> roleIds = user.getRoleIds();
            for (RoleId roleId : roleIds) {
                System.out.println(roleId.getRoles());
            }*/
        }
    }

    /**
     * 根据用户Id查询所对应的角色
     * @param
     */
    @Test
    public void findRoleByRoleId(){
        List<RoleId> roleIds = roleIdDao.findRoleByRoleId(41);
        for (RoleId roleId : roleIds) {
            System.out.println(roleId);
            System.out.println(roleId.getRoles());
        }


    }

    /**
     * 查询单个用户
     */
    @Test
    public void findUserById() {
        List<User> users = session.selectList("findUserById", 49);
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 添加用户
     */
    @Test
    public void addUser() {
        User user = new User();
        user.setUsername("风雪");
        user.setAddress("福州");
        user.setBirthday(new Date());
        user.setSex("女");
        int num = session.insert("addUser", user);
        if (num > 0) {
            System.out.println("添加成功");
        } else {
            System.out.println("添加失败");
        }
    }

    /**
     * 修改用户
     */
    @Test
    public void updateUser() {
        User user = userDao.findUserById(49);
        user.setAddress("宁德");
        int num = session.update("updateUser", user);
        if (num > 0) {
            System.out.println("修改成功");
        } else {
            System.out.println("修改失败");
        }
    }

    /**
     * 根据Id删除用户
     */
    @Test
    public void delUserById() {
        int num = session.delete("delUserById", 49);
        if (num > 0) {
            System.out.println("删除成功");
        } else {
            System.out.println("删除失败");
        }
    }

    /**
     * 查询用户总数
     */
    @Test
    public void findTotalUser() {
        Integer totalUser = userDao.findTotalUser();
        System.out.println("一共有" + totalUser + "位用户");
    }

    /**
     * 模糊查询用户
     */

    @Test
    public void findLikeUser() {
        List<User> users = session.selectList("findLikeUser", "%王%");
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 只查询账户不查询用户懒加载
     */
    @Test
    public void findAccAll() {
        List<Account> accs = accountDao.findAccAll();
        for (Account acc : accs) {
            System.out.println(acc);
            //  System.out.println(acc.getUser()); //获取用户信息，懒加载就会开始加载
        }
    }





    /**
     * 结束执行
     *
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
