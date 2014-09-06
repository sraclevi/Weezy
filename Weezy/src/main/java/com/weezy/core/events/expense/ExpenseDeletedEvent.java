package com.weezy.core.events.expense;

import java.util.UUID;

public class ExpenseDeletedEvent {

	private UUID	key;
	private boolean	deletionCompleted;
	private boolean	entityFound;

	private ExpenseDeletedEvent(UUID key) {
		this.key = key;
	}

	public UUID getKey() {
		return key;
	}

	public boolean isDeletionCompleted() {
		return deletionCompleted;
	}

	public static ExpenseDeletedEvent deletionCompleted(UUID key) {
		ExpenseDeletedEvent ev = new ExpenseDeletedEvent(key);
		ev.entityFound = true;
		ev.deletionCompleted = true;
		return ev;
	}

	public static ExpenseDeletedEvent deletionForbidden(UUID key) {
		ExpenseDeletedEvent ev = new ExpenseDeletedEvent(key);
		ev.entityFound = true;
		ev.deletionCompleted = false;
		return ev;
	}

	public static ExpenseDeletedEvent notFound(UUID key) {
		ExpenseDeletedEvent ev = new ExpenseDeletedEvent(key);
		ev.entityFound = false;
		return ev;
	}

	public boolean isEntityFound() {
		return entityFound;
	}

}
