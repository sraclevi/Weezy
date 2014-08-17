package com.weezy.rest.domain;

import java.util.UUID;

import org.joda.time.DateTime;

import com.weezy.core.events.income.IncomeDetails;

public class Income extends Cashflow {

	public Income() {

	}

	public Income(UUID key, String name, int amount, DateTime from,
			DateTime to, CashflowFrequency frequency) {
		super(key, name, amount, from, to, frequency);
	}

	public static Income fromIncomeDetails(IncomeDetails detail) {
		return new Income(detail.getKey(), detail.getName(),
				detail.getAmount(), detail.getFrom(), detail.getTo(),
				detail.getFrequency());
	}

	public IncomeDetails toIncomeDetails() {
		return new IncomeDetails(key, name, amount, from, to, frequency);
	}

}
