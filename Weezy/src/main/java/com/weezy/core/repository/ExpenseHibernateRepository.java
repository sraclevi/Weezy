package com.weezy.core.repository;

import java.util.Collection;
import java.util.UUID;

import javax.transaction.Transactional;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.weezy.core.domain.Expense;

@Transactional
public class ExpenseHibernateRepository implements ExpenseRepository {

	private static String		SELECT_FOR_MONTH	= "SELECT e.amount FROM Expense e WHERE e.from <= ? and e.to >= ?";

	@Autowired
	private HibernateTemplate	hibernateTemplate;

	@Override
	public Expense save(Expense expense) {
		hibernateTemplate.save(expense);
		return expense;
	}

	@Override
	public Collection<Expense> findAll() {
		return hibernateTemplate.loadAll(Expense.class);
	}

	@Override
	public Expense findById(UUID uuid) {
		return hibernateTemplate.load(Expense.class, uuid);
	}

	@Override
	public synchronized void delete(UUID uuid) {
		Expense expense = hibernateTemplate.load(Expense.class, uuid);
		hibernateTemplate.delete(expense);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Integer> findAllAmountForMonth(DateTime monthStart) {
		return (Collection<Integer>) hibernateTemplate.find(SELECT_FOR_MONTH,
				monthStart, monthStart);
	}
}
