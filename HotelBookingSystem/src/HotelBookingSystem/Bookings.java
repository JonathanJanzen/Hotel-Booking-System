package HotelBookingSystem;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * A class which defines an object of bookings, consisting of an ArrayList of different red-black
 * binary search trees. Each BST in the ArrayList corresponds to an enumerated RoomType (i.e. the 
 * size of the ArrayList is equivalent to the number of different Room Types that can be booked).
 * 
 * Each node in each BST consists of a LocalDate as a Key and an Integer as its corresponding value
 * (the data structure was kept general in the interests of potential future reusability).
 * The value shows how many of that specific room are available on the given date, depending on the
 * room type. Adding a new booking for a 3-day span would mean creating three nodes (if no bookings
 * existed for any of those days already) with value numRoomsByType[RoomType] - 1. If a booking
 * exists already for one of those rooms, the booking can still occur, but the value of the node
 * with an existing booking must decrement by one. If doing so would set the value of that node
 * to a value less than 0, the booking cannot be completed, and the user must be informed.
 * 
 * Red-black binary search trees were chosen due to their insertion and lookup properties. A red-black
 * BST (in general) boasts 2lg(n) search and 2lg(n) insertion complexity in worst-case circumstances. In this
 * particular case, given that an availability check requires k (where k is the number of days the stay
 * is booked for) search operations, this becomes 2*k*lg(n) for searching and inserting into the relevant
 * BST (as insertion also requires a check for capacity).
 * @author Jonathan Janzen
 *
 */
public class Bookings {
	
	private ArrayList<RedBlackBST> bookingsList;
	private ArrayList<Integer> numRoomsByType;
	
	/**
	 * Constructor for a new Bookings() object.
	 */
	public Bookings() {
		this.bookingsList = new ArrayList<RedBlackBST>();
		this.numRoomsByType = new ArrayList<Integer>();
		for (int i = 0; i < RoomType.values().length; i++) {
			numRoomsByType.add(-1);
		}
	}
	
	/**
	 * Function to get the number of rooms of a particular type.
	 * @param type RoomType desired.
	 * @return The number of rooms of the desired type that are potentially available.
	 */
	public int getNumRoomsByType(RoomType type) {
		return numRoomsByType.get(type.getValue());
	}
	
	/**
	 * Sets the number of rooms for a given RoomType.
	 * @param type The RoomType to be changed.
	 * @param n The number of rooms of that type.
	 */
	public void setNumRooms(RoomType type, int n) {
		numRoomsByType.set(type.getValue(), n);
	}
	
	/**
	 * Creates a booking for the given single day, then ensures that the list of bookings is in order
	 * by date.
	 * @param type RoomType that the booking is to be created for.
	 * @param startDate LocalDate object, representing the date for the booking to begin.
	 * @param endDate LocalDate object, representing the date for the booking to end.
	 * @return Boolean of 'true' if the booking has been created successfully, and false if not.
	 */
	public boolean createBooking(RoomType type, LocalDate startDate, LocalDate endDate) {
		//TODO
		return true;
	}
	
	/**
	 * Function to check the availability of a given date
	 * @param type RoomType to check the availability of.
	 * @param date The date on which to check the availability.
	 * @return Number of rooms available of the given type.
	 */
	public int checkAvailability(RoomType type, LocalDate date) {
		//TODO
	}
	
	/**
	 * Function to validate that Bookings are being processed properly using the console.
	 */
	public void printAsString(RoomType type) {
		//TODO
	}
}
