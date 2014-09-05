package com.weezy.core.services;

import java.util.ArrayList;
import java.util.List;

import com.weezy.core.domain.Income;
import com.weezy.core.events.income.AllIncomesEvent;
import com.weezy.core.events.income.CreateIncomeEvent;
import com.weezy.core.events.income.IncomeCreatedEvent;
import com.weezy.core.events.income.IncomeDetails;
import com.weezy.core.events.income.IncomeEvent;
import com.weezy.core.events.income.RequestAllIncomesEvent;
import com.weezy.core.events.income.RequestIncomeEvent;
import com.weezy.core.repository.IncomeRepository;

public class IncomeService {

	private final IncomeRepository	incomesRepository;

	public IncomeService(IncomeRepository incomesRepository) {
		this.incomesRepository = incomesRepository;
	}

	public AllIncomesEvent requestAllIncomes(
			RequestAllIncomesEvent requestAllIncomesEvent) {
		List<IncomeDetails> generatedDetails = new ArrayList<IncomeDetails>();
		for (Income income : incomesRepository.findAll()) {
			generatedDetails.add(income.toIncomeDetails());
		}
		return new AllIncomesEvent(generatedDetails);
	}

	public IncomeEvent requestIncome(
			RequestIncomeEvent requestIncomeDetailsEvent) {
		Income income = incomesRepository.findById(requestIncomeDetailsEvent
				.getKey());

		if (income == null) {
			return IncomeEvent.notFound(requestIncomeDetailsEvent.getKey());
		}

		return new IncomeEvent(requestIncomeDetailsEvent.getKey(),
				income.toIncomeDetails());
	}

	public IncomeCreatedEvent createIncome(CreateIncomeEvent createIncomeEvent) {

		Income income = Income.fromIncomeDetails(createIncomeEvent
				.getIncomeDetails());

		income = incomesRepository.save(income);

		return new IncomeCreatedEvent(income.getKey(), income.toIncomeDetails());
	}
}
