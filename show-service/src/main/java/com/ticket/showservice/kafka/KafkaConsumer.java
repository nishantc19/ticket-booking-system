package com.ticket.showservice.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.ticket.showservice.service.ShowSeatService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class KafkaConsumer {
	
	@Autowired
	ShowSeatService seatService;
	
	@KafkaListener(topics="show-seat", groupId="group1")
	public void consumeShowSeatTopic(ShowMessage message) {
		log.info("Message is: "+message.toString());
		seatService.bookShowSeat(message);
	}
	
	/*
	 * @KafkaListener(topics="show-seat-rollback", groupId="group1") public void
	 * consumeSeatRollbackTopic(KafkaMessage message) {
	 * log.info("Message is: "+message.toString()); }
	 */

}
