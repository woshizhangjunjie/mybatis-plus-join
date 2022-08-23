package com.plus.join.plus;

public interface JoinPlusWrapperAbstract<L, C, R> {
    /**
     * 首次关联
     *
     * @param left  左表
     * @param center 中间表
     * @return
     */
    JoinPlusWrapper firstOn(L left,C center);

    /**
     * 再次关联
     *
     * @param center  中间表
     * @param right 右表
     * @return
     */
    JoinPlusWrapper secondOn(C center, R right);


    /**
     * 左表的=条件查询
     *
     * @param condition 是否执行
     * @param column    筛选字段
     * @param value     筛选值
     * @return
     */
    JoinPlusWrapper leftEq(boolean condition, L column, Object value);

    JoinPlusWrapper leftEq(L column, Object value);


    /**
     * 中间表的=条件查询
     *
     * @param condition 是否执行
     * @param column    筛选字段
     * @param value     筛选值
     * @return
     */
    JoinPlusWrapper centerEq(boolean condition, C column, Object value);

    JoinPlusWrapper centerEq(C column, Object value);

    /**
     * 右表的=条件查询
     *
     * @param condition 是否执行
     * @param column    筛选字段
     * @param value     筛选值
     * @return
     */
    JoinPlusWrapper rightEq(boolean condition, R column, Object value);

    JoinPlusWrapper rightEq(R column, Object value);

    /**
     * 右表的!=条件查询
     *
     * @param condition
     * @param column
     * @param value
     * @return
     */
    JoinPlusWrapper rightNe(boolean condition, R column, Object value);

    JoinPlusWrapper rightNe(R column, Object value);

    /**
     * 右表的!=条件查询
     *
     * @param condition
     * @param column
     * @param value
     * @return
     */
    JoinPlusWrapper leftNe(boolean condition, L column, Object value);

    JoinPlusWrapper leftNe(L column, Object value);

    /**
     * 左表的like条件查询
     *
     * @param condition
     * @param column
     * @param value
     * @return
     */
    JoinPlusWrapper leftLike(boolean condition, L column, Object value);

    JoinPlusWrapper leftLike(L column, Object value);

    /**
     * 右表的like条件查询
     *
     * @param condition
     * @param column
     * @param value
     * @return
     */
    JoinPlusWrapper rightLike(boolean condition, R column, Object value);

    JoinPlusWrapper rightLike(R column, Object value);

    /**
     * 左表的between条件查询
     *
     * @param condition
     * @param column    字段
     * @param value1    值1
     * @param value2    值2
     * @return
     */
    JoinPlusWrapper leftBetween(boolean condition, L column, Object value1, Object value2);

    JoinPlusWrapper leftBetween(L column, Object value1, Object value2);

    /**
     * 右表的between条件查询
     *
     * @param condition
     * @param column    字段
     * @param value1    值1
     * @param value2    值2
     * @return
     */
    JoinPlusWrapper rightBetween(boolean condition, R column, Object value1, Object value2);

    JoinPlusWrapper rightBetween(R column, Object value1, Object value2);

    JoinPlusWrapper leftOrderByDesc(boolean condition, L... column);

    JoinPlusWrapper leftOrderByDesc(L... column);

    JoinPlusWrapper leftOrderByDesc(L column);

    JoinPlusWrapper rightOrderByDesc(boolean condition, R... column);

    JoinPlusWrapper rightOrderByDesc(R... column);

    JoinPlusWrapper rightOrderByDesc(R column);

    JoinPlusWrapper leftOrderByAsc(boolean condition, L... column);

    JoinPlusWrapper leftOrderByAsc(L... column);

    JoinPlusWrapper leftOrderByAsc(L column);

    JoinPlusWrapper rightOrderByAsc(boolean condition, R... column);

    JoinPlusWrapper rightOrderByAsc(R... column);

    JoinPlusWrapper rightOrderByAsc(R column);
}
