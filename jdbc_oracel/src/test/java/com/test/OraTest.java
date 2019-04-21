package com.test;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.oracore.OracleType;
import org.junit.Test;

import java.sql.*;

/**
 * @Author: qingye
 * @Date: 2019/3/26 0026 18:57
 * @Version 1.0
 */

public class OraTest {
    @Test
    public void javaCallOra() throws Exception {
        //加载数据库驱动
        Class.forName("oracle.jdbc.driver.OracleDriver");
        //等到connection资源

        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.102.10:1521:orcl","scott","root");
        //预编译的Statement对象
        PreparedStatement pstm = conn.prepareStatement("SELECT * from emp WHERE empno = ?");
        //给参数赋值
        pstm.setObject(1,7788);
        //执行数据库查询
        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()){
            System.out.println(resultSet.getString("ename"));
        }
        resultSet.close();
        pstm.close();
    }

    /**
     * java调用存储过程:包含存储过程
     * {?= call <procedure-name>[(<arg1>,<arg2>, ...)]}   调用存储函数使用
     *  {call <procedure-name>[(<arg1>,<arg2>, ...)]}   调用存储过程使用
     * @throws Exception
     */
    @Test
    public void javaCallOra2() throws Exception {
        //加载数据库驱动
        Class.forName("oracle.jdbc.driver.OracleDriver");
        //等到connection资源

        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.102.10:1521:orcl","scott","root");
        //预编译的Statement对象
        CallableStatement pstm = conn.prepareCall("{call p_yearsal(?, ?)}");
        //给参数赋值
        pstm.setObject(1,7788);
        pstm.setObject(2,1000);
        pstm.registerOutParameter(2, OracleTypes.NUMBER);
        //执行数据库查询
        pstm.execute();
        //输出结果[第二个参数]
        System.out.println(pstm.getObject(2));
        pstm.close();
        conn.close();
    }

    /**
     * java调用存储过程:包含存储函数
     * {?= call <procedure-name>[(<arg1>,<arg2>, ...)]}   调用存储函数使用
     *  {call <procedure-name>[(<arg1>,<arg2>, ...)]}   调用存储过程使用
     * @throws Exception
     */
    @Test
    public void javaCallOra3() throws Exception {
        //加载数据库驱动
        Class.forName("oracle.jdbc.driver.OracleDriver");
        //等到connection资源

        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.102.10:1521:orcl","scott","root");
        //预编译的Statement对象
        CallableStatement pstm = conn.prepareCall("{?= call f_yearsal(?)}");
        //给参数赋值
        pstm.setObject(2,7788);

        pstm.registerOutParameter(1, OracleTypes.NUMBER);
        //执行数据库查询
        pstm.execute();
        //输出结果[第1个参数]
        System.out.println(pstm.getObject(1));
        pstm.close();
        conn.close();
    }
}
