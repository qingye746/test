package cn.itcase.Service;

import cn.itcase.domain.PageBean;
import cn.itcase.domain.User;

import java.util.List;
import java.util.Map;

/**
 * @Author: qingye
 * @Date: 2019/2/15 0015 18:57
 * @Version 1.0
 */
public interface UserService {
    List<User> findAll();
    User login(User user);

    void addUser(User user);

    void deleteUser(String id);


    User findUserByid(String id);

    void update(User user);

    void deleteUserById(String[] uids);

    PageBean<User> findUserByPage(String currentPage, String rows, Map<String, String[]> condition);
}
