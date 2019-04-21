package com.qingye.test;

import com.qingye.service.AccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: qingye
 * @Date: 2019/3/23 0023 18:57
 * @Version 1.0
 */
public class TestString {
    @Test
    public void run(){
        //加载配置文件
     ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        //获取对象
        AccountService accountService =(AccountService) ac.getBean("accountService");
        //调用方法
        accountService.findAll();
    }
}
