package cn.itcase.service;

import cn.itcase.domain.Person;

import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/2/23 0023 20:57
 * @Version 1.0
 */
public interface UserService {
    List<Person> findAll();
}
