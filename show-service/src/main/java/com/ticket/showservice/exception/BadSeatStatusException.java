package com.ticket.showservice.exception;

public class BadSeatStatusException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "Bad value provided for seat status.";
	public BadSeatStatusException() {
		super(MESSAGE);
	}
}
