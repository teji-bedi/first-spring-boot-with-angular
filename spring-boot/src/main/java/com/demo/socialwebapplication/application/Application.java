package com.demo.socialwebapplication.application;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
@ComponentScan(basePackages = {
		"com.demo.socialwebapplication.service",
		"com.demo.socialwebapplication.controller",
		"com.demo.socialwebapplication.dao",
		"com.demo.socialwebapplication.application",
})
public class Application {
	public static void main(String[] args) {
		run(Application.class, args);
	}
}
