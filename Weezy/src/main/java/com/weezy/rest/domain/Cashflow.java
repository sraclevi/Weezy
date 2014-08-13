package com.weezy.rest.domain;

import org.joda.time.DateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public abstract class Cashflow {

	protected String			name;
	protected int				amount;

	protected CashflowFrequency	frequency;

	@JsonSerialize(using = CustomDateSerializer.class)
	@JsonDeserialize(using = CustomDateDeserializer.class)
	protected DateTime			from;

	@JsonDeserialize(using = CustomDateDeserializer.class)
	@JsonSerialize(using = CustomDateSerializer.class)
	protected DateTime			to;

	public Cashflow() {

	}

	public Cashflow(String name, int amount, DateTime from, DateTime to,
			CashflowFrequency frequency) {
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

	public void setName(String name) {
		this.name = name;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void setFrequency(CashflowFrequency frequency) {
		this.frequency = frequency;
	}

	public void setFrom(DateTime from) {
		this.from = from;
	}

	public void setTo(DateTime to) {
		this.to = to;
	}

}
