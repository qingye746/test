package com.qingye.domain;

/**
 * @Author: qingye
 * @Date: 2019/3/22 0022 21:25
 * @Version 1.0
 */
public class SysException extends Exception {
    //提示异常信息
    private String massage;

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public SysException() {
    }

    public SysException(String massage) {
        this.massage = massage;
    }
}
