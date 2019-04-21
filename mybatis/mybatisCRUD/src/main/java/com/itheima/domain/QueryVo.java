package com.itheima.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/3/11 0011 9:37
 * @Version 1.0
 */
public class QueryVo implements Serializable {
    private User user;
    private List<Integer> ids;

    public QueryVo() {
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public QueryVo(User user, List<Integer> ids) {

        this.user = user;
        this.ids = ids;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "QueryVo{" +
                "user=" + user +
                ", ids=" + ids +
                '}';
    }
}
