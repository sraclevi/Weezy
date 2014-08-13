package com.weezy.core.events;

import java.util.UUID;

public class RequestExpenseEvent {

	private UUID	key;

	public RequestExpenseEvent(UUID key) {
		this.key = key;
	}

	public UUID getKey() {
		return key;
	}

}
