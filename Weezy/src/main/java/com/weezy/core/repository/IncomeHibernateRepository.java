package com.weezy.core.repository;

import java.util.Collection;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.weezy.core.domain.Income;

@Transactional
public class IncomeHibernateRepository implements IncomeRepository {

	@Autowired
	private HibernateTemplate	hibernateTemplate;

	public Income save(Income income) {
		hibernateTemplate.save(income);
		return income;
	}

	public Collection<Income> findAll() {
		return hibernateTemplate.loadAll(Income.class);
	}

	public Income findById(UUID uuid) {
		return hibernateTemplate.load(Income.class, uuid);
	}

	public synchronized void delete(UUID uuid) {
		throw new UnsupportedOperationException(
				"Delete action is not supported in hibernate cashflow repository.");
	}

}
