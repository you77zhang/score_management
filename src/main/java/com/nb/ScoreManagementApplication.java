package com.nb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.nb.*.mapper")
public class ScoreManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScoreManagementApplication.class, args);
    }

}
