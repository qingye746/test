package cn.itcase.service;

import cn.itcase.domain.Province;

import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/2/25 0025 18:39
 * @Version 1.0
 */
public interface ProvinceService {


    List<Province> findAll();

    String findAllJson();
}
