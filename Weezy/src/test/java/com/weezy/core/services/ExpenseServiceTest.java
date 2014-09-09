package com.weezy.core.services;

import java.util.Collection;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import com.weezy.core.domain.Expense;

public class ExpenseServiceTest {

	@Test
	public void testGetAllMonthsFromExpense() {
		DateTime from = new DateTime(2014, 9, 6, 15, 45);
		DateTime to = new DateTime(2015, 1, 15, 12, 0);
		Expense expense = new Expense(null, 0, from, to, null);

		Collection<DateTime> allExpenseMonthsFromExpense = ExpenseService
				.getAllMonthsFromExpense(expense);
		Assert.assertEquals(5, allExpenseMonthsFromExpense.size());
	}

	@Test
	public void testGetAllMonthsFromExpenseSameMonth() {
		DateTime from = new DateTime(2014, 9, 6, 15, 45);
		DateTime to = new DateTime(2014, 9, 15, 12, 0);
		Expense expense = new Expense(null, 0, from, to, null);

		Collection<DateTime> allExpenseMonthsFromExpense = ExpenseService
				.getAllMonthsFromExpense(expense);
		Assert.assertEquals(1, allExpenseMonthsFromExpense.size());
	}

	@Test
	public void testGetAllMonthsFromExpenseNextMonth() {
		DateTime from = new DateTime(2014, 9, 6, 15, 45);
		DateTime to = new DateTime(2014, 10, 1, 12, 0);
		Expense expense = new Expense(null, 0, from, to, null);

		Collection<DateTime> allExpenseMonthsFromExpense = ExpenseService
				.getAllMonthsFromExpense(expense);
		Assert.assertEquals(2, allExpenseMonthsFromExpense.size());
	}

	@Test
	public void testGetAllMonthsFromExpenseOneMonth() {
		DateTime from = new DateTime(2014, 9, 6, 15, 45);
		DateTime to = new DateTime(2014, 9, 10, 12, 0);
		Expense expense = new Expense(null, 0, from, to, null);

		List<DateTime> allExpenseMonthsFromExpense = ExpenseService
				.getAllMonthsFromExpense(expense);

		DateTime expectedMonth = new DateTime(2014, 9, 1, 15, 0);
		Assert.assertEquals(expectedMonth, allExpenseMonthsFromExpense.get(0));
	}
}
