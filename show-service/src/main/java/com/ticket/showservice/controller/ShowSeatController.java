package com.ticket.showservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ticket.showservice.domain.Status;
import com.ticket.showservice.service.ShowSeatService;

@RestController
@RequestMapping("/showseat")
public class ShowSeatController {
	
	@Autowired
	ShowSeatService seatService;
	
	@PatchMapping("/{id}/status")
	public String updateSeat(
			@PathVariable Long id,
			@RequestParam("version") Integer version,
			@RequestParam("status") Status status) {
		return seatService.reserveShowSeat(id, version, status);
	}
}
