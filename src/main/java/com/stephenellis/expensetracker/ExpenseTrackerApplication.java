package com.stephenellis.expensetracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExpenseTrackerApplication {

    private final static Logger log = LoggerFactory.getLogger(ExpenseTrackerApplication.class);

	public static void main(String[] args) {
        SpringApplication.run(ExpenseTrackerApplication.class, args);

        log.info("Expense Tracker Application started successfully.");
        log.info("Ready to track your expenses!");
	}

}
