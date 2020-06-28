package com.booking.bookroom.service;

import java.util.List;

import com.booking.bookroom.exception.RoomAlreadyBookedException;
import com.booking.bookroom.exception.RoomNotFoundException;
import com.booking.bookroom.model.Room;
import com.booking.bookroom.model.RoomType;

public interface IRoomService {
	List<Room> findAllAvailableRoomsByBuildingId(String buildingName, String floorId, RoomType roomType)  throws RoomNotFoundException;

	List<Room> findAllRooms();

	Room findRoomById(String roomId) throws RoomNotFoundException;

	Room bookRoom(String roomId) throws RoomNotFoundException, RoomAlreadyBookedException;

	Room cancelRoom(String bookingReferenceId) throws RoomNotFoundException;

	Room findRoomByBookingReferenceId(String bookingReferenceId) throws RoomNotFoundException;
}
