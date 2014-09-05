package com.weezy.rest.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

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

import com.weezy.core.events.income.IncomeDetails;
import com.weezy.core.events.income.IncomeEvent;
import com.weezy.core.events.income.RequestAllIncomesEvent;
import com.weezy.core.events.income.RequestIncomeEvent;
import com.weezy.core.services.IncomeService;
import com.weezy.rest.domain.CashflowFrequency;
import com.weezy.rest.domain.Income;

@Controller
@RequestMapping("/incomes")
public class IncomeQueriesController {

	private static Logger	LOG	= LoggerFactory
										.getLogger(IncomeQueriesController.class);

	@Autowired
	private IncomeService	incomeService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Income> getAllIncomes() {
		List<Income> incomes = new ArrayList<Income>();
		for (IncomeDetails detail : incomeService.requestAllIncomes(
				new RequestAllIncomesEvent()).getIncomesDetails()) {
			incomes.add(Income.fromIncomeDetails(detail));
		}
		return incomes;
	}

	@RequestMapping(value = "/frequencyEnums", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<CashflowFrequency> getFrequencyEnums() {
		return Arrays.asList(CashflowFrequency.values());
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<Income> viewIncome(@PathVariable String id) {

		IncomeEvent details = incomeService
				.requestIncome(new RequestIncomeEvent(UUID.fromString(id)));

		if (!details.isEntityFound()) {
			return new ResponseEntity<Income>(HttpStatus.NOT_FOUND);
		}

		Income income = Income.fromIncomeDetails(details.getIncomeDetails());

		return new ResponseEntity<Income>(income, HttpStatus.OK);
	}

}
