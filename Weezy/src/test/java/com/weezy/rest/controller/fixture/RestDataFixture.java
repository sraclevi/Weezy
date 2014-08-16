package com.weezy.rest.controller.fixture;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Ignore;

import com.weezy.core.events.AllExpensesEvent;
import com.weezy.core.events.ExpenseDetails;
import com.weezy.rest.domain.Expense;

//TODOCUMENT.  Use of test data fixture classes is considered good practice.
/*
 The majority of tests aren't testing data edge cases, they are testing logical flows and
 what happens to a generic set of data.  For these, use a small, standardised set of fixtures.

 For anything more esoteric, create a new fixture in the test class.
 */
@Ignore
public class RestDataFixture {
	public static final String	YUMMY_ITEM	= "yummy1";

	public static AllExpensesEvent allExpenses() {
		List<ExpenseDetails> expenses = new ArrayList<ExpenseDetails>();

		expenses.add(standardExpenseDetails());
		expenses.add(standardExpenseDetails());
		expenses.add(standardExpenseDetails());

		return new AllExpensesEvent(expenses);
	}

	public static Expense standardExpense() {
		Expense expense = new Expense();

		expense.setName(YUMMY_ITEM);

		return expense;
	}

	public static ExpenseDetails customKeyExpenseDetails(UUID key) {
		ExpenseDetails expensedetails = new ExpenseDetails(key);

		expensedetails.setName(YUMMY_ITEM);

		return expensedetails;
	}

	public static ExpenseDetails standardExpenseDetails() {
		return customKeyExpenseDetails(UUID.randomUUID());
	}

	public static String standardExpenseJSON() {
		String json = "{\"name\": \""
				+ YUMMY_ITEM
				+ "\",\"amount\": 98,\"from\": \"2014-08-07\",\"to\": \"2014-08-10\",\"frequency\": \"YEARLY\"}";
		return json;
	}
}
