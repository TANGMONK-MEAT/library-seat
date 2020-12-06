package com.github.tangmonkmeat.entity;

import java.io.Serializable;

public class RolePower implements Serializable {
    private Integer rpId;

    private Integer roleId;

    private Integer powerId;

    private static final long serialVersionUID = 1L;

    public Integer getRpId() {
        return rpId;
    }

    public void setRpId(Integer rpId) {
        this.rpId = rpId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPowerId() {
        return powerId;
    }

    public void setPowerId(Integer powerId) {
        this.powerId = powerId;
    }
}