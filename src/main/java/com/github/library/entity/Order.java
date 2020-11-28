package com.github.library.entity;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    private Integer oId;

    private Long orderNo;

    private Integer seatId;

    private Integer userId;

    private Date appointTime;

    private Date signTime;

    private Date signbackTime;

    private Byte status;

    private static final long serialVersionUID = 1L;

    public Integer getoId() {
        return oId;
    }

    public void setoId(Integer oId) {
        this.oId = oId;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getSeatId() {
        return seatId;
    }

    public void setSeatId(Integer seatId) {
        this.seatId = seatId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getAppointTime() {
        return appointTime;
    }

    public void setAppointTime(Date appointTime) {
        this.appointTime = appointTime;
    }

    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    public Date getSignbackTime() {
        return signbackTime;
    }

    public void setSignbackTime(Date signbackTime) {
        this.signbackTime = signbackTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}