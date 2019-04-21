package com.qingyemarket.portal.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qingyemarket.content.service.ContentService;
import com.qingyemarket.pojo.TbContent;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/4/20 0020 19:28
 * @Version 1.0
 */
@RestController
@RequestMapping("/content")
public class ContentController {
    @Reference
    private ContentService contentService;

    @RequestMapping("/findByCategoryId")
    public List<TbContent> findByCategoryId(long categoryId) {
        return contentService.findByCategoryId(categoryId);
    }
}
