package com.weezy.core.events.expense;

public class CreateExpenseEvent {

	private ExpenseDetails	expenseDetails;

	public CreateExpenseEvent(ExpenseDetails expenseDetails) {
		this.expenseDetails = expenseDetails;
	}

	public ExpenseDetails getExpenseDetails() {
		return expenseDetails;
	}

}
