package com.qingye.dao.impl;

import com.qingye.domain.User;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: qingye
 * @Date: 2019/3/28 0028 19:55
 * @Version 1.0
 */
public interface UserDao {
    /**
     * 根据id寻找用户
     * @param id
     * @return
     */
    @Select("select * from items where id = #{id}")
    public User findById(Integer id);
}
