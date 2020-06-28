package com.booking.bookroom.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class RoomAlreadyBookedException extends RuntimeException {
	private static final long serialVersionUID = 100L;

	public RoomAlreadyBookedException(String message) {
		super(message);
	}
}
