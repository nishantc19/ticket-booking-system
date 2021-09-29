package com.ticket.showservice.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import com.ticket.showservice.domain.Show;
import com.ticket.showservice.domain.ShowSeat;
import com.ticket.showservice.domain.Status;
import com.ticket.showservice.exception.AlreadyModifiedException;
import com.ticket.showservice.kafka.KafkaProducer;
import com.ticket.showservice.util.UniqueIdGenerator;

@DataJpaTest()
@Import({ShowSeatService.class, UniqueIdGenerator.class})
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ShowSeatServiceIntegrationTest {
	
	@Autowired
	private ShowSeatService showSeatService;
	
	@MockBean
	private KafkaProducer kafkaProducer;
	
	@MockBean
	private UniqueIdGenerator uniqueIdGenerator;
	
	@Autowired
	private TestEntityManager entityManager;
	
	private final Show show = new Show();
	
	@BeforeEach
	private void setUp() {
		createShow();
	}
	
	@Test
	public void shouldReserveSeat_withoutConcurrency() {
		final String expectedUid = "UID1234";
		final ShowSeat seat = show.getSeats().get(0);
		Mockito.when(uniqueIdGenerator.generateUniqueId()).thenReturn(expectedUid);
		
		showSeatService.reserveShowSeat(seat.getShowSeatId(), seat.getVersion(), Status.RESERVED);
		entityManager.clear();
		
		final ShowSeat actualUpdatedSeat = entityManager.find(ShowSeat.class, seat.getSeatId());
		
		final ShowSeat expectedUpdatedSeat = seat;
		expectedUpdatedSeat.setVersion(1);
		expectedUpdatedSeat.setStatus(Status.RESERVED);
		expectedUpdatedSeat.setUniqueId(expectedUid);
		
		Assertions.assertEquals(expectedUpdatedSeat, actualUpdatedSeat);
	}
	
	@Test
	public void shouldNotReserveSeat_withConcurrency() throws InterruptedException {
		final String expectedUid = "UID1234";
		final ShowSeat seat = show.getSeats().get(0);
		Mockito.when(uniqueIdGenerator.generateUniqueId()).thenReturn(expectedUid);
		
		showSeatService.reserveShowSeat(seat.getShowSeatId(), seat.getVersion(), Status.RESERVED);
		entityManager.clear();
		
		Assertions.assertThrows(AlreadyModifiedException.class, ()->{
			showSeatService.reserveShowSeat(seat.getShowSeatId(), seat.getVersion(), Status.RESERVED);
		});
		
		
	}
	
	private Show createShow() {
		final ShowSeat seat = new ShowSeat();
		seat.setSeatId(1L);
		seat.setPrice(new BigDecimal("350.00"));
		
		final List<ShowSeat> seats = new ArrayList<ShowSeat>();
		seats.add(seat);
		
		show.setMovieName("Pirates of the caribbean: Dead man tell no tales");
		show.setStartTimestamp(LocalDateTime.parse("2021-11-04T10:10:00"));
		show.setEndTimestamp(LocalDateTime.parse("2021-11-04T13:10:00"));
		show.setSeats(seats);
		
		seat.setShow(show);
		
		return entityManager.persist(show);
	}

}
