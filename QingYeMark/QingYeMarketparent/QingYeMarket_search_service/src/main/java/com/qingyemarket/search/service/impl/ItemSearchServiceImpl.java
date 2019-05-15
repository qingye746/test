package com.qingyemarket.search.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qingyemarket.pojo.TbItem;
import com.qingyemarket.search.service.ItemSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.*;
import org.springframework.data.solr.core.query.result.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Map search(Map searchMap) {
        Map map = new HashMap();
        //1查询列表
        map.putAll(searchList(searchMap));
        //2.分组查询商品分类列表
        List<String> categoryList = searchCategory(searchMap);
        map.put("categoryList", categoryList);
        //3.查询规格列表和品牌列表
        String category = (String) searchMap.get("category");
        if (!category.equals("")) {
            map.putAll(searchBrandAndSpec(category));
        } else {
            if (categoryList.size() > 0) {
                map.putAll(searchBrandAndSpec(categoryList.get(0)));
            }
        }

        return map;
    }

    /**
     * 导入数据
     *
     * @param list
     */
    @Override
    public void importList(List list) {
        //保存
        solrTemplate.saveBeans(list);
        //提交
        solrTemplate.commit();
    }

    @Override
    public void deleteByGoodsIds(List goodsIdList) {
        Query query = new SimpleQuery();
        //创建条件只要item_goodsid普通域中只要有goodsIdList内含有的id获得
        Criteria criteria = new Criteria("item_goodsid").in(goodsIdList);
        query.addCriteria(criteria);
        solrTemplate.delete(query);
        solrTemplate.commit();
    }


    /**
     * 查询列表
     *
     * @return
     */
    private Map searchList(Map searchMap) {
        Map map = new HashMap();
        //空格处理
        String keywords = (String) searchMap.get("keywords");
        searchMap.put("keywords", keywords.replace(" ", ""));

        //高亮初始化
        //获取高亮查询对象
        HighlightQuery query = new SimpleHighlightQuery();
        HighlightOptions highlightOptions = new HighlightOptions().addField("item_title");

        //设置高亮域的前后缀
        highlightOptions.setSimplePrefix("<em style='color:red'>");
        highlightOptions.setSimplePostfix("</em>");
        //设置高亮选项
        query.setHighlightOptions(highlightOptions);
        // Query query = new SimpleQuery("*:*");

        //1.1 添加查询条件(关键字查询)，复制域：item_keywords  定义item_keywords结构为{“keywords”，“value”}
        Criteria criteria = new Criteria("item_keywords").is(searchMap.get("keywords"));
        query.addCriteria(criteria);

        //1.2按商品分类过滤
        //如果用户点击了商品分类标签则执行
        if (!"".equals(searchMap.get("category"))) {

            FilterQuery filterQuery = new SimpleFilterQuery();
            //添加查询条件(分类查询)，复制域：item_keywords  定义item_category结构为{“category”，“value”}
            Criteria filterCriteria = new Criteria("item_category").is(searchMap.get("category"));
            filterQuery.addCriteria(filterCriteria);
            query.addFilterQuery(filterQuery);
        }
        //1.3按品牌分类过滤
        //如果用户点击了品牌分类标签则执行
        if (!"".equals(searchMap.get("brand"))) {

            FilterQuery filterQuery = new SimpleFilterQuery();
            //添加查询条件(分类查询)，复制域：item_keywords  定义item_keywords结构为{“category”，“value”}
            Criteria filterCriteria = new Criteria("item_brand").is(searchMap.get("brand"));
            filterQuery.addCriteria(filterCriteria);
            query.addFilterQuery(filterQuery);
        }
        //1.4按规格分类循环过滤
        //如果用户点击了规格分类标签则执行
        if (searchMap.get("spec") != null) {
            Map<String, String> specMap = (Map) searchMap.get("spec");
            for (String key : specMap.keySet()) {
                FilterQuery filterQuery = new SimpleFilterQuery();
                //添加查询条件(分类查询)，动态域：item_spec_*  定义item_keywords结构为{“key”，“value”}
                Criteria filterCriteria = new Criteria("item_spec_" + key).is(specMap.get(key));
                filterQuery.addCriteria(filterCriteria);


                query.addFilterQuery(filterQuery);
            }
        }


        //1.5按价格区间过滤
        //如果用户点击了品牌分类标签则执行
        if (!"".equals(searchMap.get("price"))) {
            String[] priceArray = ((String) searchMap.get("price")).split("-");
            if (!priceArray[0].equals("0")) {
                //如果最低价不为0
                FilterQuery filterQuery = new SimpleFilterQuery();
                //添加价格区间条件(分类查询)大于等于最低价格的条件
                Criteria filterCriteria = new Criteria("item_price").greaterThanEqual(priceArray[0]);
                filterQuery.addCriteria(filterCriteria);
                query.addFilterQuery(filterQuery);
            }
            if (!priceArray[1].equals("*")) {
                //如果最高价不为*
                FilterQuery filterQuery = new SimpleFilterQuery();
                //添加价格区间条件(分类查询)大小于最高价格的条件
                Criteria filterCriteria = new Criteria("item_price").lessThan(priceArray[1]);
                filterQuery.addCriteria(filterCriteria);
                query.addFilterQuery(filterQuery);
            }
        }

        //1.6分页（前端传来的数据pageNo当前页码和，pageSize每页的记录数
        //获取当前页
        Integer pageNo = (Integer) searchMap.get("pageNo");
        if (pageNo == null) {
            pageNo = 1;
        }
        Integer pageSize = (Integer) searchMap.get("pageSize");
        if (pageSize == null) {
            pageSize = 20;
        }

        //1.7排序
        //排序规则（升序降序）
        String sortValue = (String) searchMap.get("sort");
        //排序字段
        String sortField = (String) searchMap.get("sortField");
        if (sortValue != null && !sortValue.equals("")) {

            if (sortValue.equals("ASC")) { //升序
                Sort sort = new Sort(Sort.Direction.ASC, "item_" + sortField);
                query.addSort(sort);
            }
            if (sortValue.equals("DESC")) {//降序
                Sort sort = new Sort(Sort.Direction.DESC, "item_" + sortField);
                query.addSort(sort);
            }

        }

        //起始索引
        query.setOffset((pageNo - 1) * pageSize);
        //设置每页记录数
        query.setRows(pageSize);

        //****获取高亮结果集*******
        HighlightPage<TbItem> page = solrTemplate.queryForHighlightPage(query, TbItem.class);
        //循环高亮入口集合
        for (HighlightEntry<TbItem> h : page.getHighlighted()) {
            //获取原实体类

            TbItem item = h.getEntity();
            if (h.getHighlights().size() > 0 && h.getHighlights().get(0).getSnipplets().size() > 0) {
                //设置高亮结果
                item.setTitle(h.getHighlights().get(0).getSnipplets().get(0));
            }

        }
        map.put("rows", page.getContent());
        //总页数
        map.put("totalPages", page.getTotalPages());
        //总记录数
        map.put("total", page.getTotalElements());

        return map;
    }

    /**
     * 分组查询商品的分类列表
     *
     * @param searchMap
     * @return
     */
    private List<String> searchCategory(Map searchMap) {
        List<String> list = new ArrayList();
        Query query = new SimpleQuery("*:*");
        //创建查询条件 相当于Sql数据库的where
        Criteria criteria = new Criteria("item_keywords").is(searchMap.get("keywords"));
        query.addCriteria(criteria);
        //设置分组选项 相当于group by
        GroupOptions groupOptions = new GroupOptions().addGroupByField("item_category");
        query.setGroupOptions(groupOptions);
        //获取广告类分组页
        GroupPage<TbItem> page = solrTemplate.queryForGroupPage(query, TbItem.class);
        //获取分组查询的结果对象
        GroupResult<TbItem> groupResult = page.getGroupResult("item_category");
        //获取分组入口页
        Page<GroupEntry<TbItem>> groupEntries = groupResult.getGroupEntries();
        //获取分组入口的集合
        List<GroupEntry<TbItem>> entryList = groupEntries.getContent();

        for (GroupEntry<TbItem> tbItemGroupEntry : entryList) {
            //将获取到的结果字符串放入集合中
            list.add(tbItemGroupEntry.getGroupValue());
        }
        return list;
    }

    /**
     * 根据商品分类名称查询规格和品牌列表
     *
     * @param category 商品分类名称
     * @return
     */
    private Map searchBrandAndSpec(String category) {
        Map map = new HashMap();
        //通过商品分类名称获得模板ID
        Long templateId = (Long) redisTemplate.boundHashOps("itemCat").get(category);
        System.out.println(templateId);
        if (templateId != null) {
            //根据模板ID获取品牌列表
            List brandList = (List) redisTemplate.boundHashOps("brandList").get(templateId);
            System.out.println(brandList);
            map.put("brandList", brandList);

            //根据规格ID获取品牌列表
            List specList = (List) redisTemplate.boundHashOps("specList").get(templateId);
            System.out.println(specList);
            map.put("specList", specList);
        }
        return map;
    }

}
