package com.weezy.core.events;

import org.joda.time.DateTime;

import com.weezy.rest.domain.CashflowFrequency;
import com.weezy.rest.domain.Expense;

public class ExpenseDetails extends CashflowDetails {

	public ExpenseDetails(String name, int amount, DateTime from, DateTime to,
			CashflowFrequency frequency) {
		super(name, amount, from, to, frequency);
	}

	public static ExpenseDetails fromExpense(Expense expense) {
		return new ExpenseDetails(expense.getName(), expense.getAmount(),
				expense.getFrom(), expense.getTo(), expense.getFrequency());

	}

	public Expense toExpense() {
		return new Expense(name, amount, from, to, frequency);
	}

}
