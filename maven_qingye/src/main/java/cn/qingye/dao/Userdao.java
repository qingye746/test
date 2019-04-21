package cn.qingye.dao;

import cn.qingye.domain.Student;

import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/2/26 0026 19:18
 * @Version 1.0
 */
public interface Userdao {
    List<Student> findAll() throws Exception;
}
