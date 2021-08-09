package com.plus.join.plus.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("wallet")
public class Wallet {

    private Long id;
    private String total;
    private Long userId;

}
