package com.itheima.domain;

import java.io.Serializable;

/**
 * @Author: qingye
 * @Date: 2019/3/11 0011 14:57
 * @Version 1.0
 * 定义 AccountCustomer 类中要包含账户信息同时还要包含用户信息
 */
public class AccountUser extends Account implements Serializable {
    private String username;
    private String address;



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return  super.toString()+"AccountUser{" +
                "username='" + username + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
