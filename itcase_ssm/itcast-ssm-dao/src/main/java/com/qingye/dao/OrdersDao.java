package com.qingye.dao;

import com.qingye.domain.Member;
import com.qingye.domain.Orders;
import com.qingye.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/4/1 0001 14:55
 * @Version 1.0
 */
public interface OrdersDao {
    /**
     * 查询所有客户
     * @return
     * @throws Exception
     */
    @Select("select * from orders")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "productId",javaType = Product.class,one = @One(select = "com.qingye.dao.ProductDao.findById")),
    })
    List<Orders> findAll()throws Exception;

    /**
     * 根据用户id查询用户
     * @param ordersId
     * @return
     * @throws Exception
     */
    @Select("select * from orders where id =#{ordersId}")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "productId",javaType = Product.class,one = @One(select = "com.qingye.dao.ProductDao.findById")),
            @Result(property = "member",column = "memberId",javaType = Member.class,one = @One(select = "com.qingye.dao.MemberDao.findById")),
            @Result(property = "travellers",column = "id",javaType = java.util.List.class,many = @Many(select = "com.qingye.dao.TravellerDao.findByOrdersId"))

    })
    Orders findById(String ordersId) throws Exception;
}
