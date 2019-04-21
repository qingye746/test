package com.qingyemarket.sellergoods.service;

import com.qingyemarket.entity.PageResult;
import com.qingyemarket.pojo.TbBrand;

import java.util.List;
import java.util.Map;

/**
 * @Author: qingye
 * @Date: 2019/4/10 0010 19:04
 * @Version 1.0
 */
public interface BrandService {

    /**
     * 查询所有品牌
     * @return
     */
    public List<TbBrand> findAll();

    /**
     * 返回分页列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageResult findPage(int pageNum,int pageSize);

    /**
     * 添加品牌
     * @param tbBrand
     * @throws Exception
     */
    public void add(TbBrand tbBrand) throws Exception;

    /**
     * 修改品牌
     * @param tbBrand
     */

    void brandUpdate(TbBrand tbBrand) throws Exception;

    /**
     * 查询品牌
     * @param id
     * @return
     */
    TbBrand findOne(long id) ;

    /**
     * 根据id删除品牌
     * @param ids
     */
    void brandDelete(long[] ids) throws Exception;

    /**
     * 查询结果分页显示
     * @param tbBrand
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageResult findPage(TbBrand tbBrand,int pageNum,int pageSize);

    /**
     * 品牌下拉数据
     * @return
     */
    List<Map> selectOptionList();
}
