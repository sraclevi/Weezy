package com.weezy.core.events.income;

import java.util.UUID;

public class IncomeCreatedEvent {

	private final IncomeDetails	incomeDetails;
	private final UUID			newIncomeKey;

	public IncomeDetails getIncomeDetails() {
		return incomeDetails;
	}

	public IncomeCreatedEvent(final UUID newIncomeKey,
			final IncomeDetails incomeDetails) {
		this.newIncomeKey = newIncomeKey;
		this.incomeDetails = incomeDetails;
	}

	public IncomeDetails getDetails() {
		return incomeDetails;
	}

	public UUID getNewIncomeKey() {
		return newIncomeKey;
	}

}
