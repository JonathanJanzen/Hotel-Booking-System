package HotelBookingSystem;

import java.io.File;
import java.time.LocalDate;

import javax.swing.text.Document;
import javax.swing.text.html.parser.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Main class to implement the two use cases requested:
 * 1. Determine if a given room type is available on a given date.
 * 2. Book a room of a given type for a specific date range.
 * @author Jonathan Janzen
 *
 */
public class Main {
	
	private static Bookings hotelBookings;
	
	/**
	 * Main method to be used via command line to execute the functionality requested.
	 * @param args Any command line arguments. None are required to execute the program
	 * correctly.
	 * @throws ParserConfigurationException 
	 */
	public static void main(String[] args) {
		
		horizontalLine();
		System.out.println("Welcome to the console edition of our Hotel Room Booking program.");
		
		horizontalLine();
		
		System.out.println("Data has been added successfully. Please type '1' to check availability on a given date, or "
				+ "type '2' to book a room of a given type for a specific date range.");
		
	}
	
	/**
	 * Prints a horizontal line for console output beautification.
	 */
	private static void horizontalLine() {
		System.out.println("-----------------------------------------------------------------------"
				+ "-------------------------------------------------------------------");
	}
	
	/**
	 * Function to check the availability of a particular RoomType.
	 * @param type RoomType desired.
	 * @param date Date for which to check the room availability.
	 * @return Returns the number of rooms available of a given type on a given date.
	 */
	private int checkRoomAvailability(RoomType type, LocalDate date) {
		//TODO
	}
	
	/**
	 * Function to create a booking for multiple days.
	 * @param type RoomType that is to be booked for.
	 * @param startDate Start date that the booking is for.
	 * @param endDate End date that the booking is for.
	 * @return Returns true if the room was booked successfully, and false otherwise.
	 */
	private boolean createBooking(RoomType type, LocalDate startDate, LocalDate endDate) {
		return hotelBookings.createBooking(type, startDate, endDate);
	}
}
