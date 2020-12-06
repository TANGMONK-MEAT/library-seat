package com.github.tangmonkmeat.entity;

import java.io.Serializable;

public class Role implements Serializable {
    private Integer rId;

    private Byte rAvailable;

    private String rName;

    private String rDesc;

    private static final long serialVersionUID = 1L;

    public Integer getrId() {
        return rId;
    }

    public void setrId(Integer rId) {
        this.rId = rId;
    }

    public Byte getrAvailable() {
        return rAvailable;
    }

    public void setrAvailable(Byte rAvailable) {
        this.rAvailable = rAvailable;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName == null ? null : rName.trim();
    }

    public String getrDesc() {
        return rDesc;
    }

    public void setrDesc(String rDesc) {
        this.rDesc = rDesc == null ? null : rDesc.trim();
    }
}