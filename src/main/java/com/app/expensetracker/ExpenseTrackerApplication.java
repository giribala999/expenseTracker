package com.app.expensetracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication //(exclude = {DataSourceAutoConfiguration.class })
//@ComponentScan(basePackages = "com.app.*")
//@EntityScan("com.app.*")
@EnableJpaRepositories(basePackages = "com.app.expensetracker.repository")

public class ExpenseTrackerApplication {

	public static void main(String[] args) {

		SpringApplication.run(ExpenseTrackerApplication.class, args);
	}

}