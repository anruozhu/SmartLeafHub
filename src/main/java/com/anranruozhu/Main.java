package com.anranruozhu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.context.annotation.ApplicationScope;

@SpringBootApplication
@ServletComponentScan
public class Main {
    //项目启动
    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }
}