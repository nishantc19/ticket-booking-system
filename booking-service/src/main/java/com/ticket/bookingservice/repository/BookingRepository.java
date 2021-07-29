package com.ticket.bookingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ticket.bookingservice.domain.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

}
