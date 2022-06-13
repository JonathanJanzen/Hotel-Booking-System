package unitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import HotelBookingSystem.RedBlackBST;

class redBlackBSTTests {
	
	RedBlackBST testBST = new RedBlackBST();

	@Test
	void testSizeEmpty() {
		//Verifies that an empty BST returns 0 for size
		assert testBST.size() == 0;
	}
	
	@Test
	void testSizeSomeElements() {
		//Verifies that a BST with 3 item within returns the correct size
		testBST.put(LocalDate.parse("2022-06-13"), 5);
		testBST.put(LocalDate.parse("2022-06-14"), 2);
		testBST.put(LocalDate.parse("2022-03-13"), 2);
		assert testBST.size() == 3;
	}
	
	@Test
	void testIsEmptyTrue() {
		//Verifies that a BST with no elements is empty
		assert testBST.isEmpty();
	}
	
	@Test
	void testIsEmptyFalse() {
		//Verifies that a BST with 3 item within is not empty
		testBST.put(LocalDate.parse("2022-06-13"), 5);
		testBST.put(LocalDate.parse("2022-06-14"), 2);
		testBST.put(LocalDate.parse("2022-03-13"), 2);
		assert !testBST.isEmpty();
	}
	
	@Test
	void testGetEmptyBST() {
		//Verifies that a BST with no elements returns null
		assert testBST.get(LocalDate.parse("2022-06-13")) == null;
	}
	
	@Test
	void testGetNonEmptyBSTSuccess() {
		//Verifies that 'get' returns a value that exists in the BST
		testBST.put(LocalDate.parse("2022-06-13"), 5);
		testBST.put(LocalDate.parse("2022-06-14"), 2);
		testBST.put(LocalDate.parse("2022-03-13"), 2);
		assert (Integer) testBST.get(LocalDate.parse("2022-06-13")) == 5;
	}
	
	@Test
	void testGetNonEmptyBSTFailure() {
		//Verifies that 'get' returns null if a value does not exist in the BST
		testBST.put(LocalDate.parse("2022-06-13"), 5);
		testBST.put(LocalDate.parse("2022-06-14"), 2);
		testBST.put(LocalDate.parse("2022-03-13"), 2);
		assert (Integer) testBST.get(LocalDate.parse("2022-06-15")) == null;
	}
	
	@Test
	void testPutOverwritingValue() {
		//Verifies that 'put' successfully overwrites a value that is already present in the tree
		testBST.put(LocalDate.parse("2022-06-14"), 2);
		testBST.put(LocalDate.parse("2022-03-13"), 2);
		assert (Integer) testBST.get(LocalDate.parse("2022-06-14")) == 2;
		//Overwrite the value
		testBST.put(LocalDate.parse("2022-06-14"), 1);
		assert (Integer) testBST.get(LocalDate.parse("2022-06-14")) == 1;
	}
	
	@Test
	void testPutAddingValue() {
		//Standard values added as base
		testBST.put(LocalDate.parse("2022-06-13"), 5);
		testBST.put(LocalDate.parse("2022-06-14"), 2);
		testBST.put(LocalDate.parse("2022-03-13"), 2);
		assert testBST.get(LocalDate.parse("2022-06-12")) == null;
		testBST.put(LocalDate.parse("2022-06-12"), 10);
		assert (Integer) testBST.get(LocalDate.parse("2022-06-12")) == 10;
	}
}
