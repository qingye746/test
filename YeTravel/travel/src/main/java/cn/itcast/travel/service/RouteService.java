package cn.itcast.travel.service;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;

/**
 * @Author: qingye
 * @Date: 2019/3/1 0001 16:39
 * @Version 1.0
 */

/**
 * 分页查询
 */
public interface RouteService {

    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname);

    Route findOne(String rid);
}
