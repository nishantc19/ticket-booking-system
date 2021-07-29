package com.ticket.showservice.util;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class UniqueIdGenerator {
	
	public String generateUniqueId() {
		return UUID.randomUUID().toString();
	}
}
