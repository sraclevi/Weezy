package com.weezy.core.domain;

import org.joda.time.DateTime;

import com.weezy.core.events.expense.ExpenseDetails;
import com.weezy.rest.domain.CashflowFrequency;

public class Expense extends Cashflow {

	public Expense(String name, int amount, DateTime from, DateTime to,
			CashflowFrequency frequency) {
		super(name, amount, from, to, frequency);
	}

	public ExpenseDetails toExpenseDetails() {
		return new ExpenseDetails(key, name, amount, from, to, frequency);
	}

	public static Expense fromExpenseDetails(ExpenseDetails expenseDetails) {
		Expense expense = new Expense(expenseDetails.getName(),
				expenseDetails.getAmount(), expenseDetails.getFrom(),
				expenseDetails.getTo(), expenseDetails.getFrequency());
		return expense;
	}

}
