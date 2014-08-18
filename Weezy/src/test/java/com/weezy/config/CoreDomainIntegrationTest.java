package com.weezy.config;

import static junit.framework.TestCase.assertEquals;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.weezy.core.events.expense.AllExpensesEvent;
import com.weezy.core.events.expense.CreateExpenseEvent;
import com.weezy.core.events.expense.ExpenseDetails;
import com.weezy.core.events.expense.RequestAllExpensesEvent;
import com.weezy.core.services.ExpenseService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { CoreConfig.class })
public class CoreDomainIntegrationTest {

	@Autowired
	ExpenseService	expenseService;

	// TODOCUMENT We have already asserted the correctness of the collaboration.
	// This is to check that the wiring in CoreConfig works.
	// We do this by inference.
	@Test
	public void addANewExpenseToTheSystem() {

		CreateExpenseEvent ev = new CreateExpenseEvent(new ExpenseDetails(
				UUID.randomUUID()));

		expenseService.createExpense(ev);

		AllExpensesEvent allExpenses = expenseService
				.requestAllExpenses(new RequestAllExpensesEvent());

		assertEquals(1, allExpenses.getExpensesDetails().size());
	}
}
