package com.itheima.dao;

import com.itheima.domain.QueryVo;
import com.itheima.domain.User;

import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/3/10 0010 19:17
 * @Version 1.0
 */
public interface IUserDao {
    /**
     * 查询所有用户方法
     *
     * @return
     */
    List<User> findAll();

    /**
     * 添加用户方法
     *
     * @return
     */
    Integer userAdd(User user);

    /**
     * 根据Id删除用户
     *
     * @param id
     * @return
     */
    Integer userDelById(Integer id);

    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    Integer userUpdate(User user);

    /**
     * 根据id查询单个用户
     *
     * @param id
     * @return
     */
    User findUserById(Integer id);

    /**
     * 模糊查询
     *
     * @param name
     * @return
     */
    List<User> findUserByName(String name);

    /**
     * 根据用户名称模糊查询，参数变成一个 QueryVo 对象
     *
     * @param vo
     * @return
     */
    List<User> findUserByVo(QueryVo vo);

    /**
     * 多条件模糊查询
     *
     * @param user
     * @return
     */
    List<User> findUserByUser(User user);

    /**
     * 根据id集合模糊查询
     * @param vo
     * @return
     */
    List<User> findUserByIDs(QueryVo vo);

}
