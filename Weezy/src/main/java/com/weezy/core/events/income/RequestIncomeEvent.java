package com.weezy.core.events.income;

import java.util.UUID;

public class RequestIncomeEvent {

	private UUID	key;

	public RequestIncomeEvent(UUID key) {
		this.key = key;
	}

	public UUID getKey() {
		return key;
	}

}
