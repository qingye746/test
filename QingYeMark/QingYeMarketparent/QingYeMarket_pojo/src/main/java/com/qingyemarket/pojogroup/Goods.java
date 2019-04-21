package com.qingyemarket.pojogroup;

import com.qingyemarket.pojo.TbGoods;
import com.qingyemarket.pojo.TbGoodsDesc;
import com.qingyemarket.pojo.TbItem;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/4/16 0016 14:47
 * @Version 1.0
 */
public class Goods implements Serializable {
    private TbGoods goods; //商品SPU
    private TbGoodsDesc goodsDesc; //商品扩展
    private List<TbItem> itemList; // 商品Sku列表

    public TbGoods getGoods() {
        return goods;
    }

    public void setGoods(TbGoods goods) {
        this.goods = goods;
    }

    public TbGoodsDesc getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(TbGoodsDesc goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public List<TbItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<TbItem> itemList) {
        this.itemList = itemList;
    }
}
