package com.ticket.bookingservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.ticket.bookingservice.domain.KafkaMessage;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaConsumer {
	
	@Autowired
	BookingService bookingService;
	
	@KafkaListener(topics="booking", groupId="group1")
	public void consumeShowSeatTopic(KafkaMessage message) {
		log.info("Message is: "+message.toString());
		bookingService.handleBooking(message);
	}

}
