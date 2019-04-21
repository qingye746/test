package com.qingye.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * @Author: qingye
 * @Date: 2019/3/17 0017 16:27
 * @Version 1.0
 */
@Component("transactionManager")
//配置切面
@Aspect
public class TransactionManager {
    @Autowired
    private ConnUtils connUtils;

    @Pointcut("execution(* com.qingye.Service.Impl.*.*(..))")
    private void pt1() {
    }

    /**
     * 开启事务
     */

    public void beginTransaction() {
        try {
            connUtils.getThreadConn().setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭事务
     */
    public void closeTransaction() {
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
    public void commitTransaction() {
        try {
            connUtils.getThreadConn().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * 回滚事务
     */
    public void rollbackTransaction() {
        try {
            connUtils.getThreadConn().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 环绕通知
     *
     * @param pjp
     * @return
     */
    @Around("pt1()")
    public Object aroundAdvice(ProceedingJoinPoint pjp) {
        Object rtValue = null;
        try {
            //获取参数
            Object[] args = pjp.getArgs();
            //开启事务
            this.beginTransaction();
            //执行方法
            rtValue = pjp.proceed(args);
            //提交事务
            this.commitTransaction();
        } catch (Throwable throwable) {
            //回滚事务
            this.rollbackTransaction();
            throw new RuntimeException(throwable);
        } finally {
            //释放资源
            this.closeTransaction();
        }
        return rtValue;
    }
}


