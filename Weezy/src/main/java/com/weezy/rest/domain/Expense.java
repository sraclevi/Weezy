package com.weezy.rest.domain;

import com.weezy.core.events.ExpenseDetails;

public class Expense extends Cashflow {

	public static Expense fromExpenseDetails(ExpenseDetails detail) {
		return new Expense();
	}

	public ExpenseDetails toExpenseDetails() {
		return new ExpenseDetails();
	}

}
