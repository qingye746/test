package com.qingye.controller;

import com.github.pagehelper.PageInfo;
import com.qingye.domain.Product;
import com.qingye.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/3/31 0031 19:53
 * @Version 1.0
 */
@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * 存储订单
     * @param product
     */
    @RequestMapping("/save.do")
    public String save(Product product)throws Exception{
        productService.save(product);
        //添加完数据跳转到查询页面
        return "redirect:findAll.do";
    }

   /* *//**
     * 查询所有订单(未分页)
     * @return
     * @throws Exception
     *//*
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Product> products = productService.findAll();

        mv.addObject("productList",products);
        mv.setViewName("product-list1");
        return mv;
    }*/
    @RequestMapping("/findAll.do")
    @RolesAllowed("ADMIN")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,@RequestParam(name = "size",required = true,defaultValue = "4")Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Product> products = productService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(products);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("product-page-list");
        return mv;
    }
}

