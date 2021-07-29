package com.ticket.showservice.validation;

import java.time.Duration;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ticket.showservice.domain.Show;

@Component
public class ShowValidator implements Validator {
	
	private static final Duration MAX_SHOW_DURATION = Duration.ofHours(6);
	private static final String SHOW_TIME_EXCEEDED_ERROR_MESSAGE = "The show end time should be within 6hrs of show start time.";
	private static final String SHOW_END_TIME_BEFORE_ERROR_MESSAGE = "The show end time should be after show start time.";
	private static final String SHOW_SEATS_NULL_ERROR_MESSAGE = "Seats cannot be null.";
	private static final String SHOW_SEATS_EMPTY_ERROR_MESSAGE = "Seats cannot be empty.";
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Show.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Show show = (Show) target;
		long showDurationInMillis = Duration.between(show.getStartTimestamp(), show.getEndTimestamp()).toMillis();
		if(showDurationInMillis < 0) {
			errors.rejectValue("endTimestamp", "ST101", SHOW_END_TIME_BEFORE_ERROR_MESSAGE);
		} else if(showDurationInMillis > MAX_SHOW_DURATION.toMillis()) {
			errors.rejectValue("endTimestamp", "ST102", SHOW_TIME_EXCEEDED_ERROR_MESSAGE);
		} else if(null == show.getSeats()) {
			errors.rejectValue("seats", "SS101", SHOW_SEATS_NULL_ERROR_MESSAGE);
		} else if(show.getSeats().isEmpty()) {
			errors.rejectValue("seats", "SS102", SHOW_SEATS_EMPTY_ERROR_MESSAGE);
		}
	}
	
}
