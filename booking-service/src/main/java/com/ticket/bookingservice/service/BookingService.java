package com.ticket.bookingservice.service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticket.bookingservice.domain.Booking;
import com.ticket.bookingservice.domain.BookingStatus;
import com.ticket.bookingservice.domain.KafkaMessage;
import com.ticket.bookingservice.domain.Ticket;
import com.ticket.bookingservice.repository.BookingRepository;

@Service
public class BookingService {
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private KafkaProducer kafkaProducer;
	
	private Booking createBooking(Booking booking) {
		return bookingRepository.save(booking);
	}
	
	public void handleBooking(KafkaMessage message) {	
		createBooking(createBookingObject(message));
		kafkaProducer.publishTicket(createTicketObject(message));
	}
	
	private Booking createBookingObject(KafkaMessage message) {
		Booking booking = new Booking();
		booking.setBookingTime(LocalDateTime.now());
		booking.setShowId(message.getShowId());
		booking.setStatus(BookingStatus.CONFIRMED);
		booking.setUniqueId(message.getUniqueId());
		booking.setUserId(message.getUserId());
		return booking;
	}
	
	private Ticket createTicketObject(KafkaMessage message) {
		Ticket ticket = new Ticket();
		ticket.setAuditoriumName("B");
		ticket.setMovieName(message.getMovieName());
		ticket.setSeatName(message.getSeatIds().stream().map(Object::toString).collect(Collectors.toList()).toString());
		ticket.setShowDate(message.getShowDate().toString());
		ticket.setShowStartTime(message.getShowStartTime().toString());
		ticket.setTheaterName("INOX");
		return ticket;
	}
}
