package org.gradle;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PersonWrapperTest {
	@Test
	public void canConstructAPersonWithAName() {
		PersonWrapper person = new PersonWrapper("Larry");
		assertEquals("Larry", person.getName());
	}
}
