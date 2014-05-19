package org.gradle;

public class PersonWrapper {
	private final Person person;

	public PersonWrapper(String name) {
		person = new Person(name);
	}

	public String getName() {
		return person.getName();
	}
}
