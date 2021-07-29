package com.ticket.showservice.domain;


import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ShowSeat {
	
	private static final String PRICE_NOT_EMPTY_MESSAGE = "Price must be mentioned for show-seat.";
	private static final String SEAT_ID_NOT_EMPTY_MESSAGE = "There must be a seat id associated with show-seat.";
	private static final String PRICE_DIGITS_MESSAGE = "The price must comprise of only digits with maximum 6 integers and 2 digits in decimals. Eg. 350.00";
	private static final String SEAT_ID_DIGITS_MESSAGE = "The seat id must comprise of only digits.";
	private static final String SEAT_ID_POSITIVE_MESSAGE = "The seat id must be a positive number.";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="showSeatId")
	private Long showSeatId;
	
	private Status status = Status.AVAILABLE;
	
	@Digits( integer = 6, fraction = 2, message=PRICE_DIGITS_MESSAGE)
	@NotNull(message=PRICE_NOT_EMPTY_MESSAGE)
	private BigDecimal price;
	
	@NotNull(message=SEAT_ID_NOT_EMPTY_MESSAGE)
	@Digits( integer = 19, fraction = 0, message=SEAT_ID_DIGITS_MESSAGE)
	@Positive(message=SEAT_ID_POSITIVE_MESSAGE)
	private Long seatId;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "showId", nullable = false)
	private Show show;
	
	@Version
	private Integer version;
	
	@Column(nullable = true)
	private String uniqueId;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((seatId == null) ? 0 : seatId.hashCode());
		result = prime * result + ((showSeatId == null) ? 0 : showSeatId.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((uniqueId == null) ? 0 : uniqueId.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShowSeat other = (ShowSeat) obj;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (seatId == null) {
			if (other.seatId != null)
				return false;
		} else if (!seatId.equals(other.seatId))
			return false;
		if (showSeatId == null) {
			if (other.showSeatId != null)
				return false;
		} else if (!showSeatId.equals(other.showSeatId))
			return false;
		if (status != other.status)
			return false;
		if (uniqueId == null) {
			if (other.uniqueId != null)
				return false;
		} else if (!uniqueId.equals(other.uniqueId))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}
	
	
}
