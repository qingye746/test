package com.qingye.proxy;

import com.qingye.Service.AccountService;
import com.qingye.Service.Impl.AccountServiceImpl;
import com.qingye.utils.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: qingye
 * @Date: 2019/3/17 0017 16:01
 * @Version 1.0
 */
public class BeanFactory {
    private    AccountService accountService;
    private    TransactionManager transactionManager;

    public final void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    public   void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    /**
     * 工厂对象代理
     *
     * @return
     */
    public  AccountService getAccountService() {
        //定义被代理的对象

        //定义被代理的对象

        AccountService proxyAccService = (AccountService) Proxy.newProxyInstance(accountService.getClass().getClassLoader(),
                accountService.getClass().getInterfaces(),
                new InvocationHandler() {

                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object rtValue = null;
                        try {
                            //开启事务
                            transactionManager.beginTransaction();
                            //执行业务层
                            rtValue = method.invoke(accountService, args);
                            //提交事务
                            transactionManager.commitTransaction();
                        } catch (Exception e) {
                            transactionManager.rollbackTransaction();
                            throw  new RuntimeException(e);
                            //e.printStackTrace();
                        }finally {
                            transactionManager.closeTransaction();
                        }
                        return rtValue;
                    }
                });
        return proxyAccService;
    }
}
