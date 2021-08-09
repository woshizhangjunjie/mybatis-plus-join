package com.plus.join.plus.methods;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.plus.join.plus.builder.SqlParse;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * 连表返回Map集合
 *
 * @author 张俊杰
 * @since 2021-08-06
 */
public class SelectMapJoin extends AbstractMethod {

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        String sqlMethod = "selectJoin";
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, SqlParse.parseJoinSql(tableInfo), modelClass);
        return this.addSelectMappedStatementForTable(mapperClass, sqlMethod, sqlSource, tableInfo);
    }
}
