package com.weezy.core.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

import org.joda.time.DateTime;

import com.weezy.core.domain.Expense;

public class ExpensesInMemoryRepository implements ExpenseRepository {

	private Map<UUID, Expense>	expenses;

	public ExpensesInMemoryRepository(Map<UUID, Expense> expenses) {
		this.expenses = expenses;
	}

	@Override
	public Collection<Expense> findAll() {
		return Collections.unmodifiableList(new ArrayList<Expense>(expenses
				.values()));
	}

	@Override
	public Expense save(Expense expense) {
		expenses.put(expense.getKey(), expense);
		return expense;
	}

	@Override
	public Expense findById(UUID uuid) {
		return expenses.get(uuid);
	}

	@Override
	public synchronized void delete(UUID uuid) {
		if (expenses.containsKey(uuid)) {
			expenses.remove(uuid);
		}
	}

	@Override
	public Collection<Integer> findAllAmountForMonth(DateTime month) {
		throw new UnsupportedOperationException(
				"findAllWithMonth is not supported in inmemory test classes");
	}
}
