package com.itheima.test;

import com.itheima.dao.IUserDao;
import com.itheima.domain.QueryVo;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/3/10 0010 19:32
 * @Version 1.0
 */

public class MybatisCRud {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
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
        userDao = session.getMapper(IUserDao.class);
    }

    /**
     * 查询所有用户
     */
    @Test
    public void findAll() {

        //使用代理对象执行方法
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }

    }


    /**
     * 添加用户
     */
    @Test
    public void userAdd() {
        User user = new User();
        user.setUsername("清叶");
        user.setAddress("福州");
        user.setSex("男");
        user.setBirthday(new Date());
        Integer n = userDao.userAdd(user);
        if (n != 0) {
            System.out.println("用户删除成功");
        } else {
            System.out.println("用户删除失败");
        }

    }

    /**
     * 根据id查询用户
     */
    @Test
    public void findUserById() {
        User user = userDao.findUserById(49);
        System.out.println(user);
    }

    /**
     * 修改用户
     */
    @Test
    public void userUpdate() {
        User user = userDao.findUserById(49);
        user.setAddress("厦门");
        Integer n = userDao.userUpdate(user);
        if (n != 0) {
            System.out.println("用户修改成功");
        } else {
            System.out.println("用户修改失败");
        }

    }

    /**
     * 删除用户
     */
    @Test
    public void userDelById() {
        Integer n = userDao.userDelById(42);
        if (n != 0) {
            System.out.println("用户删除成功");
        } else {
            System.out.println("用户删除失败");
        }

    }

    /**
     * 模糊查询
     */
    @Test
    public void findUserByName() {
        List<User> users = userDao.findUserByName("王");
        for (User user : users) {
            System.out.println(user);
        }

    }

    /**
     * 根据用户名称模糊查询，参数变成一个 QueryVo 对象
     */
    @Test
    public void findUserByVo() {
        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUsername("%王%");
        vo.setUser(user);
        List<User> userVos = userDao.findUserByVo(vo);
        for (User userVo : userVos) {
            System.out.println(userVo);
        }
    }

    /**
     * 多条件模糊查询
     */
    @Test
    public void findUserByUser() {
        User user = new User();
        user.setUsername("%王%");
        user.setAddress("%北京%");
        List<User> users = session.selectList("findUserByUser", user);
        for (User user1 : users) {
            System.out.println(user1);
        }
    }

    /**
     * 通过id查询
     */
    @Test
    public void findUserByIDs() {
        QueryVo vo = new QueryVo();
        List<Integer> ids = new ArrayList<Integer>();

        ids.add(41);
        ids.add(49);
        ids.add(48);
        vo.setIds(ids);
        List<User> users = session.selectList("findUserByIDs", vo);
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
