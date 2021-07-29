package com.ticket.showservice.exception;

public class AlreadyModifiedException extends RuntimeException {
	private static final long serialVersionUID = 6681459022351786673L;
	public AlreadyModifiedException(String message) {
		super(message);
	}
}
