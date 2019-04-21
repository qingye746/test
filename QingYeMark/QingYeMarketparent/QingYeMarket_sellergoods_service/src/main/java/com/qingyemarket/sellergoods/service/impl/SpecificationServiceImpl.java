package com.qingyemarket.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qingyemarket.entity.PageResult;
import com.qingyemarket.mapper.TbSpecificationMapper;
import com.qingyemarket.mapper.TbSpecificationOptionMapper;
import com.qingyemarket.pojo.TbSpecification;
import com.qingyemarket.pojo.TbSpecificationExample;
import com.qingyemarket.pojo.TbSpecificationOption;
import com.qingyemarket.pojo.TbSpecificationOptionExample;
import com.qingyemarket.pojogroup.Specification;
import com.qingyemarket.sellergoods.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class SpecificationServiceImpl implements SpecificationService {

	@Autowired
	private TbSpecificationMapper specificationMapper;
	@Autowired
	private TbSpecificationOptionMapper tbSpecificationOptionMapper;
	/**
	 * 查询全部
	 */
	@Override
	public List<TbSpecification> findAll() {
		return specificationMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<TbSpecification> page=   (Page<TbSpecification>) specificationMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}



	
	/**
	 * 修改
	 */
	@Override
	public void update(Specification specification){
        //保存修改的规格
        specificationMapper.updateByPrimaryKey(specification.getSpecification());
        TbSpecificationOptionExample example = new TbSpecificationOptionExample();
        //criteria是个条件对象
        TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
        //指定规格ID为对象
        criteria.andSpecIdEqualTo(specification.getSpecification().getId());
        //删除原有的规格
        tbSpecificationOptionMapper.deleteByExample(example);
        for (TbSpecificationOption option : specification.getSpecificationOptionList()) {
            //循环插入规格
            option.setSpecId(specification.getSpecification().getId());
            tbSpecificationOptionMapper.insert(option);
        }

	}	
	
	/**
	 *
	 * @param id
	 * @return
	 */
	@Override
	public Specification findOne(Long id){

        //获取参数实体
        TbSpecification tbSpecification = specificationMapper.selectByPrimaryKey(id);

        //获取规格选项列表
        TbSpecificationOptionExample example = new TbSpecificationOptionExample();
        TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
        criteria.andSpecIdEqualTo(id);
        List<TbSpecificationOption> optionList = tbSpecificationOptionMapper.selectByExample(example);
        //构建实体类返回结果
        Specification specification = new Specification();
        specification.setSpecification(tbSpecification);
        specification.setSpecificationOptionList(optionList);
        return specification;
    }

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			specificationMapper.deleteByPrimaryKey(id);
			//删除选项表数据
            TbSpecificationOptionExample example = new TbSpecificationOptionExample();
            TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
            //根据ID为条件
            criteria.andSpecIdEqualTo(id);
            //删除
            tbSpecificationOptionMapper.deleteByExample(example);

		}		
	}
	
	
		@Override
	public PageResult findPage(TbSpecification specification, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		TbSpecificationExample example=new TbSpecificationExample();
		TbSpecificationExample.Criteria criteria = example.createCriteria();
		
		if(specification!=null){			
						if(specification.getSpecName()!=null && specification.getSpecName().length()>0){
				criteria.andSpecNameLike("%"+specification.getSpecName()+"%");
			}
	
		}
		
		Page<TbSpecification> page= (Page<TbSpecification>)specificationMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 规格输入的数据封装并添加
	 * @param specification
	 */
	@Override
	public void add(Specification specification) {
		//插入规格
		specificationMapper.insert(specification.getSpecification());
		for (TbSpecificationOption tbSpecificationOption : specification.getSpecificationOptionList()) {
			//设置规格ID
			tbSpecificationOption.setSpecId(specification.getSpecification().getId());
			tbSpecificationOptionMapper.insert(tbSpecificationOption);
		}
	}

	@Override
	public List<Map> selectOptionList() {

		return specificationMapper.selectOptionList();
	}


}
