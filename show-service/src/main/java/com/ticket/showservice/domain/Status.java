package com.ticket.showservice.domain;

import java.util.Arrays;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ticket.showservice.exception.BadSeatStatusException;

public enum Status {
	AVAILABLE,
	RESERVED,
	CONFIRMED;
	
	@JsonCreator
	public static Status deSerialize(String value) {
		return new StatusConverter().convert(value);
	}
	
	@JsonValue
	public String serialize() {
		return this.toString().toLowerCase();
	}
	
	@Component
	private static class StatusConverter implements Converter<String, Status>{
		
		@Override
		public Status convert(String value) {
			return Arrays.stream(Status.values())
					.filter(itr -> itr.toString().equalsIgnoreCase(value))
					.findFirst()
					.orElseThrow(BadSeatStatusException::new);
		}
	}
}
