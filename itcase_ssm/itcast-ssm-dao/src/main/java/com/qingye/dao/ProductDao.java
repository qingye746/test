package com.qingye.dao;

import com.qingye.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/3/31 0031 19:09
 * @Version 1.0
 */
public interface ProductDao {
    /**
     * 根据id查询订单
     * @param id
     * @return
     * @throws Exception
     */
    @Select("select * from product where id = #{id}")
    Product findById(String id)throws Exception;
    /**
     * 查询账户所有产品
     * @return
     * @throws Exception
     */
    @Select("select * from product")
    List<Product> findAll() throws Exception;

    /**
     * 添加订单信息
     * @param product
     * @throws Exception
     */
    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product)throws Exception;
}
