package test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import model.Direction;

/**
 * A test class for the Direction class.
 * The tests are performed to check if relations between direction which support navigation are set correctly.
 * 
 * @author Ruta Binkyte-Sadauskiene e-mail:s1792395@sms.ed.ac.uk
 * @version 1.0
 *
 */
public class DirectionTest {

	/**
	 * Tests if opposite Directions are set properly
	 */
	@Test
	public void getOppositeTest() {
		
		Direction northDirection = Direction.NORTH;
		Direction oppositeDirection = northDirection.getOpposite();
		assertTrue(oppositeDirection == Direction.SOUTH);
	}
	
	
	/**
	 * Tests if Direction to the right of given one
	 * is set properly.
	 */
	@Test
	public void getRightTest() {
		
		Direction northDirection = Direction.NORTH;
		Direction rightDirection = northDirection.getRight();
		assertTrue(rightDirection == Direction.EAST);
	}
	
	
	/**
	 * Tests if "turning around" results in the same initial Direction
	 */
	@Test
	public void turnAround() {
		
		Direction northDirection = Direction.NORTH;
		Direction rightDirection = northDirection.getRight();
		Direction oppositeDirection = rightDirection.getRight();
		Direction leftDirection = oppositeDirection.getRight();
		Direction backDirection = leftDirection.getRight();
		
		assertTrue(rightDirection == Direction.EAST);
		assertTrue(oppositeDirection == northDirection.getOpposite());
		assertTrue(leftDirection == northDirection.getLeft());
		assertTrue(backDirection == northDirection);
	}
		
		

}
