package com.weezy.rest.controller;

import static com.weezy.rest.controller.fixture.RestDataFixture.YUMMY_ITEM;
import static com.weezy.rest.controller.fixture.RestEventFixtures.expenseDetailsEvent;
import static com.weezy.rest.controller.fixture.RestEventFixtures.expenseDetailsNotFound;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;

import com.weezy.core.events.RequestExpenseEvent;
import com.weezy.core.services.ExpenseService;

public class ViewExpenseIntegrationTest {

	MockMvc						mockMvc;

	@InjectMocks
	ExpenseQueriesController	controller;

	@Mock
	ExpenseService				expenseService;

	UUID						key	= UUID.fromString("f3512d26-72f6-4290-9265-63ad69eccc13");

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

		this.mockMvc = standaloneSetup(controller).setMessageConverters(
				new MappingJackson2HttpMessageConverter()).build();
	}

	@Test
	public void thatViewExpenseUsesHttpNotFound() throws Exception {

		when(expenseService.requestExpense(any(RequestExpenseEvent.class)))
				.thenReturn(expenseDetailsNotFound(key));

		this.mockMvc
				.perform(
						get("/aggregators/expenses/{id}", key.toString())
								.accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isNotFound());
	}

	@Test
	public void thatViewExpenseUsesHttpOK() throws Exception {

		when(expenseService.requestExpense(any(RequestExpenseEvent.class)))
				.thenReturn(expenseDetailsEvent(key));

		this.mockMvc.perform(
				get("/expenses/{id}", key.toString()).accept(
						MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void thatViewExpenseRendersCorrectly() throws Exception {

		when(expenseService.requestExpense(any(RequestExpenseEvent.class)))
				.thenReturn(expenseDetailsEvent(key));

		this.mockMvc
				.perform(
						get("/expenses/{id}", key.toString()).accept(
								MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.name").value(YUMMY_ITEM))
				.andExpect(jsonPath("$.key").value(key.toString()));
	}

}
