package cn.qingye.dao.Impl;

import cn.qingye.dao.Userdao;
import cn.qingye.domain.Student;
import cn.qingye.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/2/26 0026 19:19
 * @Version 1.0
 */
public class UserDaoImpl implements Userdao {
    @Override
    public List<Student> findAll() throws Exception{
        ArrayList<Student> students = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            //加载驱动类
            Class.forName("com.mysql.jdbc.Driver");
            //获取connection对象
            connection = DriverManager.getConnection("jdbc:mysql:///scoremysql", "root", "root");
            //获取真正的操作对象
            pst = connection.prepareCall("SELECT * FROM student");
            //执行数据库查询语句
            rs = pst.executeQuery();
            //把查询的数据转为list集合
            while (rs.next()) {
                Student student = new Student();
                student.setSid(rs.getInt("sid"));
                student.setGender(rs.getString("gender"));
                student.setClass_id(rs.getInt("class_id"));
                student.setSname(rs.getString("sname"));
                students.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
            pst.close();
            rs.close();

        }


        return students;
    }
   /* JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<Student> findAll() {
        String sql = "select * from student";
        List<Student> list = template.query(sql, new BeanPropertyRowMapper<Student>(Student.class));
        return list;
    }*/
}
