package com.qingyemarket.sellergoods.service;

import com.qingyemarket.entity.PageResult;
import com.qingyemarket.pojo.TbSpecification;
import com.qingyemarket.pojogroup.Specification;

import java.util.List;
import java.util.Map;

/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface SpecificationService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<TbSpecification> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum, int pageSize);
	
	

	
	/**
	 * 修改
	 */
	public void update(Specification specification);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public Specification findOne(Long id);
	
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public void delete(Long[] ids);

	/**
	 * 分页
	 * @param pageNum 当前页 码
	 * @param pageSize 每页记录数
	 * @return
	 */
	public PageResult findPage(TbSpecification specification, int pageNum, int pageSize);

	/**
	 * 规格输入的数据封装并添加
	 * @param specification
	 */
	public void add(Specification specification);

	/**
	 * 返回规格数据的下拉列表
	 * @return
	 */
	List<Map> selectOptionList();
}
