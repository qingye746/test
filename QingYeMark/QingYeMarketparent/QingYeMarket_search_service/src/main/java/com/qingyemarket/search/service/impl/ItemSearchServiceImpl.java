package com.qingyemarket.search.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qingyemarket.pojo.TbItem;
import com.qingyemarket.search.service.ItemSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.ScoredPage;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: qingye
 * @Date: 2019/4/21 0021 16:44
 * @Version 1.0
 */
@Service(timeout = 5000)
public class ItemSearchServiceImpl implements ItemSearchService {

    @Autowired
    private SolrTemplate solrTemplate;

    @Override
    public Map search(Map searchMap) {
        Map map = new HashMap();
        Query query = new SimpleQuery("*:*");
        //添加查询条件，复制域：item_keywords  定义item_keywords结构为{“keywords”，“value”}
        Criteria criteria = new Criteria("item_keywords").is(searchMap.get("keywords"));
        query.addCriteria(criteria);
        ScoredPage<TbItem>  page = solrTemplate.queryForPage(query, TbItem.class);
        //将查询出来的结果放入rows关键字找中
        map.put("rows",page.getContent());
        return map;
    }
}
