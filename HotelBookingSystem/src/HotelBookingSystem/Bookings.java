package HotelBookingSystem;

import java.util.ArrayList;

/**
 * A class which defines an object of bookings, maintaining a list of all bookings in order of date 
 * in the interests of efficiency.
 * @author Jonathan Janzen
 *
 */
public class Bookings {
	
	private ArrayList<Booking> bookingsList;
	
	public Bookings() {
		this.bookingsList = new ArrayList<Booking>();
	}

}
