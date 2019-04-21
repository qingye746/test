package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;

import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/3/1 0001 17:07
 * @Version 1.0
 */
public interface RouteDao {
    /**
     * 根据cid查询总记录数
     * @param cid
     * @param rname
     * @return
     */
    public int findTotalCount(int cid, String rname);

    /**
     * 根据cid查询当前页面数据集合
     * @param cid
     * @param start
     * @param pageSize
     * @param rname
     * @return
     */
    List<Route> findByPage(int cid, int start, int pageSize, String rname);

    Route findOne(int rid);
}
