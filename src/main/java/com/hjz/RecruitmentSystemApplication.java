package com.hjz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hjz.dao")
public class RecruitmentSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecruitmentSystemApplication.class, args);
        System.out.println("招聘管理系统启动成功！");
    }

}
