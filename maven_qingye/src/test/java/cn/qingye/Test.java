package cn.qingye;

import cn.qingye.dao.Impl.UserDaoImpl;
import cn.qingye.dao.Userdao;
import cn.qingye.domain.Student;

import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/2/26 0026 19:48
 * @Version 1.0
 */
public class Test {
    private Userdao dao = new UserDaoImpl();
    @org.junit.Test
    public void findAll() throws Exception {
        List<Student> students = dao.findAll();
        for (Student student : students) {
            System.out.println(student);
        }
    }
}
