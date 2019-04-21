package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteImg;

import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/3/4 0004 10:16
 * @Version 1.0
 */
public interface RouteImgDao {
    List<RouteImg> findByRID(int rid);
}
