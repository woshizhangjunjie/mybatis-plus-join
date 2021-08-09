package com.plus.join.plus.methods;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * @author 张俊杰
 * @since 2021-08-06
 */
public class SelectJoin extends AbstractMethod {

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        String sqlMethod = "selectJoin";
        String sql = "<script>SELECT * FROM " + tableInfo.getTableName() + " JOIN ${joinWrapper.tableName} ON "
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
                + "</script>";
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        return this.addSelectMappedStatementForTable(mapperClass, sqlMethod, sqlSource, tableInfo);
    }
}
