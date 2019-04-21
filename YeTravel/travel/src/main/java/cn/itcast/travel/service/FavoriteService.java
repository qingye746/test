package cn.itcast.travel.service;

/**
 * @Author: qingye
 * @Date: 2019/3/4 0004 13:04
 * @Version 1.0
 */
public interface FavoriteService {
    /**
     * 添加收藏路线
     * @param rid
     * @param uid
     */
    void add(String rid, int uid);

    boolean isFavorite(String rid, int uid);
}