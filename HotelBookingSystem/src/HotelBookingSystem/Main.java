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
		System.out.println("Data has been added successfully.");
		
		while(true) {
			horizontalLine();
			int checkVal = 0;
			while (checkVal != 1 && checkVal != 2) {
				System.out.println("Please type '1' to check availability on a given date, or type '2' to add a "
						+ "booking over a certain range.");
				try {
					checkVal = Integer.parseInt(input.nextLine());
					if (checkVal != 1 && checkVal != 2) System.out.println("ERROR: Input must be '1' or '2'.");
				} catch (NumberFormatException ex) {
					System.out.println("ERROR: Input must be an integer.");
				}
			}
			System.out.println("Selection successful.");
			
			//Selection successful
			if (checkVal == 1) {
				//Availability check for a specific room type on a given date
				//Get a date to check for availability from the console
				LocalDate checkDate = null;
				while (checkDate == null) {
					horizontalLine();
					System.out.println("Please enter the date to be checked for availability as a string in the"
							+ " form yyyy-mm-dd. For example, 2020-01-08:");
					checkDate = dateFromInput(input);
				}
				
				RoomType checkType = null;
				while (checkType == null) {
					horizontalLine();
					System.out.println("Please enter the RoomType that you wish to check the availability of. Your options"
							+ " and their corresponding numerical values are listed below:");
					for (int i = 0; i < RoomType.values().length; i++) {
						System.out.println(RoomType.values()[i] + ": " + RoomType.values()[i].getValue());
					}
					try {
						checkType = RoomType.values()[Integer.parseInt(input.nextLine())];
					} catch (Exception ex) {
						System.out.println("ERROR: Input value must be an integer that corresponds to one of the RoomTypes listed"
								+ " above.");
					}
				}
				
				//Check availability
				horizontalLine();
				int availabilityCheck = hotelBookings.checkAvailability(checkType, checkDate);
				if (availabilityCheck == 0) {
					System.out.println("Sorry, there are no rooms of type " + checkType + " available on " + checkDate.toString());
				} else {
					System.out.println("There are " + availabilityCheck + " rooms of type " + checkType + " available on " + checkDate.toString());
				}
				
			} else if (checkVal == 2) {
				
			}
		}
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
		return hotelBookings.checkAvailability(type, date);
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
	
	/**
	 * Function to parse a date from the command line. Returns null if input is
	 * invalid.
	 * @param s Scanner to be used to get input.
	 * @return LocalDate object if the input was valid, null otherwise.
	 */
	private static LocalDate dateFromInput(Scanner s) {
		LocalDate checkDate = null;
		try {
			checkDate = LocalDate.parse(s.nextLine());
			return checkDate;
		} catch (Exception ex) {
			System.out.println("An error occured while processing the date requested. Please try again.");
			return null;
		}
	}
}
