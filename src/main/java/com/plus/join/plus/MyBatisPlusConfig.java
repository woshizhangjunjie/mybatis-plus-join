package com.plus.join.plus;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisPlusConfig {

    //自定义sql注入器
    @Bean
    public MySqlInjector mySqlInjector(){
        return new MySqlInjector();
    }
}
