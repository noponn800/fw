package com.rafal.family;

import com.vaadin.flow.spring.annotation.EnableVaadin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableVaadin
public class FamilyApplication {

	public static void main(String[] args) {
		SpringApplication.run(FamilyApplication.class, args);
	}
}
