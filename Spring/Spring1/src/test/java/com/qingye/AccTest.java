package com.qingye;

import com.qingye.domain.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/3/16 0016 19:52
 * @Version 1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ="classpath:bean.xml" )
public class AccTest {

    @Autowired
    private  AccountService as;


    @Test
    public void testFindAll() {
        //3.执行方法
        List<Account> accounts = as.findAll();
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    @Test
    public void testFindOne() {
        //3.执行方法
        Account account = as.findById(1);
        System.out.println(account);
    }

    @Test
    public void testSave() {
        Account account = new Account();
        account.setName("test");
        account.setMoney(12345f);
        //3.执行方法
        as.saveAcc(account);

    }

    @Test
    public void testUpdate() {
        //3.执行方法
        Account account = as.findById(4);
        account.setMoney(23456f);
        as.updateAcc(account);
    }

    @Test
    public void testDelete() {
        //3.执行方法
        as.deleteAcc(4);
    }
}
