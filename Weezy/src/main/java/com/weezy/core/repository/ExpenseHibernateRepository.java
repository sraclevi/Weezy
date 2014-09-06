package com.weezy.core.repository;

import java.util.Collection;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.weezy.core.domain.Expense;

@Transactional
public class ExpenseHibernateRepository implements ExpenseRepository {

	@Autowired
	private HibernateTemplate	hibernateTemplate;

	public Expense save(Expense expense) {
		hibernateTemplate.save(expense);
		return expense;
	}

	public Collection<Expense> findAll() {
		return hibernateTemplate.loadAll(Expense.class);
	}

	public Expense findById(UUID uuid) {
		return hibernateTemplate.load(Expense.class, uuid);
	}

	public synchronized void delete(UUID uuid) {
		Expense expense = hibernateTemplate.load(Expense.class, uuid);
		hibernateTemplate.delete(expense);
	}

}
