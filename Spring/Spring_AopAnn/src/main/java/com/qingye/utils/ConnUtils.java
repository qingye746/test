package com.qingye.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @Author: qingye
 * @Date: 2019/3/17 0017 16:19
 * @Version 1.0
 */

@Component("connUtils")
public class ConnUtils {
    private ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
    @Autowired
    private DataSource dataSource;



    /**
     * 获取当前线程上的连接
     *
     * @return
     */
    public Connection getThreadConn() {
        //先从thread获取连接
        Connection conn = tl.get();
        //判断是否获取到连接
        try {
            if (conn == null){
                //从数据源中获取一个连接，存入到ThreadLocal
                conn =dataSource.getConnection();
                tl.set(conn);
            }
            return conn;
        }catch (Exception e){
            throw new RuntimeException();
        }

    }
    public void  removeConn(){
        tl.remove();
    }

}
