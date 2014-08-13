package com.weezy.config;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weezy.rest.domain.Expense;

public class SandBox {
	public static void main(String[] args) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String userDataJSON = "{" + "  \"name\": \"TestExpense\","
					+ "  \"amount\": 98," + "  \"from\": \"2014-08-07\","
					+ "  \"to\": \"2014-08-10\","
					+ "  \"frequency\": \"YEARLY\"" + "}";
			Expense userFromJSON = mapper
					.readValue(userDataJSON, Expense.class);
			System.out.println(userFromJSON);
		} catch (JsonGenerationException e) {
			System.out.println(e);
		} catch (JsonMappingException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
