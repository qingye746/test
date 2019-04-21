package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.service.FavoriteService;

/**
 * @Author: qingye
 * @Date: 2019/3/4 0004 13:09
 * @Version 1.0
 */
public class FavoriteServiceImpl implements FavoriteService {
    private FavoriteDao favoriteDao = new FavoriteDaoImpl();
    @Override
    public void add(String rid, int uid) {
        favoriteDao.add(Integer.parseInt(rid),uid);
    }

    @Override
    public boolean isFavorite(String rid, int uid) {
      Favorite favorite =favoriteDao.findByRidAndUid(Integer.parseInt(rid),uid);
        return favorite !=null;
    }
}
