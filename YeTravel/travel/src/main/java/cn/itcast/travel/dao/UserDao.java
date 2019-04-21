package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

/**
 * @Author: qingye
 * @Date: 2019/2/27 0027 15:17
 * @Version 1.0
 */
public interface UserDao {
    /**
     * 查询用户名
     * @param username
     * @return
     */
      User findByUsername(String username);

    /**
     * 存储用户
     * @param user
     */
      void save(User user);

    User findBycode(String code);

    void updateStatus(User user);

    User findByUserNandP(String username, String password);
}
