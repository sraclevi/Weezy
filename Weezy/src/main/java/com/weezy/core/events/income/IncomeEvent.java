package com.weezy.core.events.income;

import java.util.UUID;

public class IncomeEvent {

	private UUID			key;
	private IncomeDetails	incomeDetails;
	private boolean			entityFound	= true;

	public boolean isEntityFound() {
		return entityFound;
	}

	private IncomeEvent(UUID key) {
		this.key = key;
	}

	public IncomeEvent(UUID key, IncomeDetails incomeDetails) {
		this.key = key;
		this.incomeDetails = incomeDetails;
	}

	public UUID getKey() {
		return key;
	}

	public static IncomeEvent notFound(UUID key) {
		IncomeEvent ev = new IncomeEvent(key);
		ev.entityFound = false;
		return ev;
	}

	public IncomeDetails getIncomeDetails() {
		return incomeDetails;
	}

}
