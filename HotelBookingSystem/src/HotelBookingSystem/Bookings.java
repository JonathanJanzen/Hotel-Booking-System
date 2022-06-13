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
 * BST (as insertion also requires a check for capacity). Therefore, both can be considered O(lg n) in complexity.
 * 
 * ASSUMPTION: Rooms are booked based on nights. So, for example, a booking from 2022-03-02 to 2022-03-04 would be two
 * nights, the 2nd and 3rd, meaning that a booking would not be created for the 4th.
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
			bookingsList.add(new RedBlackBST());
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
		if (n < 0) {
			throw new NumberFormatException();
		}
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
		ArrayList<Integer> existingBookings = new ArrayList<Integer>();
		//First: check availability over the entire set of dates
		if (startDate.isEqual(endDate)) return false;
		else if (startDate.isAfter(endDate)) return false;
		
		for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
			Integer checkVal = checkAvailability(type, date);
			//Shortcut exit if any room is unavailable in the series
			if (checkVal == 0) return false;
			//add to existing bookings list to prevent additional searching later
			existingBookings.add(checkVal);
		}
		
		//Next: add or update booking for each day in the series
		int i = 0;
		for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
			//Index bookingsList for the correct BST, then put the new booking information in
			bookingsList.get(type.getValue()).put(date, existingBookings.get(i) - 1);
			i++;
		}
		return true;
	}
	
	/**
	 * Function to check the availability of a given date
	 * @param type RoomType to check the availability of.
	 * @param date The date on which to check the availability.
	 * @return Number of rooms available of the given type.
	 */
	public Integer checkAvailability(RoomType type, LocalDate date) {
		Integer checkVal = (Integer) bookingsList.get(type.getValue()).get(date);
		if (checkVal == null) {
			//Must determine if there are enough rooms of that type to handle a booking in the event that the
			//node does not currently exist
			if (numRoomsByType.get(type.getValue()) > 0) {
				return numRoomsByType.get(type.getValue());
			} else {
				return 0;
			}
		} else {
			return checkVal;
		}
	}
	
	/**
	 * Function to determine the size of the bookings list for a given RoomType. Gives the number of days that have
	 * any bookings.
	 * @param type RoomType to check the number of bookings for.
	 * @return Integer with the number of days booked for the given type.
	 */
	public int getNumBookings(RoomType type) {
		return bookingsList.get(type.getValue()).size();
	}
}
