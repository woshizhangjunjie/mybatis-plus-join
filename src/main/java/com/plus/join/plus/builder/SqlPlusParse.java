package com.plus.join.plus.builder;

import com.baomidou.mybatisplus.core.metadata.TableInfo;

public class SqlPlusParse {

    public static String parseJoinSql(TableInfo tableInfo) {
        return "<script>SELECT * FROM " + tableInfo.getTableName() + " ${joinPlusWrapper.joinType} JOIN ${joinPlusWrapper.centerTableName} ON "
                + tableInfo.getTableName() + ".${joinPlusWrapper.firstJoinKeyLeft} = ${joinPlusWrapper.centerTableName}.${joinPlusWrapper.firstJoinKeyRight} " +
                "${joinPlusWrapper.joinType} JOIN ${joinPlusWrapper.rightTableName} ON "
                +"${joinPlusWrapper.centerTableName}.${joinPlusWrapper.secondJoinKeyLeft} = ${joinPlusWrapper.rightTableName}.${joinPlusWrapper.secondJoinKeyRight} " +
                "WHERE 1 = 1"
                //eq筛选
                + " <foreach collection=\"joinPlusWrapper.leftEqCriteria\" index=\"key\" item=\"value\" >"
                + " AND " + tableInfo.getTableName() + ".${key} = #{value}"
                + "</foreach>  "
                + " <foreach collection=\"joinPlusWrapper.centerEqCriteria\" index=\"key\" item=\"value\" >"
                + " AND ${joinPlusWrapper.centerTableName}.${key} = #{value}"
                + "</foreach>  "
                + " <foreach collection=\"joinPlusWrapper.rightEqCriteria\" index=\"key\" item=\"value\" >"
                + " AND ${joinPlusWrapper.rightTableName}.${key} = #{value}"
                + "</foreach>  "
                //ne筛选
                + " <foreach collection=\"joinPlusWrapper.leftEqCriteria\" index=\"key\" item=\"value\" >"
                + " AND " + tableInfo.getTableName() + ".${key} != #{value}"
                + "</foreach>  "

                + " <foreach collection=\"joinPlusWrapper.rightEqCriteria\" index=\"key\" item=\"value\" >"
                + " AND ${joinPlusWrapper.tableName}.${key} != #{value}"
                + "</foreach>  "
                //like筛选
                + " <foreach collection=\"joinPlusWrapper.leftLikeCriteria\" index=\"key\" item=\"value\" >"
                + " AND " + tableInfo.getTableName() + ".${key} like '%${value}%'"
                + "</foreach>  "

                + " <foreach collection=\"joinPlusWrapper.rightLikeCriteria\" index=\"key\" item=\"value\" >"
                + " AND ${joinPlusWrapper.tableName}.${key} like '%${value}%'"
                + "</foreach>  "
                //between筛选
                + " <foreach collection=\"joinPlusWrapper.leftBetweenCriteria\" index=\"key\" item=\"value\" >"
                + " AND (" + tableInfo.getTableName() + ".${key} BETWEEN ${value})"
                + "</foreach>  "

                + " <foreach collection=\"joinPlusWrapper.rightBetweenCriteria\" index=\"key\" item=\"value\" >"
                + " AND (${joinPlusWrapper.tableName}.${key} BETWEEN ${value})"
                + "</foreach>  "

                //排序
                + "<if test=\" joinPlusWrapper.leftOrderByDescCriteria.size()!=0 or joinPlusWrapper.leftOrderByAscCriteria.size()!=0 or joinPlusWrapper.rightOrderByDescCriteria.size()!=0 or joinPlusWrapper.rightOrderByAscCriteria.size()!=0\">"
                + "ORDER BY"
                + "</if>"

                + " <foreach collection=\"joinPlusWrapper.leftOrderByDescCriteria\" item=\"value\" separator=\",\">\n"
                + tableInfo.getTableName() + ".${value} DESC"
                + "</foreach>"

                //逗号拼接
                + "<if test=\"joinPlusWrapper.leftOrderByDescCriteria.size()!=0 and (joinPlusWrapper.leftOrderByAscCriteria.size()!=0 or joinPlusWrapper.rightOrderByDescCriteria.size()!=0 or joinPlusWrapper.rightOrderByAscCriteria.size()!=0)\">"
                + ","
                + "</if>"

                + " <foreach collection=\"joinPlusWrapper.leftOrderByAscCriteria\" item=\"value\" separator=\",\">\n"
                + tableInfo.getTableName() + ".${value} ASC"
                + "</foreach>"

                //逗号拼接
                + "<if test=\"joinPlusWrapper.leftOrderByAscCriteria.size()!=0 and (joinPlusWrapper.rightOrderByDescCriteria.size()!=0 or joinPlusWrapper.rightOrderByAscCriteria.size()!=0 )\">"
                + ","
                + "</if>"

                + " <foreach collection=\"joinPlusWrapper.rightOrderByDescCriteria\" item=\"value\" separator=\",\">\n"
                + " ${joinPlusWrapper.tableName}.${value} DESC"
                + "</foreach>"

                //逗号拼接
                + "<if test=\"joinPlusWrapper.rightOrderByDescCriteria.size()!=0 and joinPlusWrapper.rightOrderByAscCriteria.size()!=0\">"
                + ","
                + "</if>"

                + " <foreach collection=\"joinPlusWrapper.rightOrderByAscCriteria\" item=\"value\" separator=\",\">\n"
                + " ${joinPlusWrapper.tableName}.${value} ASC"
                + "</foreach>"
                + "</script>";
    }
}
