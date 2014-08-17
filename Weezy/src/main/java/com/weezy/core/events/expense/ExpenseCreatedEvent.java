package com.weezy.core.events.expense;

import java.util.UUID;

public class ExpenseCreatedEvent {

	private final ExpenseDetails	expenseDetails;
	private final UUID				newExpenseKey;

	public ExpenseDetails getExpenseDetails() {
		return expenseDetails;
	}

	public ExpenseCreatedEvent(final UUID newExpenseKey,
			final ExpenseDetails expenseDetails) {
		this.newExpenseKey = newExpenseKey;
		this.expenseDetails = expenseDetails;
	}

	public ExpenseDetails getDetails() {
		return expenseDetails;
	}

	public UUID getNewExpenseKey() {
		return newExpenseKey;
	}

}
