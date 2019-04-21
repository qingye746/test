package com.qingye.dao;

import com.qingye.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/4/1 0001 21:13
 * @Version 1.0
 */
public interface TravellerDao {
    /**
     * 根据ID查询客户
     * @param ordersId
     * @return
     * @throws Exception
     */

    @Select("select *  from Traveller where id in (select travellerId  from order_traveller where orderID = #{ordersId} )")
    public List<Traveller> findByOrdersId(String ordersId) throws Exception;
}
