package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Favorite;

/**
 * @Author: qingye
 * @Date: 2019/3/4 0004 13:11
 * @Version 1.0
 */
public interface FavoriteDao {

    void add(int rid, int uid);

    Favorite findByRidAndUid(int i, int uid);

    int findCountByRId(int rid);
}
