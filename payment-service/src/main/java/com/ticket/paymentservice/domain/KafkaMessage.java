package com.ticket.paymentservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class KafkaMessage {
	private String uniqueId;
	private Long userId;
}
