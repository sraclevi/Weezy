package com.weezy.core.events;

import java.util.UUID;

import org.joda.time.DateTime;

import com.weezy.rest.domain.CashflowFrequency;

public class CashflowDetails {

	protected String			name;
	protected int				amount;
	protected DateTime			from;
	protected DateTime			to;
	protected CashflowFrequency	frequency;
	protected UUID				key;

	public CashflowDetails(UUID key) {
		this.key = key;
	}

	public CashflowDetails(UUID key, String name, int amount, DateTime from,
			DateTime to, CashflowFrequency frequency) {
		super();
		this.key = key;
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

	public UUID getKey() {
		return key;
	}

	public void setKey(UUID key) {
		this.key = key;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void setFrom(DateTime from) {
		this.from = from;
	}

	public void setTo(DateTime to) {
		this.to = to;
	}

	public void setFrequency(CashflowFrequency frequency) {
		this.frequency = frequency;
	}

}
