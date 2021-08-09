package com.plus.join.plus;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.plus.join.plus.methods.SelectJoin;
import com.plus.join.plus.methods.SelectMapJoin;

import java.util.List;

public class MySqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        methodList.add(new SelectJoin());
        methodList.add(new SelectMapJoin());
        return methodList;
    }
}
