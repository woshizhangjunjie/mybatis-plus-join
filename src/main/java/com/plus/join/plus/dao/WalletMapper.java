package com.plus.join.plus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.plus.join.plus.JoinWrapper;
import com.plus.join.plus.entity.Wallet;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface WalletMapper extends BaseMapper<Wallet> {

    List<Wallet> selectJoin(@Param("joinWrapper") JoinWrapper joinWrapper);

    List<Map> selectMapJoin(@Param("joinWrapper") JoinWrapper joinWrapper);
}
