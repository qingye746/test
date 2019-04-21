package com.qingyemarket.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qingyemarket.entity.PageResult;
import com.qingyemarket.entity.Result;
import com.qingyemarket.pojo.TbBrand;
import com.qingyemarket.sellergoods.service.BrandService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Author: qingye
 * @Date: 2019/4/10 0010 19:12
 * @Version 1.0
 */
@RestController
@RequestMapping("/brand")
public class BrandController {
   @Reference
    private BrandService brandService;

    /**
     * 查询所有品牌
     * @return
     */
    @RequestMapping("/findAll")
    public List<TbBrand> findAll(){
        return brandService.findAll();
    }
    @RequestMapping("/findPage")
    public PageResult findPage(int page,int rows){
        PageResult pageResult = brandService.findPage(page,rows);
        return pageResult;
    }

    /**
     * 添加品牌
     * @RequestBody 因为用复杂类型只能用post上传所以用@RequestBody标签
     * @param tbBrand
     * @return
     */
    @RequestMapping("/brandAdd")
    public Result brandAdd(@RequestBody TbBrand tbBrand){
        try {
            brandService.add(tbBrand);
            return new Result(true,"添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"添加失败");
        }
    }
    @RequestMapping("/brandUpdate")
    public Result brandUpdate(@RequestBody TbBrand tbBrand){
        try {
            brandService.brandUpdate(tbBrand);
            return new Result(true,"添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"添加失败");
        }
    }
    @RequestMapping("/findOne")
    public TbBrand findOne(long id){
     return   brandService.findOne(id);
    }
    @RequestMapping("/brandDelete")
    public Result brandDelete(long[] ids){
        try {
            brandService.brandDelete(ids);
            return new Result(true,"删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"删除失败");
        }
    }
    @RequestMapping("/search")
    public PageResult search(@RequestBody TbBrand tbBrand,int page,int rows){
        System.out.println(tbBrand);
        return brandService.findPage(tbBrand,page,rows);
    }
    @RequestMapping("/selectOptionList")
    public List<Map> selectOptionList(){
        return  brandService.selectOptionList();
    }
}
