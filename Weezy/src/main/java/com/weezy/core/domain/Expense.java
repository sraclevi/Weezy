package com.weezy.core.domain;

import com.weezy.core.events.ExpenseDetails;

public class Expense {

	public ExpenseDetails toExpenseDetails() {
		return new ExpenseDetails();
	}

}
