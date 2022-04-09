package com.plus.join.plus;


public interface JoinWrapperAbstract<L, R> {

    /**
     * 关联
     *
     * @param left  左表
     * @param right 右表
     * @return
     */
    JoinWrapper on(L left, R right);

    /**
     * 左表的=条件查询
     *
     * @param condition 是否执行
     * @param column    筛选字段
     * @param value     筛选值
     * @return
     */
    JoinWrapper leftEq(boolean condition, L column, Object value);

    JoinWrapper leftEq(L column, Object value);

    /**
     * 右表的=条件查询
     *
     * @param condition 是否执行
     * @param column    筛选字段
     * @param value     筛选值
     * @return
     */
    JoinWrapper rightEq(boolean condition, R column, Object value);

    JoinWrapper rightEq(R column, Object value);

    /**
     * 右表的!=条件查询
     *
     * @param condition
     * @param column
     * @param value
     * @return
     */
    JoinWrapper rightNe(boolean condition, R column, Object value);

    JoinWrapper rightNe(R column, Object value);

    /**
     * 右表的!=条件查询
     *
     * @param condition
     * @param column
     * @param value
     * @return
     */
    JoinWrapper leftNe(boolean condition, L column, Object value);

    JoinWrapper leftNe(L column, Object value);

    /**
     * 左表的like条件查询
     *
     * @param condition
     * @param column
     * @param value
     * @return
     */
    JoinWrapper leftLike(boolean condition, L column, Object value);

    JoinWrapper leftLike(L column, Object value);

    /**
     * 右表的like条件查询
     *
     * @param condition
     * @param column
     * @param value
     * @return
     */
    JoinWrapper rightLike(boolean condition, R column, Object value);

    JoinWrapper rightLike(R column, Object value);

    /**
     * 左表的between条件查询
     *
     * @param condition
     * @param column    字段
     * @param value1    值1
     * @param value2    值2
     * @return
     */
    JoinWrapper leftBetween(boolean condition, L column, Object value1, Object value2);

    JoinWrapper leftBetween(L column, Object value1, Object value2);

    /**
     * 右表的between条件查询
     *
     * @param condition
     * @param column    字段
     * @param value1    值1
     * @param value2    值2
     * @return
     */
    JoinWrapper rightBetween(boolean condition, R column, Object value1, Object value2);

    JoinWrapper rightBetween(R column, Object value1, Object value2);

    JoinWrapper leftOrderByDesc(boolean condition, L... column);

    JoinWrapper leftOrderByDesc(L... column);

    JoinWrapper leftOrderByDesc(L column);

    JoinWrapper rightOrderByDesc(boolean condition, R... column);

    JoinWrapper rightOrderByDesc(R... column);

    JoinWrapper rightOrderByDesc(R column);

    JoinWrapper leftOrderByAsc(boolean condition, L... column);

    JoinWrapper leftOrderByAsc(L... column);

    JoinWrapper leftOrderByAsc(L column);

    JoinWrapper rightOrderByAsc(boolean condition, R... column);

    JoinWrapper rightOrderByAsc(R... column);

    JoinWrapper rightOrderByAsc(R column);
}
