package com.ticket.showservice.kafka;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class BookingMessage extends ShowMessage {
	Long showId;
	
	List<Long> seatIds;
	
	LocalTime showStartTime;
	
	LocalDate showDate;
	
	String movieName;
}
