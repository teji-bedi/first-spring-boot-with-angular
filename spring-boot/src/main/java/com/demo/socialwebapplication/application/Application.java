package com.demo.socialwebapplication.application;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
@EntityScan(basePackages = {
        "com.demo.socialwebapplication.entities"
})
@ComponentScan(basePackages = {
        "com.demo.socialwebapplication.controller",
        "com.demo.socialwebapplication.dao",
        "com.demo.socialwebapplication.service",
        "com.demo.socialwebapplication.application"
})
@EnableJpaRepositories({"com.demo.socialwebapplication.dao"})
public class Application {
    public static void main(String[] args) {
        run(Application.class, args);
    }
}
