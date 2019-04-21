package com.qingye.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: qingye
 * @Date: 2019/3/21 0021 21:20
 * @Version 1.0
 */
public class User implements Serializable {
    private String uname;
    private Integer age;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "uname='" + uname + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                '}';
    }
}
