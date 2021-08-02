package com.ticket.showservice.controller;

import javax.validation.Valid;

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

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/show")
@Validated
@RequiredArgsConstructor
public class ShowController {
	
	private final ShowService showService;
	
	private final ShowValidator showValidator;
	
	@PostMapping("/")
	@ResponseStatus(value = HttpStatus.CREATED)
	public Show createShow(@RequestBody @Valid final Show show) {
		return showService.createShow(show);
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(value = HttpStatus.FOUND)
	public Show getShowDetails(@PathVariable final Long id) {
		return showService.getShowDetails(id);
	}
	
	@InitBinder
	private void initBinder(final WebDataBinder binder) {
		binder.setValidator(showValidator);
	}

}
