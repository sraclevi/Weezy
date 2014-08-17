package com.weezy.core.events.income;

import java.util.UUID;

import org.joda.time.DateTime;

import com.weezy.core.events.CashflowDetails;
import com.weezy.rest.domain.CashflowFrequency;

public class IncomeDetails extends CashflowDetails {

	public IncomeDetails(UUID key, String name, int amount, DateTime from,
			DateTime to, CashflowFrequency frequency) {
		super(key, name, amount, from, to, frequency);
	}

	public IncomeDetails(UUID key) {
		super(key);
	}

}
