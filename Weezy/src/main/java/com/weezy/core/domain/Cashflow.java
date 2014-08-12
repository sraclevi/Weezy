package com.weezy.core.domain;

import java.util.UUID;

import org.joda.time.DateTime;

import com.weezy.rest.domain.CashflowFrequency;

public class Cashflow {

	protected final UUID		key;
	protected String			name;
	protected int				amount;
	protected DateTime			from;
	protected DateTime			to;
	protected CashflowFrequency	frequency;

	public Cashflow(String name, int amount, DateTime from, DateTime to,
			CashflowFrequency frequency) {
		this.key = UUID.randomUUID();
		this.name = name;
		this.amount = amount;
		this.from = from;
		this.to = to;
		this.frequency = frequency;
	}

	public UUID getKey() {
		return key;
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
