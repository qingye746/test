package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;

/**
 * @Author: qingye
 * @Date: 2019/3/4 0004 13:12
 * @Version 1.0
 */
public class FavoriteDaoImpl implements FavoriteDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public void add(int rid, int uid) {
        String sql = "insert into tab_favorite values (?,?,?) ";
        template.update(sql,rid,new Date(),uid);
    }

    @Override
    public Favorite findByRidAndUid(int rid, int uid) {
        Favorite favorite = null;
        try {
            String sql = "select  * from tab_favorite where rid = ? and uid = ? ";
        favorite =  template.queryForObject(sql,new BeanPropertyRowMapper<Favorite>(Favorite.class),rid,uid);

        }catch (Exception e){

        }
       return  favorite;
    }

    @Override
    public int findCountByRId(int rid) {
        String sql = "select count(rid) from tab_favorite where rid = ?";
        return  template.queryForObject(sql,Integer.class,rid);
    }
}
