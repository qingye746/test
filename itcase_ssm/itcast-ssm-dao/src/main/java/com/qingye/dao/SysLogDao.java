package com.qingye.dao;

import com.qingye.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/4/6 0006 20:26
 * @Version 1.0
 */
public interface SysLogDao {

    /**
     * 保存实体类SysLog数据
     * @param sysLog
     * @throws Exception
     */
    @Insert("insert into syslog(visitTime,username,ip,url,executionTime,method) values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    public void save(SysLog sysLog) throws Exception;

    /**
     * 查询所有操作
     * @return
     * @throws Exception
     */
    @Select("Select * from syslog")
    List<SysLog> findAll() throws Exception;
}
