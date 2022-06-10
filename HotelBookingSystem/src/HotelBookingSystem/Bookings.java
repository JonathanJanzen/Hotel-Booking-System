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
	public boolean createBooking(LocalDate date, String name, String contactNumber) {
		//No need to check availability here, as availability has been checked at the Rooms level
		Booking newBooking = new Booking(date, name, contactNumber);
		bookingsList.add(newBooking);
		
		//Sort bookings list before returning
		sortBookings();
		return true;
	}
	
	/**
	 * Function to check the availability of a given date
	 * @param date
	 * @return
	 */
	public boolean checkAvailability(LocalDate date) {
		//TODO
		return true;
	}
	
	/**
	 * Function to check the availability of a given date range.
	 * @param startDate Start date to check.
	 * @param endDate End date to check.
	 * @return Boolean true if section is available, false otherwise.
	 */
	public boolean checkAvailability(LocalDate startDate, LocalDate endDate) {
		//TODO
		return true;
	}
	
	/**
	 * Function to sort bookingsList by date.
	 */
	private void sortBookings() {
		algorithms.BookingsMergesort.sortByDate(bookingsList);
	}
	
	/**
	 * Function to validate that Bookings are being processed properly using the console.
	 */
	public void printAsString() {
		for (int i = 0; i < bookingsList.size(); i++) {
			System.out.println("Booking #" + i + "under " + bookingsList.get(i).getName() + " for date " + bookingsList.get(i).getDate().toString());
		}
	}
}
