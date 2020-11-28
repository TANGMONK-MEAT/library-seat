package com.github.library.entity;

import java.io.Serializable;

public class Role implements Serializable {
    private Integer rId;

    private String rName;

    private Integer rPower;

    private static final long serialVersionUID = 1L;

    public Integer getrId() {
        return rId;
    }

    public void setrId(Integer rId) {
        this.rId = rId;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName == null ? null : rName.trim();
    }

    public Integer getrPower() {
        return rPower;
    }

    public void setrPower(Integer rPower) {
        this.rPower = rPower;
    }
}