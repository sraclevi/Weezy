package com.weezy.rest.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.weezy.core.events.expense.ExpenseDetails;
import com.weezy.core.events.expense.ExpenseEvent;
import com.weezy.core.events.expense.RequestAllExpensesEvent;
import com.weezy.core.events.expense.RequestExpenseEvent;
import com.weezy.core.services.ExpenseService;
import com.weezy.rest.domain.CashflowFrequency;
import com.weezy.rest.domain.DateTimeUtils;
import com.weezy.rest.domain.Expense;

@Controller
@RequestMapping("/expenses")
public class ExpenseQueriesController {

	private static Logger	LOG	= LoggerFactory
										.getLogger(ExpenseQueriesController.class);

	@Autowired
	private ExpenseService	expenseService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Expense> getAllExpenses() {
		List<Expense> expenses = new ArrayList<Expense>();
		for (ExpenseDetails detail : expenseService.requestAllExpenses(
				new RequestAllExpensesEvent()).getExpensesDetails()) {
			expenses.add(Expense.fromExpenseDetails(detail));
		}
		return expenses;
	}

	@RequestMapping(value = "/frequencyEnums", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<CashflowFrequency> getFrequencyEnums() {
		return Arrays.asList(CashflowFrequency.values());
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<Expense> viewExpense(@PathVariable String id) {

		ExpenseEvent details = expenseService
				.requestExpense(new RequestExpenseEvent(UUID.fromString(id)));

		if (!details.isEntityFound()) {
			return new ResponseEntity<Expense>(HttpStatus.NOT_FOUND);
		}

		Expense expense = Expense.fromExpenseDetails(details
				.getExpenseDetails());

		return new ResponseEntity<Expense>(expense, HttpStatus.OK);
	}

	@RequestMapping(value = "/amountForMonth/{month}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Integer getExpenseAmountForMonth(@PathVariable String month) {
		DateTime dtMonth = DateTimeUtils.parseString(month);
		return expenseService.requestAmountForMonth(dtMonth);
	}

	@RequestMapping(value = "/expenseMonths", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Set<String> getExpenseMonths() {
		Set<DateTime> months = expenseService.getAllExpenseMonths();

		// as it is not a field, we can not annotate it. Maybe worth to wrap
		// into an object
		Set<String> serializedMonths = new HashSet<String>();

		for (DateTime month : months) {
			serializedMonths.add(DateTimeUtils.FORMATTER.print(month));
		}
		return serializedMonths;
	}

}
