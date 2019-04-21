package cn.itcast.travel.service;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.dao.impl.CategoryDaoImpl;
import cn.itcast.travel.domain.Category;

import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/3/1 0001 9:42
 * @Version 1.0
 */
public interface CategoryService {

    public List<Category> findAll();
}
