package com.weezy.core.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

import com.weezy.core.domain.Income;

public class IncomesInMemoryRepository {

	private Map<UUID, Income>	incomes;

	public IncomesInMemoryRepository(Map<UUID, Income> incomes) {
		this.incomes = incomes;
	}

	public Collection<Income> findAll() {
		return Collections.unmodifiableList(new ArrayList<Income>(incomes
				.values()));
	}

	public Income save(Income income) {
		incomes.put(income.getKey(), income);
		return income;
	}

	public Income findById(UUID uuid) {
		return incomes.get(uuid);
	}

	public synchronized void delete(UUID uuid) {
		if (incomes.containsKey(uuid)) {
			incomes.remove(uuid);
		}
	}
}
