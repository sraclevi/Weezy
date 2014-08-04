package com.weezy.rest.domain;

import org.joda.time.DateTime;

public abstract class Cashflow {

	private String				name;
	private int					amount;
	private DateTime			from;
	private DateTime			to;
	private CashflowFrequency	frequency;

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
