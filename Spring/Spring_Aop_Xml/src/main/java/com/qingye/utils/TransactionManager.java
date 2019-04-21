package com.qingye.utils;

import java.sql.SQLException;

/**
 * @Author: qingye
 * @Date: 2019/3/17 0017 16:27
 * @Version 1.0
 */
public class TransactionManager {
    private ConnUtils connUtils;

    public void setConnUtils(ConnUtils connUtils) {
        this.connUtils = connUtils;
    }

    /**
     * 开启事务
     */
    public  void  beginTransaction(){
        try {
            connUtils.getThreadConn().setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 关闭事务
     */
    public  void  closeTransaction(){
        try {
            connUtils.getThreadConn().close();
            connUtils.removeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    /**
     * 提交事务
     */
    public  void  commitTransaction(){
        try {
            connUtils.getThreadConn().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    /**
     * 回滚事务
     */
    public  void  rollbackTransaction(){
        try {
            connUtils.getThreadConn().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


