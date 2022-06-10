package HotelBookingSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.*;

/**
 * A class used for organizing rooms of all possible types. Also used for keeping list of Rooms in order by 
 * number of bookings for efficient organization.
 * @author Jonathan Janzen
 *
 */
public class Rooms {
	
	private ArrayList<ArrayList<Room>> roomsList = new ArrayList<ArrayList<Room>>();
	private String hotelDocFileName;
	private String bookingDocFileName;
	
	/**
	 * Constructor for a new Rooms object which takes two filenames from which to read
	 * and populates them with information about the given hotel.
	 * @param hotelDocFileName String filename/path of the file which contains room numbers
	 * and types for the given hotel.
	 * @param bookingDocFileName String filename/path of the file which contains booking information
	 * (if it exists) for any existing rooms.
	 */
	public Rooms(String _hotelDocFileName, String _bookingDocFileName) {
		this.hotelDocFileName = _hotelDocFileName;
		this.bookingDocFileName = _bookingDocFileName;
		
		//Populate internal data
		populateRoomsList();
		populateBookings();
	}
	
	/**
	 * Function to create a booking for a single day.
	 * @param type Room type that is desired for the booking.
	 * @param date Single date that the booking is to be made for.
	 * @param name Name under which the booking has been made.
	 * @param contactNumber Contact number of the individual booking the room.
	 * @return A boolean outlining whether booking was successful or not.
	 */
	public boolean createBooking(RoomType type, LocalDate date, String name, String contactNumber) {
		//After successful booking creation, save data to local files
		saveData();
		return true;
	}
	
	/**
	 * Function to create a booking for a number of days.
	 * @param type Room type that is desired for the booking.
	 * @param startDate Start date for the booking.
	 * @param endDate End date that the booking is to end on (inclusive).
	 * @param name Name under which the booking has been made.
	 * @param contactNumber Contact number of the individual booking the room.
	 * @return A boolean outlining whether booking was successful or not.
	 */
	public boolean createBooking(RoomType type, LocalDate startDate, LocalDate endDate, String name, String contactNumber) {
		//TODO: must check that ALL dates are free for a given room before calling "createBooking()" in the given Room object
		
		//After successful booking creation, save data to local files
		saveData();
		return true;
	}
	
	/**
	 * Function to save all data to the bookingDocFile. Called after any booking is created.
	 */
	private void saveData() {
		
	}
	
	/**
	 * Function to iterate through the HotelDoc and add a room to the roomsList for each line.
	 * Each line consists of a room number and roomtype (as an integer), separated by a tab.
	 */
	private void populateRoomsList() {
		//Create a new ArrayList for every value of the enumerated class RoomType
		for (int i = 0; i < RoomType.values().length; i++) {
			roomsList.add(i, new ArrayList<Room>());
		}
		
		//Open HotelDoc file
		try {
			File hotelDoc = new File(hotelDocFileName);
			Scanner myScanner = new Scanner(hotelDoc);
			while (myScanner.hasNextLine()) {
				String data = myScanner.nextLine();
				String[] dataSplit = data.split("\t");
				//Create Room object and add it to the list
				Room tempRoom = new Room(Integer.parseInt(dataSplit[0]), RoomType.values()[Integer.parseInt(dataSplit[1])], new Bookings());
				roomsList.get(Integer.parseInt(dataSplit[1])).add(tempRoom);
			}
			myScanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("The file name specified in the config.xml file was not found."
					+ " Please ensure that the file name is accurate and try again.");
		}
	}
	
	/**
	 * Iterate through the BookingsDoc (if it exists) and add a booking for each relevant room. 
	 * If a bookings document does not exist, create one and exit. If it does and is empty, simply exit.
	 */
	private void populateBookings() {
		
	}

}
