package com.ticket.showservice.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ticket.showservice.domain.ShowSeat;
import com.ticket.showservice.domain.Status;

public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {
	
	@Modifying(flushAutomatically = true)
	@Query("Update ShowSeat seat set seat.status= :status, seat.version= (:version + 1), seat.uniqueId= :uuid where seat.showSeatId = :id and seat.version= :version")
	Integer updateShowSeatStatus(@Param("id") Long id, @Param("version") Integer version, @Param("status") Status status, @Param("uuid") String uuid);
	
	@Transactional
	@Modifying
	@Query("Update ShowSeat seat set seat.status= :status where seat.uniqueId = :uniqueId")
	Integer bookShowSeat(@Param("uniqueId") String uniqueId, @Param("status") Status status);
	
	@Query("SELECT seat FROM ShowSeat seat WHERE seat.uniqueId= :uniqueId")
	Optional<List<ShowSeat>> listShowSeatByUniqueId(@Param("uniqueId") String uniqueId);
}
