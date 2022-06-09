package HotelBookingSystem;

/**
 * Enumerated class for different types of rooms. A sequence of four possibilities has been chosen
 * as an example.
 * @author Jonathan Janzen
 *
 */
public enum RoomType {
	DOUBLE(0),
	TWIN(1),
	QUEEN(2),
	KING(3);
	
	private final int value;
	
	/**
	 * Private constructor, used to define the numerical value of each item.
	 * @param _value Numerical value to be assigned to the enumerated type.
	 */
	private RoomType(int _value) {
		this.value = _value;
	}
	
	/**
	 * Getter for the value of the enumerated class (used for indexing the Rooms list)
	 * @return Integer value that corresponds to the type.
	 */
	public int getValue() {
		return this.value;
	}
}
