package com.app.expensetracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
		(exclude = {DataSourceAutoConfiguration.class })
public class ExpenseTrackerApplication {

	public static void main(String[] args) {

		// init all your tables here
//		public UserTable = Map<string_id, entity.User>;

//		public CategoryTable = Map<catgeory_id, entity.Category>


		SpringApplication.run(ExpenseTrackerApplication.class, args);
	}

}


