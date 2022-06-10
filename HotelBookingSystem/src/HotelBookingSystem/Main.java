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
	
	private static Rooms hotelRooms;
	
	/**
	 * Main method to be used via command line to execute the functionality requested.
	 * @param args Any command line arguments. None are required to execute the program
	 * correctly.
	 * @throws ParserConfigurationException 
	 */
	public static void main(String[] args) {
		
		horizontalLine();
		System.out.println("Welcome to the console edition of our Hotel Room Booking program. "
				+ "Please wait while we restore any previous data before proceeding...");
		
		String HotelDocFileName = null, BookingDocFileName = null;
		//Read from config file to determine file name to read for Rooms.
		try {
			File configFile = new File("src/config.xml");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			org.w3c.dom.Document doc = builder.parse(configFile);
			
			NodeList HotelDocNodeList = doc.getElementsByTagName("HotelRoomsFileName");
			Node HotelDocNode = HotelDocNodeList.item(0);
			HotelDocFileName = HotelDocNode.getFirstChild().getNodeValue();
			
			NodeList BookingDocNodeList = doc.getElementsByTagName("RoomBookingsFileName");
			Node BookingDocNode = BookingDocNodeList.item(0);
			BookingDocFileName = BookingDocNode.getFirstChild().getNodeValue();
		}
		catch (Exception e) {
			System.out.println("An error has occurred in reading 'config.xml'. Please "
					+ "ensure that the file exists and is present within the 'src' directory.");
			System.out.println(e);
		}
		
		//Create Rooms object using the two filenames specified in the config.xml file.
		//All parsing of these files occurs within the constructor for the "Rooms" object.
		hotelRooms = new Rooms(HotelDocFileName, BookingDocFileName);
		
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
	 * @param date Date to check for availability of the particular RoomType.
	 * @return Returns true if a room is available on that date of that type, and false otherwise.
	 */
	private boolean checkRoomAvailability(RoomType type, LocalDate date) {
		return hotelRooms.checkRoomAvailability(type, date) == null ? false : true;
	}
	
	/**
	 * Function to create a booking for a single day.
	 * @param type RoomType that is to be booked for.
	 * @param date Single date that the booking is for.
	 * @param name Name of the individual who has booked the room.
	 * @param contactNumber Contact number of the individual who has booked the room.
	 * @return Returns true if the room was booked successfully, and false otherwise.
	 */
	private boolean createBooking(RoomType type, LocalDate date, String name, String contactNumber) {
		return hotelRooms.createBooking(type, date, name, contactNumber);
	}
	
	/**
	 * Function to create a booking for multiple days.
	 * @param type RoomType that is to be booked for.
	 * @param startDate Start date that the booking is for.
	 * @param endDate End date that the booking is for.
	 * @param name Name of the individual who has booked the room.
	 * @param contactNumber Contact number of the individual who has booked the room.
	 * @return Returns true if the room was booked successfully, and false otherwise.
	 */
	private boolean createBooking(RoomType type, LocalDate startDate, LocalDate endDate, String name, String contactNumber) {
		return hotelRooms.createBooking(type, startDate, endDate, name, contactNumber);
	}
}
