package com.weezy.core.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.weezy.core.domain.Expense;

public class ExpensesRepository {

	private Map<UUID, Expense>	expenses;

	public ExpensesRepository(HashMap<UUID, Expense> expenses) {
		this.expenses = expenses;
	}

	public Collection<Expense> findAll() {
		expenses.put(UUID.randomUUID(), new Expense());
		return Collections.unmodifiableList(new ArrayList<Expense>(expenses
				.values()));
	}

}
