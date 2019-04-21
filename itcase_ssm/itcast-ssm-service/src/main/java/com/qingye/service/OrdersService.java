package com.qingye.service;

import com.qingye.domain.Orders;
import com.qingye.domain.Product;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;

import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/4/1 0001 14:52
 * @Version 1.0
 */
public interface OrdersService {
    /**
     * 查询所有客户
     * @param page
     * 查询页面
     * @param size
     * 每个页面查询的条数
     * @return
     * @throws Exception
     */

    List<Orders> findAll(int page,int size)throws Exception;

    /**
     * 根据id查询用户
     * @param ordersId
     * @return
     * @throws Exception
     */
    Orders findById(String ordersId) throws Exception;
}
