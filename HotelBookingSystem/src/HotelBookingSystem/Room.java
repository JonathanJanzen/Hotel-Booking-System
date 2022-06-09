package HotelBookingSystem;

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
}
