package com.weezy.core.services;

import java.util.ArrayList;
import java.util.List;

import com.weezy.core.domain.Expense;
import com.weezy.core.events.AllExpensesEvent;
import com.weezy.core.events.CreateExpenseEvent;
import com.weezy.core.events.ExpenseCreatedEvent;
import com.weezy.core.events.ExpenseDetails;
import com.weezy.core.events.ExpenseEvent;
import com.weezy.core.events.RequestAllExpensesEvent;
import com.weezy.core.events.RequestExpenseEvent;
import com.weezy.core.repository.ExpensesRepository;

public class ExpenseService {

	private final ExpensesRepository	expensesRepository;

	public ExpenseService(ExpensesRepository expensesRepository) {
		this.expensesRepository = expensesRepository;
	}

	public AllExpensesEvent requestAllExpenses(
			RequestAllExpensesEvent requestAllExpensesEvent) {
		List<ExpenseDetails> generatedDetails = new ArrayList<ExpenseDetails>();
		for (Expense expense : expensesRepository.findAll()) {
			generatedDetails.add(expense.toExpenseDetails());
		}
		return new AllExpensesEvent(generatedDetails);
	}

	public ExpenseEvent requestExpense(
			RequestExpenseEvent requestExpenseDetailsEvent) {
		Expense expense = expensesRepository
				.findById(requestExpenseDetailsEvent.getKey());

		if (expense == null) {
			return ExpenseEvent.notFound(requestExpenseDetailsEvent.getKey());
		}

		return new ExpenseEvent(requestExpenseDetailsEvent.getKey(),
				expense.toExpenseDetails());
	}

	public ExpenseCreatedEvent createExpense(
			CreateExpenseEvent createExpenseEvent) {

		Expense expense = Expense.fromExpenseDetails(createExpenseEvent
				.getExpenseDetails());

		expense = expensesRepository.save(expense);

		return new ExpenseCreatedEvent(expense.getKey(),
				expense.toExpenseDetails());
	}
}
