package com.qingyemarket.pojogroup;

import com.qingyemarket.pojo.TbSpecification;
import com.qingyemarket.pojo.TbSpecificationOption;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/4/13 0013 9:13
 * @Version 1.0
 */
public class Specification implements Serializable {
    /**
     *  前端传来的俩个数据，后台获取封装成实体类
     *  tbSpecificationOption 添加选项行的数组
     */
    private TbSpecification Specification;
    private List<TbSpecificationOption> SpecificationOptionList;

    public TbSpecification getSpecification() {
        return Specification;
    }

    public void setSpecification(TbSpecification specification) {
        Specification = specification;
    }

    public List<TbSpecificationOption> getSpecificationOptionList() {
        return SpecificationOptionList;
    }

    public void setSpecificationOptionList(List<TbSpecificationOption> specificationOptionList) {
        SpecificationOptionList = specificationOptionList;
    }

    @Override
    public String toString() {
        return "Specification{" +
                "Specification=" + Specification +
                ", SpecificationOptionList=" + SpecificationOptionList +
                '}';
    }
}
