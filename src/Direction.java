package model;



/**
 * Constants for valid directions in the application:
 * NORTH, SOUTH, EAST and WEST. Directions have relationships
 * mimicking real world navigation. The logic of moving fight, left,
 * or opposite from given direction is defined in class Direction methods.
 * 
 * 
 * @author Ruta Binkyte-Sadauskiene e-mail:s1792395@sms.ed.ac.uk
 * @version 1.0
 */
public enum Direction {
	
	NORTH, SOUTH, EAST, WEST; // Constant directions
	
	
	/**
	 * Gets direction which is opposite
	 * to the current direction.
	 * 
	 * @return an opposite Direction
	 */
	public Direction getOpposite()
	{   
		Direction direction = this;
		Direction oppositeDirection;
		
		switch (direction) {
		
		    case NORTH:
		    	oppositeDirection = SOUTH;
		    	break;
		    case SOUTH:
		    	oppositeDirection = NORTH;
		    	break;
		    case EAST:
		    	oppositeDirection = WEST;
		    	break;
		    case WEST:
		    	oppositeDirection = EAST;
		    	break;
		    default: oppositeDirection = SOUTH;
		        break;
	     }
		    
		return oppositeDirection;
	}
	
	
	/**
	 * Gets direction to the left from
	 * the current direction.
	 * 
	 * @return a direction to the of
	 */
	public Direction getLeft()
	{
		Direction direction = this;
		Direction leftDirection;
		
		switch (direction) {
		
		    case NORTH:
		    	leftDirection = WEST;
		    	break;
		    case SOUTH:
		    	leftDirection = EAST;
		    	break;
		    case EAST:
		    	leftDirection = NORTH;
		    	break;
		    case WEST:
		    	leftDirection = SOUTH;
		    	break;
		    default: leftDirection = WEST;
		        break;
	     }
		    
		return leftDirection;
	}
	
	
	/**
	 * Gets direction to the right of the
	 * current direction.
	 * 
	 * @return a Direction to the right
	 */
	public Direction getRight()
	{
		Direction direction = this;
		Direction rightDirection;
		
		switch (direction) {
		
		    case NORTH:
		    	rightDirection = EAST;
		    	break;
		    case SOUTH:
		    	rightDirection = WEST;
		    	break;
		    case EAST:
		    	rightDirection = SOUTH;
		    	break;
		    case WEST:
		    	rightDirection = NORTH;
		    	break;
		    default: rightDirection = EAST;
		        break;
	     }
		    
		return rightDirection;
	}
		    		    	
		    
	/**
	 * Checks if direction is opposite to this direction
	 * 
	 * @param direction Direction NORTH, SOUTH, WEST or EAST
	 * @return true if direction is opposite to this direction
	 */
	public boolean isOpposite (Direction direction)
	{
		if(this.getOpposite() == direction) {
			
			return true;
		}
	
		return false;
	}
	
	
	/**
	 * Checks if direction is to the left of this direction
	 * 
	 * @param direction Direction NORTH, SOUTH, WEST or EAST
	 * @return true if direction is to the left of this direction
	 */
	public boolean isLeft(Direction direction)
	{
		if(this.getLeft() == direction) {
			return true;
		}
		
		return false;
	}
	
	
	/**
	 * Checks if direction is to the right of this direction
	 * 
	 * @param direction Direction NORTH, SOUTH, WEST or EAST
	 * @return true if direction is to the right of this direction
	 */
	public boolean isRight(Direction direction)
	{
		if(this.getRight() == direction) {
			return true;
		}
		
		return false;
	}

}
