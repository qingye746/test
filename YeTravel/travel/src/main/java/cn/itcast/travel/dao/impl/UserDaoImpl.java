package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Author: qingye
 * @Date: 2019/2/27 0027 15:17
 * @Version 1.0
 */
public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    User user = null;

    @Override
    public User findByUsername(String username) {
        try {
            String sql = "SELECT * FROM tab_user WHERE username = ?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        } catch (Exception e) {
        }
        return user;
    }

    @Override
    public void save(User user) {
        String sql = "INSERT INTO tab_user(username,password,name,birthday,sex,telephone,email,status,code) VALUES(?,?,?,?,?,?,?,?,?)";
        template.update(sql, user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone(),
                user.getEmail(),
                user.getStatus(),
                user.getCode());
    }

    @Override
    public User findBycode(String Code) {
        User user = null;
        try {
            String sql = "SELECT * FROM tab_user WHERE code = ?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), Code);
        } catch (Exception e) {

        }
        return user;
    }

    @Override
    public void updateStatus(User user) {
        String sql = "UPDATE tab_user SET status ='Y' WHERE uid = ?";
        template.update(sql, user.getUid());
    }

    @Override
    public User findByUserNandP(String username, String password) {
        User u = null;
        try {
            String sql = "SELECT * FROM tab_user WHERE username = ? AND password = ?";
            u = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),username,password);

        } catch (DataAccessException e) {

        }
        return u;
    }

}
