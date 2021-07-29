package com.ticket.showservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ticket.showservice.domain.Show;

public interface ShowRepository extends JpaRepository<Show, Long> {
	
}
