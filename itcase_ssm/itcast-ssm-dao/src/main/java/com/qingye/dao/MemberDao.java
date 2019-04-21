package com.qingye.dao;

import com.qingye.domain.Member;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: qingye
 * @Date: 2019/4/1 0001 20:56
 * @Version 1.0
 */
public interface MemberDao {
    /**
     * 根据id查询会员
     * @param memberId
     * @return
     * @throws Exception
     */
    @Select("select * from member where id = #{memberId}")
    Member findById(String memberId) throws Exception;
}
