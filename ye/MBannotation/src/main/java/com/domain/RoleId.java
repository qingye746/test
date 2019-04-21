package com.domain;

import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/3/13 0013 10:50
 * @Version 1.0
 */
public class RoleId {
    private Integer uid;
    private Integer rid;
    private List<Role> roles;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }



    @Override
    public String toString() {
        return "RoleId{" +
                "uid=" + uid +
                ", rid=" + rid +
                ", roles=" + roles +
                '}';
    }
}
