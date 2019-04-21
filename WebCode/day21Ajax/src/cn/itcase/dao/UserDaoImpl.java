package cn.itcase.dao;

import cn.itcase.domain.Person;
import cn.itcase.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.lang.invoke.VarHandle;
import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/2/23 0023 21:02
 * @Version 1.0
 */
public class UserDaoImpl implements UserDao {

    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());


    @Override
    public List<Person> findall() {
        String sql = "SELECT * FROM PL ";
        List<Person> persons = template.query(sql, new BeanPropertyRowMapper<Person>(Person.class));

        return persons;
    }
}
