package com.weezy.core.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

import com.weezy.core.domain.Expense;

public class ExpensesInMemoryRepository implements ExpenseRepository {

	private Map<UUID, Expense>	expenses;

	public ExpensesInMemoryRepository(Map<UUID, Expense> expenses) {
		this.expenses = expenses;
	}

	public Collection<Expense> findAll() {
		return Collections.unmodifiableList(new ArrayList<Expense>(expenses
				.values()));
	}

	public Expense save(Expense expense) {
		expenses.put(expense.getKey(), expense);
		return expense;
	}

	public Expense findById(UUID uuid) {
		return expenses.get(uuid);
	}

	public synchronized void delete(UUID uuid) {
		if (expenses.containsKey(uuid)) {
			expenses.remove(uuid);
		}
	}
}
