package HotelBookingSystem;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * A class which defines an object of bookings, maintaining a list of all bookings in order of date 
 * in the interests of efficiency when checking availability.
 * @author Jonathan Janzen
 *
 */
public class Bookings {
	
	private ArrayList<Booking> bookingsList;
	
	public Bookings() {
		this.bookingsList = new ArrayList<Booking>();
	}
	
	/**
	 * Creates a booking for the given single day, then ensures that the list of bookings is in order
	 * by date.
	 * @param date LocalDate object, representing the date for the booking to occur.
	 * @return Boolean of 'true' if the booking has been created successfully, and false if not.
	 */
	public boolean createBooking(LocalDate date) {
		//No need to check availability here, as availability has been checked at the Rooms level
		sortBookings();
		return true;
	}
	
	public boolean checkAvailability(LocalDate date) {
		return true;
	}
	
	/**
	 * Function to sort bookingsList by date.
	 */
	private void sortBookings() {
		
	}
}
