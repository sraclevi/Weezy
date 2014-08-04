package com.weezy.core.services;

import java.util.ArrayList;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.weezy.core.domain.Expense;
import com.weezy.core.events.AllExpensesEvent;
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

	public ExpenseEvent requestExpenseDetails(
			RequestExpenseEvent requestExpenseDetailsEvent) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

}
