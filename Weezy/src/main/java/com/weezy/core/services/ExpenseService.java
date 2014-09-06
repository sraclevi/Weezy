package com.weezy.core.services;

import java.util.ArrayList;
import java.util.List;

import com.weezy.core.domain.Expense;
import com.weezy.core.events.expense.AllExpensesEvent;
import com.weezy.core.events.expense.CreateExpenseEvent;
import com.weezy.core.events.expense.DeleteExpenseEvent;
import com.weezy.core.events.expense.ExpenseCreatedEvent;
import com.weezy.core.events.expense.ExpenseDeletedEvent;
import com.weezy.core.events.expense.ExpenseDetails;
import com.weezy.core.events.expense.ExpenseEvent;
import com.weezy.core.events.expense.RequestAllExpensesEvent;
import com.weezy.core.events.expense.RequestExpenseEvent;
import com.weezy.core.repository.ExpenseRepository;

public class ExpenseService {

	private final ExpenseRepository	expensesRepository;

	public ExpenseService(ExpenseRepository expensesRepository) {
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

	public ExpenseDeletedEvent deleteExpense(
			DeleteExpenseEvent deleteExpenseEvent) {

		Expense expense = expensesRepository.findById(deleteExpenseEvent
				.getKey());

		if (expense == null) {
			return ExpenseDeletedEvent.notFound(deleteExpenseEvent.getKey());
		}

		// TODOCUMENT This contains some specific domain logic, not exposed to
		// the outside world, and not part of the
		// persistence rules.

		// if (!expense.canBeDeleted()) {
		// return ExpenseDeletedEvent.deletionForbidden(
		// deleteExpenseEvent.getKey(), details);
		// }

		expensesRepository.delete(deleteExpenseEvent.getKey());
		return ExpenseDeletedEvent.deletionCompleted(deleteExpenseEvent
				.getKey());
	}
}
