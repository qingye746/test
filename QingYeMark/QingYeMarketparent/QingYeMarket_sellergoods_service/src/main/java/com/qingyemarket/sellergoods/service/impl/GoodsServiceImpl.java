package com.qingyemarket.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qingyemarket.entity.PageResult;
import com.qingyemarket.mapper.*;
import com.qingyemarket.pojo.*;
import com.qingyemarket.pojogroup.Goods;
import com.qingyemarket.sellergoods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
@Transactional
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private TbGoodsMapper goodsMapper;
	@Autowired
	private TbGoodsDescMapper goodsDescMapper;
	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemCatMapper itemCatMapper;
	@Autowired
	private TbBrandMapper brandMapper;
	@Autowired
	private TbSellerMapper sellerMapper;
	/**
	 * 查询全部
	 */
	@Override
	public List<TbGoods> findAll() {
		return goodsMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<TbGoods> page=   (Page<TbGoods>) goodsMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(Goods goods) {
		goods.getGoods().setAuditStatus("0");//设置未审核状态
		goodsMapper.insert(goods.getGoods());
		goods.getGoodsDesc().setGoodsId(goods.getGoods().getId());//设置ID
		goodsDescMapper.insert(goods.getGoodsDesc());//插入商品拓展数据
		//插入Sku商品数据方法
		saveItemList(goods);
	}

	//封装插入Sku商品数据方法
	private void saveItemList(Goods goods){
		//如果IsEnableSpec为1则启用规格
		if("1".equals(goods.getGoods().getIsEnableSpec())) {
			//规格参数插入数据库
			for (TbItem Item : goods.getItemList()) {
				//前端带来的数据数量不足，需要后台处理补充，存入数据库
				//构建标题（商品规格+商品名字）
				//1.获取商品名字
				String title = goods.getGoods().getGoodsName();
				//获取规格类型和规格数据的Map的集合
				Map<String, Object> specMap = JSON.parseObject(Item.getSpec());
				for (String key : specMap.keySet()) {
					title += "" + specMap.get(key);
				}

				//存入标题
				Item.setTitle(title);
				//重复插入的信息封装成方法
				setItemValus(goods,Item);
				itemMapper.insert(Item);
			}
		}else {
			//没有启动规格

			TbItem item=new TbItem();
			item.setTitle(goods.getGoods().getGoodsName());//商品KPU+规格描述串作为SKU名称
			item.setPrice( goods.getGoods().getPrice() );//价格
			item.setStatus("1");//状态
			item.setIsDefault("1");//是否默认
			item.setNum(0);//商品库存数量
			item.setSpec("{}");
			setItemValus(goods,item);
			itemMapper.insert(item);
		}
	}
	/**
	 * //重复插入的信息
	 * @param goods
	 * @param Item
	 */
	private void setItemValus(Goods goods,TbItem Item) {
		//存入商品的三级分类
		Item.setCategoryid(goods.getGoods().getCategory3Id());
		//存入创建日期
		Item.setCreateTime(new Date());
		//存入更新日期
		Item.setUpdateTime(new Date());
		//存入商品id
		Item.setGoodsId(goods.getGoods().getId());
		//存入商家id
		Item.setSellerId(goods.getGoods().getSellerId());
		//根据三级分类获取分类对象
		TbItemCat ItemCat = itemCatMapper.selectByPrimaryKey(goods.getGoods().getCategory3Id());
		//根据分类对象存入分类名称
		Item.setCategory(ItemCat.getName());
		//根据物品对象获取品牌对象
		TbBrand brand = brandMapper.selectByPrimaryKey(goods.getGoods().getBrandId());
		//根据品牌对象存入品牌名称
		Item.setBrand(brand.getName());
		//根据物品对象获取商家对象
		TbSeller seller = sellerMapper.selectByPrimaryKey(goods.getGoods().getSellerId());
		//根据商家id存入商家
		Item.setSeller(seller.getNickName());
		//图片
		List<Map> imagesList = JSON.parseArray(goods.getGoodsDesc().getItemImages(), Map.class);
		//如果有图片就存入地址Url
		if (imagesList.size() > 0) {
			Item.setImage((String) imagesList.get(0).get("url"));
		}
	}
	
	/**
	 * 修改
	 */
	@Override
	public void update(Goods goods){
		//更新基本表数据
		goodsMapper.updateByPrimaryKey(goods.getGoods());
		//更新拓展数据
		goodsDescMapper.updateByPrimaryKey(goods.getGoodsDesc());

		//删除原本数据
		TbItemExample example = new TbItemExample();
		TbItemExample.Criteria criteria = example.createCriteria();
		criteria.andGoodsIdEqualTo(goods.getGoods().getId());
		itemMapper.deleteByExample(example);

		//插入Sku商品数据方法
		saveItemList(goods);
	}

	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public Goods findOne(Long id){
		Goods goods = new Goods();
		//获得商品对象
		TbGoods tbGoods = goodsMapper.selectByPrimaryKey(id);
		goods.setGoods(tbGoods);
		//获取商品拓展属性
		TbGoodsDesc tbGoodsDesc = goodsDescMapper.selectByPrimaryKey(id);
		goods.setGoodsDesc(tbGoodsDesc);


        //读取商品Sku列表
        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        //查询条件
        criteria.andGoodsIdEqualTo(id);
		List<TbItem> itemList = itemMapper.selectByExample(example);

		goods.setItemList(itemList);
		return goods ;



	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			TbGoods goods = goodsMapper.selectByPrimaryKey(id);
			goods.setIsDelete("1");//逻辑删除，IsDelete不为NUll值即不显示
			goodsMapper.updateByPrimaryKey(goods);
		}		
	}
	
	
		@Override
	public PageResult findPage(TbGoods goods, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		TbGoodsExample example=new TbGoodsExample();
		TbGoodsExample.Criteria criteria = example.createCriteria();
		//isDelete值为NULL才显示
		criteria.andIsDeleteIsNull();
		if(goods!=null){			
						if(goods.getSellerId()!=null && goods.getSellerId().length()>0){
							//模糊查询不符合需求
				//criteria.andSellerIdLike("%"+goods.getSellerId()+"%");
							criteria.andSellerIdEqualTo(goods.getSellerId());
			}
			if(goods.getGoodsName()!=null && goods.getGoodsName().length()>0){
				criteria.andGoodsNameLike("%"+goods.getGoodsName()+"%");
			}
			if(goods.getAuditStatus()!=null && goods.getAuditStatus().length()>0){
				criteria.andAuditStatusLike("%"+goods.getAuditStatus()+"%");
			}
			if(goods.getIsMarketable()!=null && goods.getIsMarketable().length()>0){
				criteria.andIsMarketableLike("%"+goods.getIsMarketable()+"%");
			}
			if(goods.getCaption()!=null && goods.getCaption().length()>0){
				criteria.andCaptionLike("%"+goods.getCaption()+"%");
			}
			if(goods.getSmallPic()!=null && goods.getSmallPic().length()>0){
				criteria.andSmallPicLike("%"+goods.getSmallPic()+"%");
			}
			if(goods.getIsEnableSpec()!=null && goods.getIsEnableSpec().length()>0){
				criteria.andIsEnableSpecLike("%"+goods.getIsEnableSpec()+"%");
			}
			if(goods.getIsDelete()!=null && goods.getIsDelete().length()>0){
				criteria.andIsDeleteLike("%"+goods.getIsDelete()+"%");
			}
	
		}
		
		Page<TbGoods> page= (Page<TbGoods>)goodsMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}

	@Override
	public void updateStatus(long[] ids, String status) throws Exception {
		for (long id : ids) {
			TbGoods goods = goodsMapper.selectByPrimaryKey(id);
			goods.setAuditStatus(status);
			goodsMapper.updateByPrimaryKey(goods);
		}
	}

}
