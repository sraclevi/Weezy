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

import com.weezy.core.events.income.CreateIncomeEvent;
import com.weezy.core.events.income.IncomeCreatedEvent;
import com.weezy.core.services.IncomeService;
import com.weezy.rest.domain.Income;

@Controller
@RequestMapping("/incomes")
public class IncomeCommandsController {

	@Autowired
	private IncomeService	incomeService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Income> createIncome(@RequestBody Income income,
			UriComponentsBuilder builder) {

		IncomeCreatedEvent incomeCreated = incomeService
				.createIncome(new CreateIncomeEvent(income.toIncomeDetails()));

		Income newIncome = Income.fromIncomeDetails(incomeCreated
				.getIncomeDetails());

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/incomes/{id}")
				.buildAndExpand(incomeCreated.getNewIncomeKey().toString())
				.toUri());

		return new ResponseEntity<Income>(newIncome, headers,
				HttpStatus.CREATED);
	}

}
