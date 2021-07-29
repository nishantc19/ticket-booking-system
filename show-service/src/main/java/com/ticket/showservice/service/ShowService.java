package com.ticket.showservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticket.showservice.domain.Show;
import com.ticket.showservice.exception.ShowNotFoundException;
import com.ticket.showservice.repository.ShowRepository;

@Service
public class ShowService {
	@Autowired
	private ShowRepository showRepository;
	
	public Show createShow(Show show) {
		return showRepository.save(show);
	}
	
	public Show getShowDetails(Long id) {
		Optional<Show> show = showRepository.findById(id);
		if(show.isEmpty()) {
			throw new ShowNotFoundException("Unable to find show for id: "+id);
		}
		return show.get();
		
	}
}
