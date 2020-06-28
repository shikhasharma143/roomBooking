package com.booking.bookroom.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.booking.bookroom.exception.RoomAlreadyBookedException;
import com.booking.bookroom.exception.RoomNotFoundException;
import com.booking.bookroom.model.Room;
import com.booking.bookroom.model.RoomStatus;
import com.booking.bookroom.model.RoomType;
import com.booking.bookroom.service.IRoomService;

@Service
public class RoomServiceImpl implements IRoomService {

	private static List<Room> rooms = new ArrayList<Room>();
	static {
		rooms.add(new Room("A11", "Single Bed Room", RoomType.FOUR_SEATER, "1", RoomStatus.AVAILABLE, null, "A"));
		rooms.add(new Room("A12", "Delux Room", RoomType.FOUR_SEATER, "1", RoomStatus.AVAILABLE, null, "A"));

		rooms.add(new Room("B11", "Delux Room", RoomType.FOUR_SEATER, "1", RoomStatus.AVAILABLE, null, "B"));
		rooms.add(new Room("B21", "Delux Room", RoomType.FOUR_SEATER, "2", RoomStatus.AVAILABLE, null, "B"));
		rooms.add(new Room("B22", "Semi Delux Room", RoomType.FOUR_SEATER, "2", RoomStatus.AVAILABLE, null, "B"));

		rooms.add(new Room("C11", " Delux Room", RoomType.EIGHT_SEATER, "1", RoomStatus.AVAILABLE, null, "C"));
		rooms.add(new Room("C12", "Semi Delux Room", RoomType.EIGHT_SEATER, "1", RoomStatus.AVAILABLE, null, "C"));
		rooms.add(new Room("C13", "Semi Delux Room", RoomType.FOUR_SEATER, "1", RoomStatus.AVAILABLE, null, "C"));
		rooms.add(new Room("C21", "Delux Room", RoomType.EIGHT_SEATER, "2", RoomStatus.AVAILABLE, null, "C"));
		rooms.add(new Room("C22", "Super Delux Room", RoomType.EIGHT_SEATER, "2", RoomStatus.AVAILABLE, null, "C"));
	}

	@Override
	public List<Room> findAllAvailableRoomsByBuildingId(String buildingId, String floorId, RoomType roomType)
			throws RoomNotFoundException {
		List<Room> availableRooms = rooms.stream()
				.filter(r -> r.getBuildingId().equalsIgnoreCase(buildingId) && r.getFloorId().equalsIgnoreCase(floorId)
						&& r.getRoomType().equals(roomType) && r.getRoomStatus() == RoomStatus.AVAILABLE)
				.collect(Collectors.toList());
		if (availableRooms == null || availableRooms.isEmpty()) {
			throw new RoomNotFoundException("Rooms Not Found");

		}
		return availableRooms;
	}

	@Override
	public List<Room> findAllRooms() {
		return rooms;
	}

	@Override
	public Room findRoomById(String roomId) throws RoomNotFoundException {

		List<Room> room = rooms.stream().filter(r -> r.getRoomId().equalsIgnoreCase(roomId))
				.collect(Collectors.toList());
		if (room == null || room.isEmpty())
			throw new RoomNotFoundException("Room Not Found for Room Id " + roomId);
		return room.get(0);
	}

	@Override
	public Room findRoomByBookingReferenceId(String bookingReferenceId) throws RoomNotFoundException {
		List<Room> bookedRooms = rooms.stream()
				.filter(r -> r.getBookingRefId() != null && r.getBookingRefId().equalsIgnoreCase(bookingReferenceId))
				.collect(Collectors.toList());
		if (bookedRooms == null || bookedRooms.isEmpty()) {
			throw new RoomNotFoundException("Room Not Found for booking reference id " + bookingReferenceId);
		}
		return bookedRooms.get(0);
	}

	@Override
	public Room bookRoom(String roomId) throws RoomNotFoundException, RoomAlreadyBookedException {
		Room room = findRoomById(roomId);
		if (room.getRoomStatus() == RoomStatus.AVAILABLE && room.getBookingRefId() == null) {
			room.setBookingRefId("BK-" + room.getRoomId() + "-" + UUID.randomUUID());
			room.setRoomStatus(RoomStatus.BOOKED);
			return room;
		} else {
			throw new RoomAlreadyBookedException("Room already booked");
		}

	}

	@Override
	public Room cancelRoom(String bookingReferenceId) throws RoomNotFoundException {
		Room room = findRoomByBookingReferenceId(bookingReferenceId);
		room.setRoomStatus(RoomStatus.AVAILABLE);
		room.setBookingRefId(null);
		return room;

	}

}
