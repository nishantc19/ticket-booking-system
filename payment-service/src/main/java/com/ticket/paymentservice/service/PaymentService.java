package com.ticket.paymentservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticket.paymentservice.domain.KafkaMessage;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PaymentService {
	
	@Autowired
	KafkaProducer kafkaProducer;
	
	public void makePayment(KafkaMessage message) {
		log.info("About to publish message: "+message.toString());
		//Assuming payment is done, Trigger change of seat status to be booked.
		kafkaProducer.publishMessage(message);
	}

}
