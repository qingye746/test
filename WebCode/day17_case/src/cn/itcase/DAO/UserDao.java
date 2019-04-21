package cn.itcase.DAO;

import cn.itcase.domain.User;

import java.util.List;
import java.util.Map;

/**
 * @Author: qingye
 * @Date: 2019/2/15 0015 19:01
 * @Version 1.0
 */
public interface UserDao {
    List<User> findAll();
    User findUserByUsernameAndPassword(String username, String password);

    void add(User user);

    void deleteUserById(int id);

    User findUserByid(int id);

    void updateById(User user);

    int findTotalCount(Map<String, String[]> condition);

    List<User> findByPage(int start, int rows, Map<String, String[]> condition);
}
