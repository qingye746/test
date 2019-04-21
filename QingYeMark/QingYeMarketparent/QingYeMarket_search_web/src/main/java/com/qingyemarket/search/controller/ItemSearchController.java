package com.qingyemarket.search.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qingyemarket.search.service.ItemSearchService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: qingye
 * @Date: 2019/4/21 0021 17:23
 * @Version 1.0
 */
@RestController
@RequestMapping("/ItemSearch")
public class ItemSearchController {
    @Reference(timeout = 5000)
    private ItemSearchService itemSearchService;

    @RequestMapping("/search")
    public Map search(@RequestBody Map searchMap){
        return itemSearchService.search(searchMap);
    }
}
