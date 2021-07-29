package com.ticket.showservice.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Show {
	
	private static final String START_TIMESTAMP_FUTURE_MESSAGE = "Start timestamp of the show should be in future.";
	private static final String START_TIMESTAMP_NOT_NULL_MESSAGE = "Start timestamp cannot be empty.";
	private static final String END_TIMESTAMP_NOT_NULL_MESSAGE = "End timestamp cannot be empty.";
	private static final String MOVIE_NAME_EMPTY_MESSAGE = "Movie name cannot be empty.";
	private static final String MOVIE_NAME_SIZE_MESSAGE = "Movie name length should not be greater than 100.";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long showId;
	
	@NotNull(message = START_TIMESTAMP_NOT_NULL_MESSAGE)
	@Future(message = START_TIMESTAMP_FUTURE_MESSAGE)
	private LocalDateTime startTimestamp;
	
	@NotNull(message = END_TIMESTAMP_NOT_NULL_MESSAGE)
	private LocalDateTime endTimestamp;
	
	@NotEmpty(message = MOVIE_NAME_EMPTY_MESSAGE)
	@Size(max = 100, message = MOVIE_NAME_SIZE_MESSAGE)
	private String movieName;
	
	@JsonManagedReference
	@OneToMany(mappedBy="show", cascade = CascadeType.ALL)
	private List<@Valid ShowSeat> seats;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endTimestamp == null) ? 0 : endTimestamp.hashCode());
		result = prime * result + ((movieName == null) ? 0 : movieName.hashCode());
		result = prime * result + ((showId == null) ? 0 : showId.hashCode());
		result = prime * result + ((startTimestamp == null) ? 0 : startTimestamp.hashCode());
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
		Show other = (Show) obj;
		if (endTimestamp == null) {
			if (other.endTimestamp != null)
				return false;
		} else if (!endTimestamp.equals(other.endTimestamp))
			return false;
		if (movieName == null) {
			if (other.movieName != null)
				return false;
		} else if (!movieName.equals(other.movieName))
			return false;
		if (showId == null) {
			if (other.showId != null)
				return false;
		} else if (!showId.equals(other.showId))
			return false;
		if (startTimestamp == null) {
			if (other.startTimestamp != null)
				return false;
		} else if (!startTimestamp.equals(other.startTimestamp))
			return false;
		return true;
	}
	
	
}
