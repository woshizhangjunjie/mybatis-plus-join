package com.plus.join;

import com.plus.join.plus.JoinWrapper;
import com.plus.join.plus.dao.UserMapper;
import com.plus.join.plus.dao.WalletMapper;
import com.plus.join.plus.entity.User;
import com.plus.join.plus.entity.Wallet;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        JoinWrapper<User, Wallet> joinWrapper = new JoinWrapper<>(Wallet.class);
        joinWrapper.on(User::getId, Wallet::getUserId);
        joinWrapper.leftEq(User::getName, "名字");
        List<User> users = userMapper.selectJoin(joinWrapper);
        System.out.println(users);
    }
}
