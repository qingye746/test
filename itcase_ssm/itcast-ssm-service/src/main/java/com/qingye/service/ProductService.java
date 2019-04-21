package com.qingye.service;

import com.qingye.domain.Product;

import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/3/31 0031 19:06
 * @Version 1.0
 */
public interface ProductService {
    /**
     * 查询账户
     * @return
     * @throws Exception
     */
    List<Product> findAll(int page,int size) throws Exception;

    /**
     * 存储订单
     * @param product
     */
    void save(Product product)throws Exception;
}
