package com.ticket.bookingservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.ticket.bookingservice.domain.Ticket;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaProducer {
	
	@Autowired
	private KafkaTemplate<String, Ticket> kafkaTemplate;
	
	public void publishTicket(Ticket ticket) {
		log.info("Publishing message: "+ticket.toString());
		kafkaTemplate.send("ticket", ticket);
	}
}
