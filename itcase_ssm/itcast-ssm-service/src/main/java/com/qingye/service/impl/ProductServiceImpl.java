package com.qingye.service.impl;

import com.github.pagehelper.PageHelper;
import com.qingye.dao.ProductDao;
import com.qingye.domain.Product;
import com.qingye.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/3/31 0031 19:31
 * @Version 1.0
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Override
    public List<Product> findAll(int page,int size) throws Exception {
        PageHelper.startPage(page,size);
        return  productDao.findAll();
    }

    @Override
    public void save(Product product)throws Exception {

        productDao.save(product);
    }
}
