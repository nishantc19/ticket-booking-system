package com.ticket.bookingservice.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import lombok.Data;

@Data
public class KafkaMessage {
	
	Long userId;
	
	String uniqueId;
	
	Long showId;
	
	List<Long> seatIds;
	
	String movieName;
	
	LocalTime showStartTime;
	
	LocalDate showDate;
	
}
