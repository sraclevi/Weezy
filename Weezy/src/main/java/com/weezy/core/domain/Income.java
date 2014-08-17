package com.weezy.core.domain;

import org.joda.time.DateTime;

import com.weezy.core.events.income.IncomeDetails;
import com.weezy.rest.domain.CashflowFrequency;

public class Income extends Cashflow {

	public Income(String name, int amount, DateTime from, DateTime to,
			CashflowFrequency frequency) {
		super(name, amount, from, to, frequency);
	}

	public IncomeDetails toIncomeDetails() {
		return new IncomeDetails(key, name, amount, from, to, frequency);
	}

	public static Income fromIncomeDetails(IncomeDetails incomeDetails) {
		Income income = new Income(incomeDetails.getName(),
				incomeDetails.getAmount(), incomeDetails.getFrom(),
				incomeDetails.getTo(), incomeDetails.getFrequency());
		return income;
	}

}
