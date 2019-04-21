package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.dao.impl.CategoryDaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Author: qingye
 * @Date: 2019/3/1 0001 9:45
 * @Version 1.0
 */
public class CategoryServiceImpl implements CategoryService {
    CategoryDao dao = new CategoryDaoImpl();

    @Override
    public List<Category> findAll() {
        Jedis jedis = JedisUtil.getJedis();
        Set<Tuple> set = jedis.zrangeWithScores("category", 0, -1);
        List<Category> list = null;
        if (set == null || set.size() == 0) {
            list = dao.findAll();

            for (Category category : list) {
                jedis.zadd("category", category.getCid(), category.getCname());
            }
        } else {
            //将jedis的数据存入list中
            list = new ArrayList<Category>();

            for (Tuple tuple : set) {
                Category category = new Category();
                category.setCname(tuple.getElement());
                category.setCid((int)tuple.getScore());
                list.add(category);
            }
        }
        return list;
    }
}
