package com.weezy.core.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;

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

	public Set<DateTime> getAllExpenseMonths() {
		Collection<Expense> expenses = expensesRepository.findAll();
		Set<DateTime> monthsWithExpenses = new HashSet<DateTime>();
		for (Expense expense : expenses) {
			monthsWithExpenses.addAll(getAllMonthsFromExpense(expense));
		}
		return monthsWithExpenses;
	}

	static List<DateTime> getAllMonthsFromExpense(Expense expense) {

		DateTime from = expense.getFrom();
		DateTime to = expense.getTo();

		DateTime actualMonth = new DateTime(from.getYear(),
				from.getMonthOfYear(), 1, from.getHourOfDay(), 0,
				from.getChronology());
		DateTime lastMonth = new DateTime(to.getYear(), to.getMonthOfYear(), 1,
				0, 0);
		lastMonth = lastMonth.plusMonths(1);

		List<DateTime> months = new ArrayList<DateTime>();

		while (actualMonth.isBefore(lastMonth)) {
			months.add(actualMonth);
			actualMonth = actualMonth.plusMonths(1);
		}
		return months;
	}
}
