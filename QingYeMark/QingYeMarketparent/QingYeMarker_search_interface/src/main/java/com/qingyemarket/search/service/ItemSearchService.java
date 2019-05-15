package com.qingyemarket.search.service;

import java.util.List;
import java.util.Map;

/**
 * @Author: qingye
 * @Date: 2019/4/21 0021 16:15
 * @Version 1.0
 */
public interface ItemSearchService {
    /**
     * 搜索方法
     * @param searchMap
     * @return
     */
    public Map search(Map searchMap);

    /**
     * 导入数据
     * @param list
     */
    public void importList(List list);

    /**
     * 删除数据
     * @param goodsIdList
     */
    public void deleteByGoodsIds(List goodsIdList);
}
