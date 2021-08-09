package com.plus.join.plus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.plus.join.plus.JoinWrapper;
import com.plus.join.plus.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper extends BaseMapper<User> {

    List<User> selectJoin(@Param("joinWrapper") JoinWrapper joinWrapper);

    List<Map> selectMapJoin(@Param("joinWrapper") JoinWrapper joinWrapper);
}
