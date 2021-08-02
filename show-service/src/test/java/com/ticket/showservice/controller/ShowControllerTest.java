package com.ticket.showservice.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ticket.showservice.domain.Show;
import com.ticket.showservice.domain.ShowSeat;
import com.ticket.showservice.domain.Status;
import com.ticket.showservice.service.ShowService;
import com.ticket.showservice.validation.ShowValidator;

@WebMvcTest(ShowController.class)
public class ShowControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@MockBean
	ShowService showService;
	
	@MockBean
	ShowValidator showValidator;
	
	@Test
	public void createShow_success() throws Exception {
		ShowSeat seat = new ShowSeat();
		seat.setSeatId(1L);
		seat.setPrice(new BigDecimal(350.00));
		seat.setStatus(Status.AVAILABLE);
		seat.setVersion(0);
		List<ShowSeat> seats = new ArrayList<ShowSeat>();
		seats.add(seat);
		Show show = new Show();
		show.setMovieName("Tere Naam");
		show.setStartTimestamp(LocalDateTime.parse("2021-08-04T10:10:00"));
		show.setEndTimestamp(LocalDateTime.parse("2021-08-04T13:10:00"));
		show.setSeats(seats);
		Mockito.when(showService.createShow(show)).thenReturn(show);
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/show/")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(show));
		mockMvc.perform(mockRequest)
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$", notNullValue()))
			.andExpect(jsonPath("$.movieName", is("Tere Naam")));
		;
	}
}
