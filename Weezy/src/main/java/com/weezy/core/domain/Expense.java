package com.weezy.core.domain;

import org.joda.time.DateTime;

import com.weezy.core.events.ExpenseDetails;
import com.weezy.rest.domain.CashflowFrequency;

public class Expense extends Cashflow {

	public Expense(String name, int amount, DateTime from, DateTime to,
			CashflowFrequency frequency) {
		super(name, amount, from, to, frequency);
	}

	public ExpenseDetails toExpenseDetails() {
		return new ExpenseDetails(name, amount, from, to, frequency);
	}

}
