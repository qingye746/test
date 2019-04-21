package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Category;

import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/3/1 0001 9:41
 * @Version 1.0
 */
public interface CategoryDao {
    /**
     * 查询所有类别
     * @return
     */
    public List<Category> findAll();
}
