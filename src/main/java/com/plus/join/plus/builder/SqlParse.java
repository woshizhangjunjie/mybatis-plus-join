package com.plus.join.plus.builder;

import com.baomidou.mybatisplus.core.metadata.TableInfo;

public class SqlParse {

    public static String parseJoinSql(TableInfo tableInfo) {
        return "<script>SELECT * FROM " + tableInfo.getTableName() + " ${joinWrapper.joinType} JOIN ${joinWrapper.tableName} ON "
                + tableInfo.getTableName() + ".${joinWrapper.joinKeyLeft} = ${joinWrapper.tableName}.${joinWrapper.joinKeyRight} WHERE 1=1"
                //eq筛选
                + " <foreach collection=\"joinWrapper.leftEqCriteria\" index=\"key\" item=\"value\" >"
                + " AND " + tableInfo.getTableName() + ".${key} = #{value}"
                + "</foreach>  "

                + " <foreach collection=\"joinWrapper.rightEqCriteria\" index=\"key\" item=\"value\" >"
                + " AND ${joinWrapper.tableName}.${key} = #{value}"
                + "</foreach>  "
                //like筛选
                + " <foreach collection=\"joinWrapper.leftLikeCriteria\" index=\"key\" item=\"value\" >"
                + " AND " + tableInfo.getTableName() + ".${key} like '%${value}%'"
                + "</foreach>  "

                + " <foreach collection=\"joinWrapper.rightLikeCriteria\" index=\"key\" item=\"value\" >"
                + " AND ${joinWrapper.tableName}.${key} like '%${value}%'"
                + "</foreach>  "
                //between筛选
                + " <foreach collection=\"joinWrapper.leftBetweenCriteria\" index=\"key\" item=\"value\" >"
                + " AND (" + tableInfo.getTableName() + ".${key} BETWEEN ${value})"
                + "</foreach>  "

                + " <foreach collection=\"joinWrapper.rightBetweenCriteria\" index=\"key\" item=\"value\" >"
                + " AND (${joinWrapper.tableName}.${key} BETWEEN ${value})"
                + "</foreach>  "

                + "</script>";
    }
}
