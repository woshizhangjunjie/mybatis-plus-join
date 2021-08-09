package com.plus.join.plus.constant;

public enum JoinType {

    INNER("INNER"),
    FULL("FULL"),
    LEFT("LEFT"),
    RIGHT("RIGHT");

    private String type;

    JoinType(String type) {
        this.type = type;
    }

}
