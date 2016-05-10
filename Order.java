package com.lxr.SkillTest;

/**
 * Created by SoulCalculator on 16/5/10.
 */
public class Order {
    private int orderId;
    private String status;

    public Order(int orderId) {
        this.orderId = orderId;
        this.status = "NEW";
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
