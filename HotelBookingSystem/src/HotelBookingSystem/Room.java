package HotelBookingSystem;

import java.time.LocalDate;

/**
 * A class used to describe a given room, made up of information about the room
 * (including its current bookings).
 * @author Jonathan Janzen
 *
 */

public class Room {
	private final int roomNumber;
	private final RoomType type;
	private Bookings bookings;
	
	/**
	 * Constructor for a new Room object.
	 * @param _roomNumber Room number (i.e. 112)
	 * @param _type Room type. One of the following set: Queen, King, Double, Twin.
	 * @param _bookings Bookings object, consisting of any existing bookings for the room. Read in and defined
	 * in the "Rooms" class.
	 */
	public Room(int _roomNumber, RoomType _type, Bookings _bookings) {
		this.roomNumber = _roomNumber;
		this.type = _type;
		this.bookings = _bookings;
	}
	
	/**
	 * Getter for the room number.
	 * @return Room number as an integer.
	 */
	public int getNumber() {
		return this.roomNumber;
	}
	
	/**
	 * Getter for the room type.
	 * @return Room type as a RoomType enumerated object.
	 */
	public RoomType getType() {
		return this.type;
	}
	
	/**
	 * Getter for the set of bookings for the particular room.
	 * @return Bookings object relevant to the given room.
	 */
	public Bookings getBookings() {
		return this.getBookings();
	}
	
	/**
	 * Creates a booking for the particular room for a single day. This function is called once per day from 'Rooms'.
	 * Individual bookings were done on a per-day basis in the interests of keeping the bookings of rooms somewhat even.
	 * For example, a room with more bookings is less likely to have a particular day free, generally, so this system
	 * prioritizes rooms with less rooms booked when trying to find an available room.
	 * @param type RoomType that
	 * @param date Date that the booking is to be created for.
	 * @param name Name under which the booking has been made.
	 * @param contactNumber Contact number for the individual who made the booking, as a string.
	 * @return Boolean 'true' if the booking was created successfully, 'false' otherwise.
	 */
	public boolean createBooking(LocalDate date, String name, String contactNumber) {
		//No need to check availability of the date here, as availabilty has already been checked at the Rooms level
		return true;
	}
	
	/**
	 * Checks the availability of a particular room on a particular date by going through all bookings. Shortcut exits
	 * if the date of a booking to be checked is beyond the date requested (Bookings are ordered by date).
	 * @param date
	 * @return
	 */
	public boolean checkAvailability(LocalDate date) {
		return true;
	}
}
