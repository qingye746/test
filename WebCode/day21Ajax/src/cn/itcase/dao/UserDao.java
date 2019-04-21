package cn.itcase.dao;

import cn.itcase.domain.Person;

import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/2/23 0023 20:57
 * @Version 1.0
 */
public interface UserDao {
    List<Person> findall();
}
