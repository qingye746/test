package com.itcast.test;

import com.itcast.pojo.TbItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.ScoredPage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/4/21 0021 11:18
 * @Version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-solr.xml")
public class TestTemplate  {
    @Autowired
    private SolrTemplate solrTemplate;
    @Test
    public void  testAdd(){
        TbItem item=new TbItem();
        item.setId(1L);
        item.setBrand("华为");
        item.setCategory("手机");
        item.setGoodsId(10L);
        item.setSeller("华为2号专卖店");
        item.setTitle("华为Mate9");
        item.setPrice(new BigDecimal(2000));

        solrTemplate.saveBean(item);
        //事务提交
        solrTemplate.commit();
    }
    @Test
    public void findBId(){
        TbItem item = solrTemplate.getById(1L, TbItem.class);
        System.out.println(item.getTitle());
    }
    @Test
    public  void  deleteByid(){
        solrTemplate.deleteById("1");
        solrTemplate.commit();
    }

    @Test
    public void testAddList(){
        List<TbItem> list=new ArrayList();

        for(int i=0;i<100;i++){
            TbItem item=new TbItem();
            item.setId(i+1L);
            item.setBrand("华为");
            item.setCategory("手机");
            item.setGoodsId(1L);
            item.setSeller("华为2号专卖店");
            item.setTitle("华为Mate"+i);
            item.setPrice(new BigDecimal(2000+i));
            list.add(item);
        }
        //批量添加数据
        solrTemplate.saveBeans(list);
        solrTemplate.commit();
    }
    @Test
    public  void testPageQuery(){
        //构建了一个查询对象
        SimpleQuery query = new SimpleQuery("*:*");
        //调校为标题包含数字2和5
        Criteria criteria=new Criteria("item_category").contains("手机");
        criteria=criteria.and("item_title").contains("2");
        query.addCriteria(criteria);
       /* query.setOffset(20);//开始索引（默认0）
        query.setRows(20);//每页记录数(默认10)*/
        ScoredPage<TbItem> page = solrTemplate.queryForPage(query, TbItem.class);
        System.out.println("总记录数："+page.getTotalElements());
        System.out.println("总页数数："+page.getTotalPages());
        List<TbItem> list = page.getContent();
        showList(list);
    }

    private void showList(List<TbItem> list){
        for(TbItem item:list){
            System.out.println(item.getTitle() +item.getPrice());
        }
    }

    @Test
    public void testDeleteAll(){
        Query query=new SimpleQuery("*:*");
        solrTemplate.delete(query);
        solrTemplate.commit();
    }
}
