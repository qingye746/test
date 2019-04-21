package com.qingye.service;

import com.qingye.domain.SysLog;

import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/4/6 0006 20:21
 * @Version 1.0
 */
public interface SysLogService {


    /**
     * 保存实体类SysLog数据
     * @param sysLog
     * @throws Exception
     */
    public void save(SysLog sysLog) throws Exception;

    /**
     * 查询所有操作
     * @return
     * @throws Exception
     */
    List<SysLog> findAll() throws Exception;
}
