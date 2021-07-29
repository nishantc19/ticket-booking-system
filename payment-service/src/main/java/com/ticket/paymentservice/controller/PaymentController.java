package com.ticket.paymentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ticket.paymentservice.domain.KafkaMessage;
import com.ticket.paymentservice.service.PaymentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {
	
	@Autowired
	PaymentService paymentService;
	
	@PostMapping("/")
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public void makePayment(@RequestBody KafkaMessage ids) {
		log.info("In controller: "+ids.toString());
		paymentService.makePayment(ids);
	}

}
