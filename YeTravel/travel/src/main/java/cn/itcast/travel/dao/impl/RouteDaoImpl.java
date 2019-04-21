package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/3/1 0001 17:07
 * @Version 1.0
 */
public class RouteDaoImpl implements RouteDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public int findTotalCount(int cid, String rname) {
        //定义Sql模板
        String sql = " SELECT count(*) FROM tab_route WHERE 1=1 ";
        StringBuilder sb = new StringBuilder(sql);
        //将条件放入数组
        List params = new ArrayList();
        if (cid != 0) {
            sb.append(" and cid = ? ");
            params.add(cid);
        }
        if (rname != null && rname.length() > 0 &&!"null".equals(rname)) {
            sb.append(" and rname like ? ");
            params.add("%" + rname +  "%");

        }
        sql = sb.toString();
        System.out.println(sql);
        for (Object o : params.toArray()) {
            System.out.println(o);
        }
        Integer num = template.queryForObject(sql, Integer.class, params.toArray());
        return num;

    }

    @Override
    public List<Route> findByPage(int cid, int start, int pageSize, String rname) {
        //String sql = "SELECT * FROM tab_route WHERE cid = ? LIMIT ? ,?";
        String sql = "SELECT * FROM tab_route WHERE 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        List params = new ArrayList<>();
        if (cid != 0) {
            sb.append(" and cid = ? ");
            params.add(cid);
        }
        if (rname != null && rname.length() > 0&&!"null".equals(rname)) {// "null"
            sb.append(" and rname like ? ");
            params.add("%" + rname + "%");
        }
        //分页条件
        sb.append(" limit ? , ?");
        sql = sb.toString();

        params.add(start);

        params.add(pageSize);
        return template.query(sql, new BeanPropertyRowMapper<Route>(Route.class), params.toArray());
    }

    @Override
    public Route findOne(int rid) {
        String sql = "select * from tab_route where rid = ?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<Route>(Route.class),rid);
    }
}
