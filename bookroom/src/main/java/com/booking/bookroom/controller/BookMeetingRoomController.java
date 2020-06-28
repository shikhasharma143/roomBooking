package com.booking.bookroom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.booking.bookroom.exception.RoomAlreadyBookedException;
import com.booking.bookroom.exception.RoomNotFoundException;
import com.booking.bookroom.model.Room;
import com.booking.bookroom.model.RoomType;
import com.booking.bookroom.service.IRoomService;

import io.swagger.annotations.ApiOperation;

@RestController
public class BookMeetingRoomController {

	@Autowired
	private IRoomService roomService;

	@GetMapping("/getAvailableRooms")
	public ResponseEntity<List<Room>> getAvailableRooms(@RequestParam("buildingId") String buildingId,
			@RequestParam("floorId") String floorId, @RequestParam("roomType") RoomType roomType)
			throws RoomNotFoundException {
		return new ResponseEntity<List<Room>>(
				roomService.findAllAvailableRoomsByBuildingId(buildingId, floorId, roomType), HttpStatus.OK);

	}

	@GetMapping("/getRoomBookingStatus")
	public ResponseEntity<Room> getRoomBookingStatus(@RequestParam("bookingReferenceId") String bookingReferenceId)
			throws RoomNotFoundException {
		Room room = roomService.findRoomByBookingReferenceId(bookingReferenceId);
		return new ResponseEntity<Room>(room, HttpStatus.OK);

	}

	@RequestMapping("/rooms/{roomId}")
	public ResponseEntity<Room> findRoom(@PathVariable String roomId) throws RoomNotFoundException {
		Room room = roomService.findRoomById(roomId);
		return new ResponseEntity<Room>(room, HttpStatus.OK);
	}

	@ApiOperation(value = "Book new Room")
	@RequestMapping(value = "/book", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Room> bookRoom(@RequestParam String roomId)
			throws RoomNotFoundException, RoomAlreadyBookedException {
		Room room = roomService.bookRoom(roomId);
		return new ResponseEntity<Room>(room, HttpStatus.OK);
	}

	@ApiOperation(value = "Cancel Room")
	@RequestMapping(value = "/cancel", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity cancelRoom(@RequestParam String bookingReferenceId) throws RoomNotFoundException {
		Room room = roomService.cancelRoom(bookingReferenceId);
		return new ResponseEntity<>("Room Cancelled Successfully", HttpStatus.OK);

	}
}
