package com.qingye.service.impl;

import com.github.pagehelper.PageHelper;
import com.qingye.dao.OrdersDao;
import com.qingye.domain.Orders;
import com.qingye.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/4/1 0001 14:53
 * @Version 1.0
 */
@Service
@Transactional
public class OrderServiceImpl implements OrdersService {
    @Autowired
    private OrdersDao ordersDao;

    /**
     *
     * @param page
     * 查询页面
     * @param size
     * 每个页面查询的条数
     * @return
     * @throws Exception
     */
    @Override
    public List<Orders> findAll(int page,int size) throws Exception {
        PageHelper.startPage(page,size);
        return ordersDao.findAll();
    }

    /**
     * 根据用户ID查询用户
     * @param ordersId
     * @return
     * @throws Exception
     */
    @Override
    public Orders findById(String ordersId) throws Exception {
        return ordersDao.findById(ordersId);
    }
}
