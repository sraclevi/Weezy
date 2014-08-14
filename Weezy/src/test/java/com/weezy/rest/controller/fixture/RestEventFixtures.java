package com.weezy.rest.controller.fixture;

import static com.weezy.rest.controller.fixture.RestDataFixture.customKeyExpenseDetails;

import java.util.UUID;

import com.weezy.core.events.ExpenseCreatedEvent;
import com.weezy.core.events.ExpenseEvent;

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
