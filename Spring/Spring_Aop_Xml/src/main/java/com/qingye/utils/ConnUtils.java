package com.qingye.utils;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @Author: qingye
 * @Date: 2019/3/17 0017 16:19
 * @Version 1.0
 */
public class ConnUtils {
    private ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
    private DataSource dataSource;

    public void setTl(ThreadLocal<Connection> tl) {
        this.tl = tl;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

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
