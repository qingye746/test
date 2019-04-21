package com.itheima.domain;

import org.omg.PortableInterceptor.USER_EXCEPTION;

import java.io.Serializable;

/**
 * @Author: qingye
 * @Date: 2019/3/11 0011 14:51
 * @Version 1.0
 */
public class Account implements Serializable {
    private Integer id;
    private Integer uid;
    private double money;

    private User user;

    public Account() {
    }


    public Account(Integer id, Integer uid, double money, User user) {
        this.id = id;
        this.uid = uid;
        this.money = money;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", uid=" + uid +
                ", money=" + money +
                '}';
    }
}
