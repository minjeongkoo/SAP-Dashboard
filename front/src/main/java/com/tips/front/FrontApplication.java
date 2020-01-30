package com.tips.front;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;

@SpringBootApplication
@Controller
public class FrontApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(FrontApplication.class, args);
	}

}