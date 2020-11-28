package com.github.library.entity;

import java.io.Serializable;

public class Power implements Serializable {
    private Integer pId;

    private Integer pLevel;

    private static final long serialVersionUID = 1L;

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public Integer getpLevel() {
        return pLevel;
    }

    public void setpLevel(Integer pLevel) {
        this.pLevel = pLevel;
    }
}