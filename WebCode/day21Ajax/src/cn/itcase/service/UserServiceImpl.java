package cn.itcase.service;

import cn.itcase.dao.UserDao;
import cn.itcase.dao.UserDaoImpl;
import cn.itcase.domain.Person;

import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/2/23 0023 21:00
 * @Version 1.0
 */
public class UserServiceImpl implements UserService {

    private UserDao dao = new UserDaoImpl();

    @Override
    public List<Person> findAll() {
        return dao.findall();
    }
}
