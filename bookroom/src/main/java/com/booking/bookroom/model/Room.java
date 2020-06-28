package com.booking.bookroom.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Room {
	private String id;
	private String name;
	private String m_bookingRefId;
	private String buildingId;

	public String getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}

	public String getBookingRefId() {
		return m_bookingRefId;
	}

	public void setBookingRefId(String m_bookingRefId) {
		this.m_bookingRefId = m_bookingRefId;
	}

	public Room(String id, String name, RoomType roomType, String floorId, RoomStatus roomStatus, String bookingRefId,
			String buildingId) {
		this.id = id;
		this.name = name;
		this.floorId = floorId;
		this.roomStatus = roomStatus;
		this.roomType = roomType;
		this.m_bookingRefId = bookingRefId;
		this.buildingId = buildingId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String floorId;
	private RoomType roomType;
	private RoomStatus roomStatus = RoomStatus.AVAILABLE;

	public RoomStatus getRoomStatus() {
		return roomStatus;
	}

	public void setRoomStatus(RoomStatus roomStatus) {
		this.roomStatus = roomStatus;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	public String getRoomId() {
		return id;
	}

	public void setRoomId(String roomId) {
		this.id = roomId;
	}

	public String getFloorId() {
		return floorId;
	}

	public void setFloorId(String floorId) {
		this.floorId = floorId;
	}

}
