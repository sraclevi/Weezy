package com.weezy.core.events;

import org.joda.time.DateTime;

import com.weezy.rest.domain.CashflowFrequency;

public class CashflowDetails {

	protected String			name;
	protected int				amount;
	protected DateTime			from;
	protected DateTime			to;
	protected CashflowFrequency	frequency;

	public CashflowDetails(String name, int amount, DateTime from, DateTime to,
			CashflowFrequency frequency) {
		super();
		this.name = name;
		this.amount = amount;
		this.from = from;
		this.to = to;
		this.frequency = frequency;
	}

	public String getName() {
		return name;
	}

	public int getAmount() {
		return amount;
	}

	public DateTime getFrom() {
		return from;
	}

	public DateTime getTo() {
		return to;
	}

	public CashflowFrequency getFrequency() {
		return frequency;
	}

}
