package com.github.library.entity;

import java.io.Serializable;
import java.util.Date;

public class Look implements Serializable {
    private Integer lkId;

    private Integer stuNo;

    private String stuName;

    private Date time;

    private String detail;

    private static final long serialVersionUID = 1L;

    public Integer getLkId() {
        return lkId;
    }

    public void setLkId(Integer lkId) {
        this.lkId = lkId;
    }

    public Integer getStuNo() {
        return stuNo;
    }

    public void setStuNo(Integer stuNo) {
        this.stuNo = stuNo;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName == null ? null : stuName.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }
}