package com.weezy.core.events;

import com.weezy.rest.domain.Expense;

public class ExpenseDetails {

	public static ExpenseDetails fromExpense(Expense expense) {
		return new ExpenseDetails();

	}

	public Expense toExpense() {
		return new Expense();
	}

}
