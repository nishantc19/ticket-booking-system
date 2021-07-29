package com.ticket.showservice.kafka;

import lombok.Data;

@Data
public class ShowMessage {
	
	private String uniqueId;
	private Long userId;

}
