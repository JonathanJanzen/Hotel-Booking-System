package unitTests;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import HotelBookingSystem.Bookings;
import HotelBookingSystem.RoomType;

/**
 * Test class for functionality within the Bookings class.
 * @author Jonathan Janzen
 *
 */
class bookingsTests {
	
	Bookings testBookings = new Bookings();
	ArrayList<Integer> testNumRooms = new ArrayList<Integer>();

	@BeforeEach
	void setUp() {
		testNumRooms.add(1);	//Double
		testNumRooms.add(3);	//Twin
		testNumRooms.add(5);	//Queen
		testNumRooms.add(0);	//King
		for (int i = 0; i < RoomType.values().length; i++) {
			testBookings.setNumRooms(RoomType.values()[i], testNumRooms.get(i));
		}
	}
	
	@Test
	void testGetNumRoomsByType() {
		//General test to ensure that setting and getting the number of rooms is functional
		for (int i = 0; i < RoomType.values().length; i++) {
			assert testBookings.getNumRoomsByType(RoomType.values()[i]) == testNumRooms.get(i);
		}
	}
	
	@Test
	void testCreateBookingSingleDay() {
		//Expect a failure, as booking from 2022-06-13 to 2022-06-13 does not include a night, and therefore
		//should not be booked successfully
		assert !testBookings.createBooking(RoomType.DOUBLE, LocalDate.parse("2022-06-13"), LocalDate.parse("2022-06-13"));
	}
	
	@Test
	void testCreateBookingEndDateBeforeStart() {
		//Expect a failure, as booking from 2022-06-13 to 2022-06-10 is not a valid range of dates.
		assert !testBookings.createBooking(RoomType.TWIN, LocalDate.parse("2022-06-13"), LocalDate.parse("2022-06-10"));
	}
	
	@Test
	void testCreateBookingNoAvailibilityFromBeginning() {
		//Tests that the creation of a booking for a room type that has no capacity ever will fail
		assert !testBookings.createBooking(RoomType.KING, LocalDate.parse("2022-06-13"), LocalDate.parse("2022-06-17"));
	}
	
	@Test
	void testCreateBookingNoAvailabilityAfterBooking() {
		//Tests that a room type that was available but has been booked in a previous booking (and has no further availablity)
		//can no longer be booked
		testBookings.createBooking(RoomType.DOUBLE, LocalDate.parse("2022-06-13"), LocalDate.parse("2022-06-18"));
		assert !testBookings.createBooking(RoomType.DOUBLE, LocalDate.parse("2022-06-17"), LocalDate.parse("2022-06-19"));
	}
	
	@Test
	void testCreateBookingAvailabilitySingleBooking() {
		//General test for booking on a set of days with no previous bookings present
		assert testBookings.createBooking(RoomType.QUEEN, LocalDate.parse("2022-07-01"), LocalDate.parse("2022-07-10"));
	}
	
	@Test
	void testCreateBookingAvailabilityMultipleBookings() {
		//General test for booking on a set of days that has some other bookings of the same type present
		assert testBookings.createBooking(RoomType.TWIN, LocalDate.parse("2022-07-01"), LocalDate.parse("2022-07-03"));
		assert testBookings.createBooking(RoomType.TWIN, LocalDate.parse("2022-06-20"), LocalDate.parse("2022-07-02"));	//one night shared
		assert testBookings.createBooking(RoomType.TWIN, LocalDate.parse("2022-06-28"), LocalDate.parse("2022-07-03"));	//two nights shared
	}
	
	@Test
	void testCreateBookingAvailabilityMultipleBookingsDifferentTypes() {
		//Test to confirm that booking multiple types of bookings on the same day does not result in any issues unexpectedly
		testBookings.createBooking(RoomType.DOUBLE, LocalDate.parse("2022-06-13"), LocalDate.parse("2022-06-16"));	//use double, as there is only one room available
		//Assert that booking the only room of one type has no impact on the success of booking another room type
		assert testBookings.createBooking(RoomType.QUEEN, LocalDate.parse("2022-06-13"), LocalDate.parse("2022-06-16"));
	}
	
	@Test
	void testCheckAvailabilityMultipleBookings() {
		//Same case as above, validating that the expected availability of that room type is there on the given days
		testBookings.createBooking(RoomType.TWIN, LocalDate.parse("2022-07-01"), LocalDate.parse("2022-07-03"));
		testBookings.createBooking(RoomType.TWIN, LocalDate.parse("2022-06-20"), LocalDate.parse("2022-07-02"));	//one night shared
		testBookings.createBooking(RoomType.TWIN, LocalDate.parse("2022-06-28"), LocalDate.parse("2022-07-03"));	//two nights shared
		
		assert testBookings.checkAvailability(RoomType.TWIN, LocalDate.parse("2022-06-21")) == testNumRooms.get(RoomType.TWIN.getValue()) - 1;
		assert testBookings.checkAvailability(RoomType.TWIN, LocalDate.parse("2022-06-29")) == testNumRooms.get(RoomType.TWIN.getValue()) - 2;
		assert testBookings.checkAvailability(RoomType.TWIN, LocalDate.parse("2022-07-01")) == testNumRooms.get(RoomType.TWIN.getValue()) - 3;
	}
	
	@Test
	void testCheckAvailabilityNoBookings() {
		//Test to go through each possible room type on a given day and ensure that they are available (with
		//the exception of the King room, as there are no King rooms available ever) and match with the original number
		//of rooms
		for (int i = 0; i < RoomType.values().length; i++) {
			assert testBookings.checkAvailability(RoomType.values()[i], LocalDate.parse("2022-06-13")) == testNumRooms.get(i);
		}
	}
	
	@Test
	void testCheckAvailabilityNoRoomCapacityNoBookings() {
		//Test to check the availability of a given room type on arbitrary dates that does not have any capacity ever (King in this case)
		assert testBookings.checkAvailability(RoomType.KING, LocalDate.parse("2022-06-13")) == 0;
		assert testBookings.checkAvailability(RoomType.KING, LocalDate.parse("2020-05-20")) == 0;
		assert testBookings.checkAvailability(RoomType.KING, LocalDate.parse("2023-06-10")) == 0;
	}
	
	@Test
	void testGetNumBookingsNoBookings() {
		//Test that the initial number of bookings for each RoomType is 0
		for (int i = 0; i < RoomType.values().length; i++) {
			assert testBookings.getNumBookings(RoomType.values()[i]) == 0;
		}
	}
	
	@Test
	void testGetNumBookingsStandardBookings() {
		//Using the case from earlier, confirm that the number of days booked are as expected for some type
		testBookings.createBooking(RoomType.TWIN, LocalDate.parse("2022-07-01"), LocalDate.parse("2022-07-03"));
		testBookings.createBooking(RoomType.TWIN, LocalDate.parse("2022-06-20"), LocalDate.parse("2022-07-02"));
		testBookings.createBooking(RoomType.TWIN, LocalDate.parse("2022-06-28"), LocalDate.parse("2022-07-03"));
		
		assert testBookings.getNumBookings(RoomType.TWIN) == 13;
	}
}
