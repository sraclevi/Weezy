package com.weezy.config;

import static com.weezy.rest.controller.fixture.RestDataFixture.YUMMY_ITEM;
import static com.weezy.rest.controller.fixture.RestDataFixture.standardExpenseJSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

//TODOCUMENT We have already asserted the correctness of the collaboration.
//This is to check that the wiring in MVCConfig works.
//We do this by inference, via hitting URLs in the system and checking they work as expected
//given a well known infrastructure and system state.
//this is a minimal set, as we've checked the actual behaviour of rendering, http status handling
//and URL mapping separately.

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { TestConfig.class, MVCConfig.class })
public class RestDomainIntegrationTest {

	@Autowired
	WebApplicationContext	wac;

	private MockMvc			mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void addANewExpenseToTheSystem() throws Exception {
		this.mockMvc
				.perform(
						post("/expenses").content(standardExpenseJSON())
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isCreated());

		this.mockMvc
				.perform(get("/expenses").accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("[0]$.name").value(YUMMY_ITEM));

	}
}
