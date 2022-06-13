package HotelBookingSystem;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * Main class to implement the two use cases requested:
 * 1. Determine if a given room type is available on a given date.
 * 2. Book a room of a given type for a specific date range.
 * @author Jonathan Janzen
 *
 */
public class Main {
	
	private static Bookings hotelBookings = new Bookings();
	
	/**
	 * Main method to be used via command line to execute the functionality requested.
	 * @param args Any command line arguments. None are required to execute the program
	 * correctly.
	 * @throws ParserConfigurationException 
	 */
	public static void main(String[] args) {
		
		horizontalLine();
		System.out.println("Welcome to the console edition of our Hotel Room Booking program.");
		System.out.println("The program will now request the number of rooms of each type that are present. Please "
				+ "enter the correct number in the console window when asked.");
		
		Scanner input = new Scanner(System.in);
		//Iterate through all RoomTypes and take input for the number of each at the hotel
		for (int i = 0; i < RoomType.values().length; i++) {
			RoomType type = RoomType.values()[i];
			while (hotelBookings.getNumRoomsByType(type) == -1) {
				System.out.println("Please enter the number of rooms of type " + RoomType.values()[i] + ":");
				try {
					int num = Integer.parseInt(input.nextLine());
					hotelBookings.setNumRooms(type, num);
				} catch (NumberFormatException ex) {
					System.out.println("That was an incorrect input. Please try again.");
				}
			}
		}	
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
