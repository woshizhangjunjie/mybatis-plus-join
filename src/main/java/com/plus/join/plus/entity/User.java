package com.plus.join.plus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user")
public class User {

    private Long id;
    private String name;

    @TableField(exist = false)
    private String total;

}
