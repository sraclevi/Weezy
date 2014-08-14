package com.weezy.rest.domain;

import java.util.UUID;

import org.joda.time.DateTime;

import com.weezy.core.events.ExpenseDetails;

public class Expense extends Cashflow {

	public Expense() {

	}

	public Expense(UUID key, String name, int amount, DateTime from,
			DateTime to, CashflowFrequency frequency) {
		super(key, name, amount, from, to, frequency);
	}

	public static Expense fromExpenseDetails(ExpenseDetails detail) {
		return new Expense(detail.getKey(), detail.getName(),
				detail.getAmount(), detail.getFrom(), detail.getTo(),
				detail.getFrequency());
	}

	public ExpenseDetails toExpenseDetails() {
		return new ExpenseDetails(key, name, amount, from, to, frequency);
	}

}
