package com.ticket.showservice.controller;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ticket.showservice.domain.Status;
import com.ticket.showservice.service.ShowSeatService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/showseat")
@RequiredArgsConstructor
public class ShowSeatController {
	
	final ShowSeatService seatService;
	
	@PatchMapping("/{id}/status")
	public String updateSeat(
			@PathVariable final Long id,
			@RequestParam("version") final Integer version,
			@RequestParam("status") final Status status) {
		return seatService.reserveShowSeat(id, version, status);
	}
}
