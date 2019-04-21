package com.qingye.domain;

import java.io.Serializable;

/**
 * @Author: qingye
 * @Date: 2019/3/16 0016 18:55
 * @Version 1.0
 */
public class Account implements Serializable {
    private Integer id;
    private String name;
    private float money;

    public Account() {
    }

    public Account(Integer id, String name, float money) {
        this.id = id;
        this.name = name;
        this.money = money;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", money=" + money +
                '}';
    }
}
