package com.ticket.showservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ticket.showservice.domain.Show;
import com.ticket.showservice.service.ShowService;
import com.ticket.showservice.validation.ShowValidator;

@RestController
@RequestMapping("/show")
@Validated
public class ShowController {
	
	@Autowired
	private ShowService showService;
	
	@Autowired
	private ShowValidator showValidator;
	
	@PostMapping("/")
	@ResponseStatus(value = HttpStatus.CREATED)
	public Show createShow(@RequestBody @Valid Show show) {
		return showService.createShow(show);
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(value = HttpStatus.FOUND)
	public Show getShowDetails(@PathVariable Long id) {
		return showService.getShowDetails(id);
	}
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(showValidator);
	}

}
