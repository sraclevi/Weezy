package com.weezy.config;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.Ignore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.weezy.core.domain.Expense;
import com.weezy.core.domain.Income;
import com.weezy.core.repository.ExpenseRepository;
import com.weezy.core.repository.ExpensesInMemoryRepository;
import com.weezy.core.repository.IncomesInMemoryRepository;
import com.weezy.core.services.ExpenseService;
import com.weezy.core.services.IncomeService;

@Configuration
@EnableTransactionManagement
@Ignore
public class TestConfig {

	@Bean
	public ExpenseService createExpenseService(ExpenseRepository repo) {
		return new ExpenseService(repo);
	}

	@Bean
	public ExpenseRepository createExpenseRepo() {
		Map<UUID, Expense> expenses = new HashMap<UUID, Expense>();
		return new ExpensesInMemoryRepository(expenses);
	}

	@Bean
	public IncomeService createIncomeService(IncomesInMemoryRepository repo) {
		return new IncomeService(repo);
	}

	@Bean
	public IncomesInMemoryRepository createIncomeRepo() {
		Map<UUID, Income> incomes = new HashMap<UUID, Income>();
		return new IncomesInMemoryRepository(incomes);
	}

}
