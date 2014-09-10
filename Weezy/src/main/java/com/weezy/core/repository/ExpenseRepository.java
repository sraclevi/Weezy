package com.weezy.core.repository;

import java.util.Collection;
import java.util.UUID;

import org.joda.time.DateTime;

import com.weezy.core.domain.Expense;

public interface ExpenseRepository {

	public Collection<Expense> findAll();

	public Expense save(Expense expense);

	public Expense findById(UUID uuid);

	public void delete(UUID uuid);

	public Collection<Integer> findAllAmountForMonth(DateTime month);

}
