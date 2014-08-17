package com.weezy.core.events.income;

public class CreateIncomeEvent {

	private IncomeDetails	incomeDetails;

	public CreateIncomeEvent(IncomeDetails incomeDetails) {
		this.incomeDetails = incomeDetails;
	}

	public IncomeDetails getIncomeDetails() {
		return incomeDetails;
	}

}
