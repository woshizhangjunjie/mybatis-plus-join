package com.plus.join.plus.methods;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.plus.join.plus.builder.SqlParse;
import com.plus.join.plus.builder.SqlPlusParse;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * 连表返回Map集合(3表连表)
 *
 * @author 张俊杰
 * @since 2022-08-23
 */
public class SelectPlusJoin extends AbstractMethod {

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        String sqlMethod = "selectPlusJoin";
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, SqlPlusParse.parseJoinSql(tableInfo), modelClass);
        return this.addSelectMappedStatementForTable(mapperClass, sqlMethod, sqlSource, tableInfo);
    }
}
