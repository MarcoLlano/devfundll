package com.jalasoft.practice;

import com.jalasoft.practice.controller.HelloController;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello!!!");
        SpringApplication.run(Main.class, args);
    }
}
