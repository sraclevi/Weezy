package com.weezy.rest.controller.fixture;

import static com.weezy.rest.controller.fixture.RestDataFixture.customKeyExpenseDetails;

import java.util.UUID;

import org.junit.Ignore;

import com.weezy.core.events.expense.ExpenseCreatedEvent;
import com.weezy.core.events.expense.ExpenseEvent;

@Ignore
public class RestEventFixtures {

	public static ExpenseEvent expenseDetailsNotFound(UUID key) {
		return ExpenseEvent.notFound(key);
	}

	public static ExpenseEvent expenseDetailsEvent(UUID key) {
		return new ExpenseEvent(key, customKeyExpenseDetails(key));
	}

	public static ExpenseCreatedEvent expenseCreated(UUID key) {
		return new ExpenseCreatedEvent(key, customKeyExpenseDetails(key));
	}

}
