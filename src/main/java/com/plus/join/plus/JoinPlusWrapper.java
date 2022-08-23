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

import java.util.*;

/**
 * 连表构造器(3表连表)
 *
 * @author 张俊杰
 * @since 2022-08-23
 */
@Getter
@Setter
public class JoinPlusWrapper<L, C, R> implements JoinPlusWrapperAbstract<SFunction<L, ?>, SFunction<C, ?>, SFunction<R, ?>> {


    //关联中间的表名
    private String centerTableName;
    //关联右边的表名
    private String rightTableName;
    //关联方式 默认INNER
    private JoinType joinType = JoinType.INNER;
    //首次关联键左
    private String firstJoinKeyLeft;
    //首次关联键右
    private String firstJoinKeyRight;
    //再次关联键左
    private String secondJoinKeyLeft;
    //再次关联键右
    private String secondJoinKeyRight;
    //左表=条件
    private Map<String, String> leftEqCriteria = new HashMap<>();
    //中间表=条件
    private Map<String, String> centerEqCriteria = new HashMap<>();
    //右表=条件
    private Map<String, String> rightEqCriteria = new HashMap<>();
    //左表!=条件
    private Map<String, String> leftNeCriteria = new HashMap<>();
    //右表!=条件
    private Map<String, String> rightNeCriteria = new HashMap<>();
    //左表like条件
    private Map<String, String> leftLikeCriteria = new HashMap<>();
    //右表like条件
    private Map<String, String> rightLikeCriteria = new HashMap<>();
    //左表between条件
    private Map<String, String> leftBetweenCriteria = new HashMap<>();
    //右表between条件
    private Map<String, String> rightBetweenCriteria = new HashMap<>();
    //左表的desc排序条件
    private List<String> leftOrderByDescCriteria = new ArrayList<>();
    //左表的asc排序条件
    private List<String> leftOrderByAscCriteria = new ArrayList<>();
    //右表的desc排序条件
    private List<String> rightOrderByDescCriteria = new ArrayList<>();
    //右表的asc排序条件
    private List<String> rightOrderByAscCriteria = new ArrayList<>();

    public JoinPlusWrapper(Class centerClass,Class rightClass) {
        this.centerTableName = verify(centerClass).value();
        this.rightTableName = verify(rightClass).value();
    }

    public JoinPlusWrapper(Class centerClass,Class rightClass, JoinType joinType) {
        this.centerTableName = verify(centerClass).value();
        this.rightTableName = verify(rightClass).value();
        this.joinType = joinType;
    }

    private JoinPlusWrapper(String centerTableName,String rightTableName) {
        this.centerTableName = centerTableName;
        this.rightTableName = rightTableName;
    }

    private JoinPlusWrapper(String centerTableName,String rightTableName, JoinType joinType) {
        this.centerTableName = centerTableName;
        this.rightTableName = rightTableName;
        this.joinType = joinType;
    }

    public static <L, C, R> JoinPlusWrapper<L, C, R> join(Class centerClass,Class rightClass) {
        return new JoinPlusWrapper<>(verify(centerClass).value(),verify(rightClass).value());
    }

    public static <L, C, R> JoinPlusWrapper<L, C, R> join(Class centerClass,Class rightClass, JoinType joinType) {
        return new JoinPlusWrapper<>(verify(centerClass).value(),verify(rightClass).value(), joinType);
    }


    private static TableName verify(Class joinClass) {
        TableName annotation = AnnotationUtils.findAnnotation(joinClass, TableName.class);
        Optional.ofNullable(annotation).orElseThrow(() -> new MybatisPlusException(joinClass.getName() + "类型没有 com.baomidou.mybatisplus.annotation.TableName 注解"));
        return annotation;
    }

    @Override
    public JoinPlusWrapper firstOn(SFunction<L, ?> left, SFunction<C, ?> center) {
        firstJoinKeyLeft = columnToString(left);
        firstJoinKeyRight = columnToString(center);
        return this;
    }

    @Override
    public JoinPlusWrapper secondOn(SFunction<C, ?> center, SFunction<R, ?> right) {
        secondJoinKeyLeft = columnToString(center);
        secondJoinKeyRight = columnToString(right);
        return this;
    }

    @Override
    public JoinPlusWrapper leftEq(boolean condition, SFunction<L, ?> column, Object value) {
        if (condition) leftEqCriteria.put(columnToString(column), String.valueOf(value));
        return this;
    }

    @Override
    public JoinPlusWrapper leftEq(SFunction<L, ?> column, Object value) {
        leftEqCriteria.put(columnToString(column), String.valueOf(value));
        return this;
    }

    @Override
    public JoinPlusWrapper centerEq(boolean condition, SFunction<C, ?> column, Object value) {
        if (condition) centerEqCriteria.put(columnToString(column), String.valueOf(value));
        return this;
    }

    @Override
    public JoinPlusWrapper centerEq(SFunction<C, ?> column, Object value) {
        centerEqCriteria.put(columnToString(column), String.valueOf(value));
        return this;
    }

    @Override
    public JoinPlusWrapper rightEq(boolean condition, SFunction<R, ?> column, Object value) {
        if (condition) rightEqCriteria.put(columnToString(column), String.valueOf(value));
        return this;
    }

    @Override
    public JoinPlusWrapper rightEq(SFunction<R, ?> column, Object value) {
        rightEqCriteria.put(columnToString(column), String.valueOf(value));
        return this;
    }

    @Override
    public JoinPlusWrapper rightNe(boolean condition, SFunction<R, ?> column, Object value) {
        if (condition) rightNeCriteria.put(columnToString(column), String.valueOf(value));
        return this;
    }

    @Override
    public JoinPlusWrapper rightNe(SFunction<R, ?> column, Object value) {
        rightNeCriteria.put(columnToString(column), String.valueOf(value));
        return this;
    }

    @Override
    public JoinPlusWrapper leftNe(boolean condition, SFunction<L, ?> column, Object value) {
        if (condition) rightNeCriteria.put(columnToString(column), String.valueOf(value));
        return this;
    }

    @Override
    public JoinPlusWrapper leftNe(SFunction<L, ?> column, Object value) {
        leftNeCriteria.put(columnToString(column), String.valueOf(value));
        return this;
    }

    @Override
    public JoinPlusWrapper leftLike(boolean condition, SFunction<L, ?> column, Object value) {
        if (condition) leftLikeCriteria.put(columnToString(column), String.valueOf(value));
        return this;
    }

    @Override
    public JoinPlusWrapper leftLike(SFunction<L, ?> column, Object value) {
        leftLikeCriteria.put(columnToString(column), String.valueOf(value));
        return this;
    }

    @Override
    public JoinPlusWrapper rightLike(boolean condition, SFunction<R, ?> column, Object value) {
        if (condition) rightLikeCriteria.put(columnToString(column), String.valueOf(value));
        return this;
    }

    @Override
    public JoinPlusWrapper rightLike(SFunction<R, ?> column, Object value) {
        rightLikeCriteria.put(columnToString(column), String.valueOf(value));
        return this;
    }

    @Override
    public JoinPlusWrapper leftBetween(boolean condition, SFunction<L, ?> column, Object value1, Object value2) {
        if (condition) leftBetweenCriteria.put(columnToString(column), value1 + " AND " + value2);
        return this;
    }

    @Override
    public JoinPlusWrapper leftBetween(SFunction<L, ?> column, Object value1, Object value2) {
        leftBetweenCriteria.put(columnToString(column), value1 + " AND " + value2);
        return this;
    }

    @Override
    public JoinPlusWrapper rightBetween(boolean condition, SFunction<R, ?> column, Object value1, Object value2) {
        if (condition) rightBetweenCriteria.put(columnToString(column), value1 + " AND " + value2);
        return this;
    }

    @Override
    public JoinPlusWrapper rightBetween(SFunction<R, ?> column, Object value1, Object value2) {
        rightBetweenCriteria.put(columnToString(column), value1 + " AND " + value2);
        return this;
    }

    @Override
    public JoinPlusWrapper leftOrderByDesc(boolean condition, SFunction<L, ?>... column) {
        if (condition) {
            for (SFunction<L, ?> rsFunction : column) {
                leftOrderByDescCriteria.add(columnToString(rsFunction));
            }
        }
        return this;
    }

    @Override
    public JoinPlusWrapper leftOrderByDesc(SFunction<L, ?>... column) {
        for (SFunction<L, ?> rsFunction : column) {
            leftOrderByDescCriteria.add(columnToString(rsFunction));
        }
        return this;
    }

    @Override
    public JoinPlusWrapper leftOrderByDesc(SFunction<L, ?> column) {
        leftOrderByDescCriteria.add(columnToString(column));
        return this;
    }

    @Override
    public JoinPlusWrapper rightOrderByDesc(boolean condition, SFunction<R, ?>... column) {
        if (condition) {
            for (SFunction<R, ?> rsFunction : column) {
                rightOrderByDescCriteria.add(columnToString(rsFunction));
            }
        }
        return this;
    }

    @Override
    public JoinPlusWrapper rightOrderByDesc(SFunction<R, ?>... column) {
        for (SFunction<R, ?> rsFunction : column) {
            rightOrderByDescCriteria.add(columnToString(rsFunction));
        }
        return this;
    }

    @Override
    public JoinPlusWrapper rightOrderByDesc(SFunction<R, ?> column) {
        rightOrderByDescCriteria.add(columnToString(column));
        return this;
    }

    @Override
    public JoinPlusWrapper leftOrderByAsc(boolean condition, SFunction<L, ?>... column) {
        if (condition) {
            for (SFunction<L, ?> rsFunction : column) {
                leftOrderByAscCriteria.add(columnToString(rsFunction));
            }
        }
        return this;
    }

    @Override
    public JoinPlusWrapper leftOrderByAsc(SFunction<L, ?>... column) {
        for (SFunction<L, ?> rsFunction : column) {
            leftOrderByAscCriteria.add(columnToString(rsFunction));
        }
        return this;
    }

    @Override
    public JoinPlusWrapper leftOrderByAsc(SFunction<L, ?> column) {
        leftOrderByAscCriteria.add(columnToString(column));
        return this;
    }

    @Override
    public JoinPlusWrapper rightOrderByAsc(boolean condition, SFunction<R, ?>... column) {
        if (condition) {
            for (SFunction<R, ?> rsFunction : column) {
                rightOrderByAscCriteria.add(columnToString(rsFunction));
            }
        }
        return this;
    }

    @Override
    public JoinPlusWrapper rightOrderByAsc(SFunction<R, ?>... column) {
        for (SFunction<R, ?> rsFunction : column) {
            rightOrderByAscCriteria.add(columnToString(rsFunction));
        }
        return this;
    }

    @Override
    public JoinPlusWrapper rightOrderByAsc(SFunction<R, ?> column) {
        rightOrderByAscCriteria.add(columnToString(column));
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
