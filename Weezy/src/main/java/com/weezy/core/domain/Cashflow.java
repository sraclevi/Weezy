package com.weezy.core.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.weezy.rest.domain.CashflowFrequency;

@Entity
@DiscriminatorColumn(name = "ref_type")
@Table(name = "cashflow")
public class Cashflow {

	protected Cashflow() {
		key = UUID.randomUUID();
	}

	@Id
	@Column(name = "`key`")
	protected final UUID		key;

	@Column(name = "name")
	protected String			name;

	@Column(name = "amount")
	protected int				amount;

	@Column(name = "`from`")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	protected DateTime			from;

	@Column(name = "`to`")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	protected DateTime			to;

	@Column(name = "frequency")
	@Enumerated(EnumType.STRING)
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
