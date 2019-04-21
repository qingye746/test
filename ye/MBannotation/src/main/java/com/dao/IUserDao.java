package com.dao;

import com.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/3/12 0012 20:06
 * @Version 1.0
 */
public interface IUserDao {
    /**
     * 查询所有用户
     *
     * @return
     */
    @Select("Select * from User")
    @Results(id = "userMap",
            value = {
                    @Result(id = true, property = "id", column = "id"),
                    @Result(property = "username", column = "username"),
                    @Result(property = "birthday", column = "birthday"),
                    @Result(property = "address", column = "address"),
                    @Result(property = "sex", column = "sex"),
                    @Result(property = "accounts",column = "id",
                            many = @Many(select = "com.dao.IAccount.findAccById",fetchType = FetchType.LAZY)),
                    @Result(property = "roleIds",column = "id",
                            many = @Many(select ="com.dao.RoleIdDao.findRoleByRoleId",fetchType = FetchType.LAZY))
            })
    List<User> findUserAll();




    /**
     * 根据Id查询用户
     *
     * @return
     */
    @Select("Select * from User where id = #{uid}")
    @ResultMap("userMap")
    User findUserById(Integer uid);


    /**
     * 添加用户
     *
     * @return
     */
    @Insert("insert into user (username,birthday,sex,address) " +
            "values (#{username},#{birthday},#{sex},#{address}) ")
    @SelectKey(keyProperty = "id", keyColumn = "id", resultType = Integer.class, before = false,
            statement = {"select last_insert_id()"})
    Integer addUser(User user);

    /**
     * 修改用户
     * @return
     */
    @Update(" update user set username=#{username},birthday=#{birthday},sex=#{sex}," +
            "address=#{address} where id =#{id} ")
    Integer updateUser(User user);

    /**
     * 根据id删除单个用户
     * @param id
     * @return
     */
    @Delete("delete from user where id = #{id} ")
    Integer delUserById(Integer id);

    /**
     * 查询用户总数
     * @return
     */
    @Select("select count(*) from user")
    Integer findTotalUser();

    /**
     * 模糊查询用户名
     * @param name
     * @return
     */
    @Select("select * from user where username like #{username}")
    List<User> findLikeUser(String name);

}
