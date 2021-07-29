package com.ticket.showservice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticket.showservice.exception.AlreadyModifiedException;
import com.ticket.showservice.kafka.BookingMessage;
import com.ticket.showservice.kafka.KafkaProducer;
import com.ticket.showservice.kafka.ShowMessage;
import com.ticket.showservice.domain.Show;
import com.ticket.showservice.domain.ShowSeat;
import com.ticket.showservice.domain.Status;
import com.ticket.showservice.repository.ShowSeatRepository;
import com.ticket.showservice.util.UniqueIdGenerator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ShowSeatService {
	
	@Autowired
	private ShowSeatRepository seatRepository;
	
	@Autowired
	private KafkaProducer kafkaProducer;
	
	@Autowired
	private UniqueIdGenerator uniqueIdGenerator;
	
	public String reserveShowSeat(Long showSeatId, Integer version, Status status) {
		String uuid = uniqueIdGenerator.generateUniqueId();
		Integer rowsUpdated = seatRepository.updateShowSeatStatus(showSeatId, version, status, uuid);
		if (rowsUpdated == 0)
			throw new AlreadyModifiedException("The seat with id: "+showSeatId+" has been reserved by another customer.");
		return uuid;
		 
	}
	
	public void bookShowSeat(ShowMessage message) {
		Integer rowsUpdated = seatRepository.bookShowSeat(message.getUniqueId(), Status.CONFIRMED);
		if(rowsUpdated == 0) {
			throw new AlreadyModifiedException("The seat with unique id: "+message.getUniqueId()+" has been reserved by another customer.");
			//Trigger payment rollback
		}
		//Trigger create booking
		kafkaProducer.publishMessageToBookingTopic(createBookingMessage(message));
	}
	
	private List<ShowSeat> listShowSeatByUniqueId(String uniqueId) {
		Optional<List<ShowSeat>> seats = seatRepository.listShowSeatByUniqueId(uniqueId);
		if(seats.isEmpty()) {
			//Cannot create booking due to some internal error. Trigger Rollback.
		}
		return seats.get();
	}
	
	private BookingMessage createBookingMessage(ShowMessage kafkaMessage) {
		log.info(">>>>>Getting show seat by uniqueId");
		List<ShowSeat> showSeats = listShowSeatByUniqueId(kafkaMessage.getUniqueId());
		log.info("<<<<<Got show seat by uniqueId");
		Show showDetails = showSeats.get(0).getShow();
		List<Long> seatIds = showSeats.stream().map(ShowSeat::getSeatId).collect(Collectors.toList());
		BookingMessage message = new BookingMessage();
		message.setSeatIds(seatIds);
		message.setShowId(showDetails.getShowId());
		message.setUniqueId(kafkaMessage.getUniqueId());
		message.setUserId(kafkaMessage.getUserId());
		message.setMovieName(showDetails.getMovieName());
		message.setShowDate(showDetails.getStartTimestamp().toLocalDate());
		message.setShowStartTime(showDetails.getStartTimestamp().toLocalTime());
		log.info("Booking message created: "+message.toString());
		return message;
	}
}
