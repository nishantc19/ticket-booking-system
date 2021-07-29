package com.ticket.bookingservice.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long bookingId;
	
	Long userId;
	
	Long showId;
	
	BookingStatus status;
	
	LocalDateTime bookingTime;
	
	String uniqueId;
}
