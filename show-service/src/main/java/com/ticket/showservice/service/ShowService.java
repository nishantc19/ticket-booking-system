package com.ticket.showservice.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ticket.showservice.domain.Show;
import com.ticket.showservice.exception.ShowNotFoundException;
import com.ticket.showservice.repository.ShowRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShowService {

	private final ShowRepository showRepository;
	
	public Show createShow(final Show show) {
		return showRepository.save(show);
	}
	
	public Show getShowDetails(final Long id) {
		final Optional<Show> show = showRepository.findById(id);
		if(show.isEmpty()) {
			throw new ShowNotFoundException("Unable to find show for id: "+id);
		}
		return show.get();
		
	}
}
