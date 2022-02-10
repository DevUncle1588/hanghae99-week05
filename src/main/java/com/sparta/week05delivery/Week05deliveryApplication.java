package com.sparta.week05delivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class Week05deliveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(Week05deliveryApplication.class, args);
    }

}
