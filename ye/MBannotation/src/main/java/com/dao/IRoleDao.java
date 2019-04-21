package com.dao;

import com.domain.Role;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

/**
 * @Author: qingye
 * @Date: 2019/3/13 0013 10:20
 * @Version 1.0
 */
public interface IRoleDao {

    /**
     * 按user查询角色
     * @return
     */
   @Select("select * from role where id = #{rid}")
   @Results(id = "roleMap",
            value = {
                    @Result(property = "roleId",column = "id"),
                    @Result(property = "roleName",column = "role_name"),
                    @Result(property = "roleDesc",column = "role_desc")
            })
   Role findRoleById(Integer rid);

}
