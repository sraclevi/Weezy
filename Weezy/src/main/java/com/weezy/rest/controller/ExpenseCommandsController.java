package com.weezy.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import com.weezy.core.events.CreateExpenseEvent;
import com.weezy.core.events.ExpenseCreatedEvent;
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

}
