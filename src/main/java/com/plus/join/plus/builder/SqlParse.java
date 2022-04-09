package com.plus.join.plus.builder;

import com.baomidou.mybatisplus.core.metadata.TableInfo;

public class SqlParse {

    public static String parseJoinSql(TableInfo tableInfo) {
        return "<script>SELECT * FROM " + tableInfo.getTableName() + " ${joinWrapper.joinType} JOIN ${joinWrapper.tableName} ON "
                + tableInfo.getTableName() + ".${joinWrapper.joinKeyLeft} = ${joinWrapper.tableName}.${joinWrapper.joinKeyRight} WHERE 1 = 1"
                //eq筛选
                + " <foreach collection=\"joinWrapper.leftEqCriteria\" index=\"key\" item=\"value\" >"
                + " AND " + tableInfo.getTableName() + ".${key} = #{value}"
                + "</foreach>  "

                + " <foreach collection=\"joinWrapper.rightEqCriteria\" index=\"key\" item=\"value\" >"
                + " AND ${joinWrapper.tableName}.${key} = #{value}"
                + "</foreach>  "
                //ne筛选
                + " <foreach collection=\"joinWrapper.leftEqCriteria\" index=\"key\" item=\"value\" >"
                + " AND " + tableInfo.getTableName() + ".${key} != #{value}"
                + "</foreach>  "

                + " <foreach collection=\"joinWrapper.rightEqCriteria\" index=\"key\" item=\"value\" >"
                + " AND ${joinWrapper.tableName}.${key} != #{value}"
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

                //排序
                + "<if test=\" joinWrapper.leftOrderByDescCriteria.size()!=0 or joinWrapper.leftOrderByAscCriteria.size()!=0 or joinWrapper.rightOrderByDescCriteria.size()!=0 or joinWrapper.rightOrderByAscCriteria.size()!=0\">"
                + "ORDER BY"
                + "</if>"

                + " <foreach collection=\"joinWrapper.leftOrderByDescCriteria\" item=\"value\" separator=\",\">\n"
                + tableInfo.getTableName() + ".${value} DESC"
                + "</foreach>"

                //逗号拼接
                + "<if test=\"joinWrapper.leftOrderByDescCriteria.size()!=0 and (joinWrapper.leftOrderByAscCriteria.size()!=0 or joinWrapper.rightOrderByDescCriteria.size()!=0 or joinWrapper.rightOrderByAscCriteria.size()!=0)\">"
                + ","
                + "</if>"

                + " <foreach collection=\"joinWrapper.leftOrderByAscCriteria\" item=\"value\" separator=\",\">\n"
                + tableInfo.getTableName() + ".${value} ASC"
                + "</foreach>"

                //逗号拼接
                + "<if test=\"joinWrapper.leftOrderByAscCriteria.size()!=0 and (joinWrapper.rightOrderByDescCriteria.size()!=0 or joinWrapper.rightOrderByAscCriteria.size()!=0 )\">"
                + ","
                + "</if>"

                + " <foreach collection=\"joinWrapper.rightOrderByDescCriteria\" item=\"value\" separator=\",\">\n"
                + " ${joinWrapper.tableName}.${value} DESC"
                + "</foreach>"

                //逗号拼接
                + "<if test=\"joinWrapper.rightOrderByDescCriteria.size()!=0 and joinWrapper.rightOrderByAscCriteria.size()!=0\">"
                + ","
                + "</if>"

                + " <foreach collection=\"joinWrapper.rightOrderByAscCriteria\" item=\"value\" separator=\",\">\n"
                + " ${joinWrapper.tableName}.${value} ASC"
                + "</foreach>"
                + "</script>";
    }
}
