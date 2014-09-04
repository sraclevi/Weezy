package com.weezy.rest.functional;

import static com.weezy.rest.controller.fixture.RestDataFixture.YUMMY_ITEM;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

import java.util.Arrays;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.weezy.rest.controller.fixture.RestDataFixture;
import com.weezy.rest.domain.Expense;

/**
 * This integration test can only be processed if the service is running
 * 
 * @author levente
 *
 */
@Ignore
public class ExpenseFunctionalTests {

	@Test
	public void thatExpensesCanBeAddedAndQueried() {

		ResponseEntity<Expense> entity = createExpense();

		String path = entity.getHeaders().getLocation().getPath();

		assertEquals(HttpStatus.CREATED, entity.getStatusCode());
		assertTrue(path.startsWith("/expenses/"));
		Expense expense = entity.getBody();

		System.out.println("The Expense ID is " + expense.getKey());
		System.out.println("The Location is "
				+ entity.getHeaders().getLocation());

		assertEquals(YUMMY_ITEM, expense.getName());
	}

	private ResponseEntity<Expense> createExpense() {
		HttpEntity<String> requestEntity = new HttpEntity<String>(
				RestDataFixture.standardExpenseJSON(), getHeaders());

		RestTemplate template = new RestTemplate();
		return template.postForEntity("http://localhost:8080/expenses",
				requestEntity, Expense.class);
	}

	static HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		return headers;
	}
}
