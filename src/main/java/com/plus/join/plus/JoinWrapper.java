package com.plus.join.plus;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.LambdaUtils;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.core.toolkit.support.SerializedLambda;
import com.plus.join.plus.constant.JoinType;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.reflection.property.PropertyNamer;
import org.springframework.core.annotation.AnnotationUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 连表构造器
 *
 * @author 张俊杰
 * @since 2021-08-06
 */
@Getter
@Setter
public class JoinWrapper<L, R> implements JoinWrapperAbstract<SFunction<L, ?>, SFunction<R, ?>> {

    //关联的表名
    private String tableName;
    //关联方式 默认INNER
    private JoinType joinType = JoinType.INNER;
    //关联键左
    private String joinKeyLeft;
    //关联键右
    private String joinKeyRight;
    //左表=条件
    private Map<String, String> leftEqCriteria = new HashMap<>();
    //右表=条件
    private Map<String, String> rightEqCriteria = new HashMap<>();
    //左表like条件
    private Map<String, String> leftLikeCriteria = new HashMap<>();
    //右表like条件
    private Map<String, String> rightLikeCriteria = new HashMap<>();
    //左表between条件
    private Map<String, String> leftBetweenCriteria = new HashMap<>();
    //右表between条件
    private Map<String, String> rightBetweenCriteria = new HashMap<>();

    public JoinWrapper(Class joinClass) {
        this.tableName = verify(joinClass).value();
    }

    public JoinWrapper(Class joinClass, JoinType joinType) {
        this.tableName = verify(joinClass).value();
        this.joinType = joinType;
    }

    private JoinWrapper(String tableName) {
        this.tableName = tableName;
    }

    private JoinWrapper(String tableName, JoinType joinType) {
        this.tableName = tableName;
        this.joinType = joinType;
    }

    public static <L, R> JoinWrapper<L, R> join(Class joinClass) {
        return new JoinWrapper<>(verify(joinClass).value());
    }

    public static <L, R> JoinWrapper<L, R> join(Class joinClass, JoinType joinType) {
        return new JoinWrapper<>(verify(joinClass).value(), joinType);
    }

    private static TableName verify(Class joinClass) {
        TableName annotation = AnnotationUtils.findAnnotation(joinClass, TableName.class);
        Optional.ofNullable(annotation).orElseThrow(() -> new MybatisPlusException(joinClass.getName() + "类型没有 com.baomidou.mybatisplus.annotation.TableName 注解"));
        return annotation;
    }

    @Override
    public JoinWrapper on(SFunction<L, ?> left, SFunction<R, ?> right) {
        joinKeyLeft = columnToString(left);
        joinKeyRight = columnToString(right);
        return this;
    }

    @Override
    public JoinWrapper leftEq(boolean condition, SFunction<L, ?> column, Object value) {
        if (condition) leftEqCriteria.put(columnToString(column), String.valueOf(value));
        return this;
    }

    @Override
    public JoinWrapper leftEq(SFunction<L, ?> column, Object value) {
        leftEqCriteria.put(columnToString(column), String.valueOf(value));
        return this;
    }

    @Override
    public JoinWrapper rightEq(boolean condition, SFunction<R, ?> column, Object value) {
        if (condition) rightEqCriteria.put(columnToString(column), String.valueOf(value));
        return this;
    }

    @Override
    public JoinWrapper rightEq(SFunction<R, ?> column, Object value) {
        rightEqCriteria.put(columnToString(column), String.valueOf(value));
        return this;
    }

    @Override
    public JoinWrapper leftLike(boolean condition, SFunction<L, ?> column, Object value) {
        if (condition) leftLikeCriteria.put(columnToString(column), String.valueOf(value));
        return this;
    }

    @Override
    public JoinWrapper leftLike(SFunction<L, ?> column, Object value) {
        leftLikeCriteria.put(columnToString(column), String.valueOf(value));
        return this;
    }

    @Override
    public JoinWrapper rightLike(boolean condition, SFunction<R, ?> column, Object value) {
        if (condition) rightLikeCriteria.put(columnToString(column), String.valueOf(value));
        return this;
    }

    @Override
    public JoinWrapper rightLike(SFunction<R, ?> column, Object value) {
        rightLikeCriteria.put(columnToString(column), String.valueOf(value));
        return this;
    }

    @Override
    public JoinWrapper leftBetween(boolean condition, SFunction<R, ?> column, Object value1, Object value2) {
        if (condition) leftBetweenCriteria.put(columnToString(column), value1 + " AND " + value2);
        return this;
    }

    @Override
    public JoinWrapper leftBetween(SFunction<R, ?> column, Object value1, Object value2) {
        leftBetweenCriteria.put(columnToString(column), value1 + " AND " + value2);
        return this;
    }

    @Override
    public JoinWrapper rightBetween(boolean condition, SFunction<R, ?> column, Object value1, Object value2) {
        if (condition) rightBetweenCriteria.put(columnToString(column), value1 + " AND " + value2);
        return this;
    }

    @Override
    public JoinWrapper rightBetween(SFunction<R, ?> column, Object value1, Object value2) {
        rightBetweenCriteria.put(columnToString(column), value1 + " AND " + value2);
        return this;
    }


    private String columnToString(SFunction<?, ?> column) {
        return getColumn(LambdaUtils.resolve(column));
    }

    private String getColumn(SerializedLambda lambda) throws MybatisPlusException {
        return humpToUnderline(PropertyNamer.methodToProperty(lambda.getImplMethodName()));
    }

    /**
     * @param name 转换前的驼峰式命名的字符串
     * @return 转换后下划线小写方式命名的字符串
     */
    private static String humpToUnderline(String name) {
        StringBuilder result = new StringBuilder();
        if (name != null && name.length() > 0) {
            // 循环处理其余字符
            for (int i = 0; i < name.length(); i++) {
                String s = name.substring(i, i + 1);
                // 在大写字母前添加下划线
                if (s.equals(s.toUpperCase()) && !Character.isDigit(s.charAt(0))) {
                    result.append("_");
                }
                // 其他字符直接转成小写
                result.append(s.toLowerCase());
            }
        }
        return result.toString();
    }


}
