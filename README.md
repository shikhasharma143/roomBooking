# roomBooking
REST APIs implemented -

List Available Rooms: Lists all available meeting rooms (with id, name etc), given the meeting room type building name, and floor (optional)
/GET /getAvailableRooms
Input parameters-  Building Id, Room Type - 4 seater/ 8 seater and floor Id (mandatory fields)
returns list of available rooms with all information.

Make Booking: Books the meeting room having the specified id. It returns success/failure. If successful, returns a booking reference id.
POST /book
takes parameter roomId	as input and returns room information with booking reference number.
Cancel Booking: Removes booking for specified reference id.
PUT /cancel
Takes parameter bookingReferenceId	and returns the status message of the booking - cancelled successfully or room not found.

other APIs-
GET /getRoomBookingStatus
bookingReferenceId - input parameter
to know the status of room booking.


NOTE: we have used in memory data for this project
