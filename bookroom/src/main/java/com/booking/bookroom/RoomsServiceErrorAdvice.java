package com.booking.bookroom;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.booking.bookroom.exception.RoomAlreadyBookedException;
import com.booking.bookroom.exception.RoomNotFoundException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class RoomsServiceErrorAdvice {
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({ RuntimeException.class })
	public ResponseEntity<String> handleRunTimeException(RuntimeException e) {
		return error(INTERNAL_SERVER_ERROR, e);
	}
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({ RoomNotFoundException.class })
	public ResponseEntity<String> handleNotFoundException(RoomNotFoundException e) {
		return error(NOT_FOUND, e);
	}
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({ RoomAlreadyBookedException.class })
	public ResponseEntity<String> handleRoomsAlreadyBookedServiceException(RoomAlreadyBookedException e) {
		return error(INTERNAL_SERVER_ERROR, e);
	}

	private ResponseEntity<String> error(HttpStatus status, Exception e) {
		return ResponseEntity.status(status).body(e.getMessage());
	}
}