package com.plus.join.plus.log;

import org.apache.ibatis.logging.Log;

public class MybatisPlusOutImpl implements Log {
    public MybatisPlusOutImpl(String clazz) {
        System.out.println("MybatisPlusOutImpl::"+clazz);
    }

    @Override
    public boolean isDebugEnabled() {
        return true;
    }
    @Override
    public boolean isTraceEnabled() {
        return true;
    }
    @Override
    public void error(String s, Throwable e) {
        System.err.println(s);
        e.printStackTrace(System.err);
    }
    @Override
    public void error(String s) {
        System.err.println("MybatisPlusOutImpl::"+s);
    }
    @Override
    public void debug(String s) {
        System.out.println("MybatisPlusOutImpl::"+s);
    }
    @Override
    public void trace(String s) {
        System.out.println("MybatisPlusOutImpl::"+s);
    }
    @Override
    public void warn(String s) {
        System.out.println("MybatisPlusOutImpl::"+s);
    }
}
