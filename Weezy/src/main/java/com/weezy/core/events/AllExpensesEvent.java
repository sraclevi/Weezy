package com.weezy.core.events;

import java.util.Collection;
import java.util.List;

public class AllExpensesEvent {

	private final List<ExpenseDetails>	expenseDetails;

	public AllExpensesEvent(List<ExpenseDetails> generatedDetails) {
		expenseDetails = generatedDetails;
	}

	public Collection<ExpenseDetails> getExpensesDetails() {
		return expenseDetails;
	}

}
