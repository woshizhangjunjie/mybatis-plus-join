package com.plus.join;

import com.github.pagehelper.PageHelper;
import com.plus.join.plus.JoinWrapper;
import com.plus.join.plus.constant.JoinType;
import com.plus.join.plus.dao.UserMapper;
import com.plus.join.plus.dao.WalletMapper;
import com.plus.join.plus.entity.User;
import com.plus.join.plus.entity.Wallet;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@SpringBootApplication(scanBasePackages = "com.plus.*")
@MapperScan(basePackages = "com.plus.join.plus.dao")
public class JoinApplication {

    public static void main(String[] args) {
        SpringApplication.run(JoinApplication.class, args);
    }

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private WalletMapper walletMapper;

    @RequestMapping(value = "test", method = RequestMethod.POST)
    public void test() {
        PageHelper.startPage(0,9);//目前只支持pageHelper分页插件
        JoinWrapper<User, Wallet> joinWrapper = new JoinWrapper<>(Wallet.class);//泛型必填 构造器中的类型为被关联表
        joinWrapper.on(User::getId, Wallet::getUserId);//键关联
        joinWrapper.rightBetween(Wallet::getTotal, 2,11);//被关联表的between筛选
        joinWrapper.leftOrderByAsc(User::getId,User::getName);//主表的排序
        List<User> users = userMapper.selectJoin(joinWrapper);
        System.err.println(users);
    }

    @RequestMapping(value = "test2", method = RequestMethod.GET)
    public void test2() {
        JoinWrapper<User, Wallet> joinWrapper = new JoinWrapper<>(Wallet.class, JoinType.LEFT);
        joinWrapper.on(User::getId, Wallet::getUserId);
        joinWrapper.rightLike(Wallet::getTotal, "2");
        joinWrapper.leftNe(User::getName,"a");
        List<Map> maps = userMapper.selectMapJoin(joinWrapper);
        System.err.println(maps);
    }
}
