package com.weezy.core.events.expense;

import java.util.UUID;

public class DeleteExpenseEvent {

	private UUID	key;

	public DeleteExpenseEvent(UUID key) {
		this.key = key;
	}

	public UUID getKey() {
		return key;
	}

}
