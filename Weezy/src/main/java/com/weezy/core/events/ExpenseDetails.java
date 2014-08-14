package com.weezy.core.events;

import java.util.UUID;

import org.joda.time.DateTime;

import com.weezy.rest.domain.CashflowFrequency;

public class ExpenseDetails extends CashflowDetails {

	public ExpenseDetails(UUID key, String name, int amount, DateTime from,
			DateTime to, CashflowFrequency frequency) {
		super(key, name, amount, from, to, frequency);
	}

	public ExpenseDetails(UUID key) {
		super(key);
	}

}
