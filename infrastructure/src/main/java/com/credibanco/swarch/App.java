package com.credibanco.swarch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.credibanco.swarch")
public class App
{
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}
