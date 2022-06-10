package algorithms;

import java.util.ArrayList;

import HotelBookingSystem.Booking;

/**
 * Class containing code for mergesort. Code has been taken and slightly modified from Sedgewick and Wayne's
 * "Algorithms: Fourth Edition". Each function has been cited individually from the textbook.
 * @author Jonathan Janzen
 *
 */
public class BookingsMergesort {
	
	private static Booking[] aux, aux2;
	
	/**
	 * Code for less taken from page 245 of "Algorithms: Fourth Edition" by
	 * Sedgewick and Wayne.
	 * @param v A comparable object.
	 * @param w A comparable object.
	 * @return Return true if v is less than w, false otherwise.
	 */
	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}
	
	/**
	 * Function to merge. Code based on Sedgewick & Wayne's "Algorithms: Fourth
	 * Edition" on page 271, but modified to implement the Booking type.
	 *
	 * @param a   An ArrayList of type <Booking>.
	 * @param lo  Low index position.
	 * @param mid Mid index position.
	 * @param hi  High index position.
	 */
	private static void merge(ArrayList<Booking> a, int lo, int mid, int hi) {
		int i = lo, j = mid + 1;
		
		for (int k = lo; k <= hi; k++)
			aux[k] = a.get(k);
		for (int k = lo; k <= hi; k++)
			if (i > mid)
				a.set(k,  aux[j++]);
			else if (j > hi)
				a.set(k, aux[i++]);
			else if (less(aux[j], aux[i]))
				a.set(k, aux[j++]);
			else
				a.set(k, aux[i++]);
	}
	
	/**
	 * Sorting function intial call. Code based from Sedgewick and Wayne's
	 * "Algorithms: Fourth Edition" on page 278, but modified to fit the Booking
	 * datatype.
	 * @param a An ArrayList of type Booking.
	 */
	public static void sort(ArrayList<Booking> a) {
		int n = a.size();
		aux = new Booking[n];
		for (int len = 1; len < n; len *= 2) {
			for (int lo = 0; lo < n - len; lo += len + len) {
				merge(a, lo, lo + len - 1, Math.min(lo + len - 1, n - 1));
			}
		}
	}
	
	/**
	 * Function to merge. Code based on Sedgewick & Wayne's "Algorithms: Fourth
	 * Edition" on page 271, but modified to implement the Booking type and sort by
	 * name.
	 * @param a   An ArrayList of type <Booking>.
	 * @param lo  Low index position.
	 * @param mid Mid index position.
	 * @param hi  High index position.
	 */
	private static void mergeByDate(ArrayList<Booking> a, int lo, int mid, int hi) {
		int i = lo, j = mid + 1;

		for (int k = lo; k <= hi; k++)
			aux2[k] = a.get(k);
		for (int k = lo; k <= hi; k++)
			if (i > mid)
				a.set(k, aux2[j++]);
			else if (j > hi)
				a.set(k, aux2[i++]);
			else if ((aux2[j].getDate()).compareTo(aux2[i].getDate()) < 0)
				a.set(k, aux2[j++]);
			else
				a.set(k, aux2[i++]);
	}

	/**
	 * Function to sort an ArrayList of Bookings based on name. Returns a duplicate
	 * of the original input ArrayList.
	 * 
	 * @param a The original ArrayList to sort.
	 * @return Returns a duplicate ArrayList sorted by name.
	 */
	public static ArrayList<Booking> sortByDate(ArrayList<Booking> a) {
		ArrayList<Booking> temp = new ArrayList<Booking>();
		for (int i = 0; i < a.size(); i++) {
			temp.add(a.get(i));
		}
		int n = a.size();
		aux2 = new Booking[n];
		for (int len = 1; len < n; len *= 2)
			for (int lo = 0; lo < n - len; lo += len + len)
				mergeByDate(temp, lo, lo + len - 1, Math.min(lo + len + len - 1, n - 1));
		return temp;
	}
}
