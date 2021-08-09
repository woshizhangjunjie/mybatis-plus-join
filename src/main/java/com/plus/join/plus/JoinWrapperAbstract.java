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
}
