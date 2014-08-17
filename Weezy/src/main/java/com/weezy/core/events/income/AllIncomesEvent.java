package com.weezy.core.events.income;

import java.util.Collection;
import java.util.List;

public class AllIncomesEvent {

	private final List<IncomeDetails>	incomeDetails;

	public AllIncomesEvent(List<IncomeDetails> generatedDetails) {
		incomeDetails = generatedDetails;
	}

	public Collection<IncomeDetails> getIncomesDetails() {
		return incomeDetails;
	}

}
