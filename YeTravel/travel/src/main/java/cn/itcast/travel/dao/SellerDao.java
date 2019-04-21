package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Seller;

/**
 * @Author: qingye
 * @Date: 2019/3/4 0004 10:25
 * @Version 1.0
 */
public interface SellerDao {
    Seller findById(int sid);
}
