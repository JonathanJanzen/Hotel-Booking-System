package HotelBookingSystem;

import java.time.LocalDate;

/**
 * A class which defines Booking objects, or the specific information regarding a given booking.
 * @author Jonathan Janzen
 *
 */
public class Booking {
	private LocalDate date;
	private String name;
	private String contactNumber;
	
	/**
	 * Constructor for a Booking object.
	 * @param _date Date (Year, month and day) that the booking is for.
	 * @param _name
	 * @param _contactNumber
	 */
	public Booking(LocalDate _date, String _name, String _contactNumber) {
		this.date = _date;
		this.name = _name;
		this.contactNumber = _contactNumber;
	}
	
	/**
	 * Getter for date of booking.
	 * @return LocalDate object with date of the booking.
	 */
	public LocalDate getDate() {
		return this.date;
	}
	
	/**
	 * Getter for the name that the booking is under.
	 * @return String of the individual's name who booked the room.
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Getter for the contact number left for the booking.
	 * @return String of the individual who booked the room's phone number.
	 */
	public String getContactNumber() {
		return this.contactNumber;
	}
}
