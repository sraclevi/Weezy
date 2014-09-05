package com.weezy.core.repository;

import java.util.Collection;
import java.util.UUID;

import com.weezy.core.domain.Income;

public interface IncomeRepository {

	public Collection<Income> findAll();

	public Income save(Income income);

	public Income findById(UUID uuid);

	public void delete(UUID uuid);

}
