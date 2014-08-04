package com.weezy.config;

import java.util.HashMap;
import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.weezy.core.domain.Expense;
import com.weezy.core.repository.ExpensesRepository;
import com.weezy.core.services.ExpenseService;

@Configuration
public class CoreConfig {

	@Bean
	public ExpenseService createService(ExpensesRepository repo) {
		return new ExpenseService(repo);
	}

	@Bean
	public ExpensesRepository createRepo() {
		return new ExpensesRepository(new HashMap<UUID, Expense>());
	}

}
