package com.weezy.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.weezy.core.domain.Cashflow;
import com.weezy.core.domain.Expense;
import com.weezy.core.domain.Income;
import com.weezy.core.repository.ExpenseHibernateRepository;
import com.weezy.core.repository.ExpenseRepository;
import com.weezy.core.repository.IncomeHibernateRepository;
import com.weezy.core.repository.IncomeRepository;
import com.weezy.core.services.ExpenseService;
import com.weezy.core.services.IncomeService;

@Configuration
@EnableTransactionManagement
public class CoreConfig {

	@Bean
	public ExpenseService createExpenseService(ExpenseRepository repo) {
		return new ExpenseService(repo);
	}

	@Bean
	public ExpenseRepository createExpenseRepo() {
		return new ExpenseHibernateRepository();
	}

	@Bean
	public IncomeService createIncomeService(IncomeRepository repo) {
		return new IncomeService(repo);
	}

	@Bean
	public IncomeRepository createIncomeRepo() {
		return new IncomeHibernateRepository();
	}

	@Bean
	public HibernateTemplate hibernateTemplate() {
		return new HibernateTemplate(sessionFactory());
	}

	@Bean
	public SessionFactory sessionFactory() {
		return new LocalSessionFactoryBuilder(getDataSource())
				.addAnnotatedClasses(Cashflow.class, Expense.class,
						Income.class).buildSessionFactory();
	}

	@Bean
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/testdb");
		dataSource.setUsername("root");
		dataSource.setPassword("mollevi88");

		return dataSource;
	}

	@Bean
	public HibernateTransactionManager transactionManager() {
		return new HibernateTransactionManager(sessionFactory());
	}
}
