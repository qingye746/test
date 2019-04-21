package cn.itcase.DAO.Impl;

import cn.itcase.DAO.UserDao;
import cn.itcase.domain.User;
import cn.itcase.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author: qingye
 * @Date: 2019/2/15 0015 19:02
 * @Version 1.0
 */
public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM user";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));

        return users;
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        try {
            String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void add(User user) {
        String sql = "INSERT INTO user VALUES(NULL,?,?,?,?,?,?,NULL,NULL)";
        template.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail());

    }

    @Override
    public void deleteUserById(int id) {
        String sql = "DELETE FROM user WHERE id = ?";
        template.update(sql, id);
    }

    @Override
    public User findUserByid(int id) {
        String sql = "SELECT * FROM user WHERE id = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), id);
    }

    @Override
    public void updateById(User user) {
        String sql = "UPDATE USER SET NAME=?,gender=?,age=?,address=?,qq=?,email=? WHERE id=?";
        template.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail(), user.getId());
    }

    /**
     * 条件查询
     * @param condition
     * @return
     */
    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        //定义sql查询语句初始化模板
        String sql = "SELECT count(*) FROM user WHERE 1 = 1 ";
            //解决sql语句拼接问题
            StringBuffer sb = new StringBuffer(sql);
            //定义参数的集合
            List<Object> params = new ArrayList<>();
            //遍历codition所有的参数键值
            Set<String> keyset = condition.keySet();
            for (String s : keyset) {
                //因为获取的是所有提交的参数，currentPage和rows与拼接Sql无关，所以跳过
                if ("currentPage".equals(s) || "rows".equals(s)) {
                    continue;
                }
                //获取condition中的value
                String value = condition.get(s)[0];
                //判断value是否有值
                if (value != null && !"".equals(value)) {
                    sb.append(" and "+s+" like ?");
                    //？条件的值
                    params.add("%"+value+"%");
                }
        }
        System.out.println(sb.toString());
        System.out.println(params);

        //.class利用反射机制
        return template.queryForObject(sb.toString(), Integer.class,params.toArray());
    }

    @Override
    public List<User> findByPage(int start, int rows, Map<String, String[]> condition) {
        //定义sql查询语句初始化模板
        String sql = "SELECT * FROM user WHERE 1 = 1";
        //解决sql语句拼接问题
        StringBuffer sb = new StringBuffer(sql);
        //定义参数的集合
        List<Object> params = new ArrayList<>();
        //遍历codition所有的参数键值
        Set<String> keyset = condition.keySet();
        for (String s : keyset) {
            //因为获取的是所有提交的参数，currentPage和rows与拼接Sql无关，所以跳过
            if ("currentPage".equals(s) || "rows".equals(s)) {
                continue;
            }
            //获取condition中的value
            String value = condition.get(s)[0];
            //判断value是否有值
            if (value != null && !"".equals(value)) {
                sb.append(" and "+s+" like ?");
                //？条件的值
                params.add("%"+value+"%");
            }
        }
        //添加分页查询
        sb.append(" limit ?,?");
        params.add(start);
        params.add(rows);
        sql=sb.toString();
        System.out.println(sql);
        System.out.println(params);
        return template.query(sql, new BeanPropertyRowMapper<User>(User.class), params.toArray());
    }


}
