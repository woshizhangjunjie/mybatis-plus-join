package com.plus.join.plus.entity;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("wallet")
public class Wallet {

    private Long id;
    private String total;
    private Long userId;

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWallet() {
        return total;
    }

    public void setWallet(String total) {
        this.total = total;
    }
}
