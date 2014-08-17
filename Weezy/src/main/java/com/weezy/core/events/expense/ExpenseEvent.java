package com.weezy.core.events.expense;

import java.util.UUID;

public class ExpenseEvent {

	private UUID			key;
	private ExpenseDetails	expenseDetails;
	private boolean			entityFound	= true;

	public boolean isEntityFound() {
		return entityFound;
	}

	private ExpenseEvent(UUID key) {
		this.key = key;
	}

	public ExpenseEvent(UUID key, ExpenseDetails expenseDetails) {
		this.key = key;
		this.expenseDetails = expenseDetails;
	}

	public UUID getKey() {
		return key;
	}

	public static ExpenseEvent notFound(UUID key) {
		ExpenseEvent ev = new ExpenseEvent(key);
		ev.entityFound = false;
		return ev;
	}

	public ExpenseDetails getExpenseDetails() {
		return expenseDetails;
	}

}
