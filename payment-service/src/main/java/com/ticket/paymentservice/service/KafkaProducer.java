package com.ticket.paymentservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.ticket.paymentservice.domain.KafkaMessage;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaProducer {
	@Autowired
	private KafkaTemplate<String, KafkaMessage> kafkaTemplate;
	
	public void publishMessage(KafkaMessage message) {
		log.info("Publishing message: "+message.toString());
		kafkaTemplate.send("show-seat", message);
	}
}
