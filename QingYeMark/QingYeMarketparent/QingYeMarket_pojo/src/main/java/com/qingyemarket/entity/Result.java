package com.qingyemarket.entity;

import java.io.Serializable;

/**
 * @Author: qingye
 * @Date: 2019/4/11 0011 18:56
 * @Version 1.0
 */
public class Result implements Serializable {
    private boolean success;
    private String massage;

    public Result(boolean success, String massage) {
        this.success = success;
        this.massage = massage;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }
}
