package com.dao;


import com.domain.RoleId;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/3/13 0013 10:47
 * @Version 1.0
 */
public interface RoleIdDao {
    /**
     * 根据user_roleId查询角色信息
     * @param uid
     * @return
     */
    @Select("select * from user_role where uid = #{uid}")
    @Results(id = "roleIdMap",
            value = {
                    @Result(property = "uid",column = "uid"),
                    @Result(property = "rid",column = "rid"),
                    @Result(property = "roles",column = "rid",
                            many = @Many(select = "com.dao.IRoleDao.findRoleById",fetchType = FetchType.LAZY))
            })
     List<RoleId>  findRoleByRoleId(Integer uid);
}
