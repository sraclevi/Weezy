package com.weezy.rest.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import com.weezy.core.events.expense.CreateExpenseEvent;
import com.weezy.core.events.expense.DeleteExpenseEvent;
import com.weezy.core.events.expense.ExpenseCreatedEvent;
import com.weezy.core.events.expense.ExpenseDeletedEvent;
import com.weezy.core.services.ExpenseService;
import com.weezy.rest.domain.Expense;

@Controller
@RequestMapping("/expenses")
public class ExpenseCommandsController {

	@Autowired
	private ExpenseService	expenseService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Expense> createExpense(@RequestBody Expense expense,
			UriComponentsBuilder builder) {

		ExpenseCreatedEvent expenseCreated = expenseService
				.createExpense(new CreateExpenseEvent(expense
						.toExpenseDetails()));

		Expense newExpense = Expense.fromExpenseDetails(expenseCreated
				.getExpenseDetails());

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/expenses/{id}")
				.buildAndExpand(expenseCreated.getNewExpenseKey().toString())
				.toUri());

		return new ResponseEntity<Expense>(newExpense, headers,
				HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<Expense> deleteExpense(@PathVariable String id) {

		ExpenseDeletedEvent expenseDeleted = expenseService
				.deleteExpense(new DeleteExpenseEvent(UUID.fromString(id)));

		if (!expenseDeleted.isEntityFound()) {
			return new ResponseEntity<Expense>(HttpStatus.NOT_FOUND);
		}

		if (expenseDeleted.isDeletionCompleted()) {
			return new ResponseEntity<Expense>(HttpStatus.OK);
		}

		return new ResponseEntity<Expense>(HttpStatus.FORBIDDEN);
	}

}
