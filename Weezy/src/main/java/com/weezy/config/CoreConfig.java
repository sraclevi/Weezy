package com.weezy.config;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.weezy.core.domain.Expense;
import com.weezy.core.domain.Income;
import com.weezy.core.repository.ExpensesRepository;
import com.weezy.core.repository.IncomesRepository;
import com.weezy.core.services.ExpenseService;
import com.weezy.core.services.IncomeService;

@Configuration
public class CoreConfig {

	@Bean
	public ExpenseService createExpenseService(ExpensesRepository repo) {
		return new ExpenseService(repo);
	}

	@Bean
	public ExpensesRepository createExpenseRepo() {
		Map<UUID, Expense> expenses = new HashMap<UUID, Expense>();
		return new ExpensesRepository(expenses);
	}

	@Bean
	public IncomeService createIncomeService(IncomesRepository repo) {
		return new IncomeService(repo);
	}

	@Bean
	public IncomesRepository createIncomeRepo() {
		Map<UUID, Income> incomes = new HashMap<UUID, Income>();
		return new IncomesRepository(incomes);
	}
}
