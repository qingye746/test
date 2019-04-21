package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.SellerDao;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Author: qingye
 * @Date: 2019/3/4 0004 10:26
 * @Version 1.0
 */
public class SellerDaoImpl implements SellerDao {
   JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public Seller findById(int sid) {
        String sql = "select * from tab_seller where sid = ?";
        return  template.queryForObject(sql,new BeanPropertyRowMapper<Seller>(Seller.class),sid);

    }
}
