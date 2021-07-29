package com.ticket.bookingservice.domain;


import lombok.Data;

@Data
public class Ticket {
	
	String theaterName;
	
	String auditoriumName;
	
	String seatName;
	
	String movieName;
	
	String showStartTime;
	
	String showDate;

}
