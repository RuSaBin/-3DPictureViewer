package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import model.Direction;



/**
 * Location class represents locations in the application. Location uses constants of class Direction (NORTH,
 * SOUTH, EAST and WEST) to define its views and logic of navigation between them. Moving between Locations is also
 * supported if Location has neighbors. Adding Location B as neighbor to Location A, automatically results 
 * in adding A as neighbor to Location B. Navigation between two Location follows the logic of real world navigation.
 * For example, if Location A has neighbour B to the North, it is possible to enter Location B when facing North view 
 * of A. This will result in facing North view of Location B, and and "exit" back to Location A will be from 
 * South (the opposite) view of B. 
 * 
 * Location can have locationContent - a list with one or more item names and picture url. 
 * Content is visible on all views of the Location.
 * Maximum 3 items can be displayed at once.
 *
 * 
 * @author Ruta Binkyte-Sadauskiene e-mail:s1792395@sms.ed.ac.uk
 * @version 1.0
 */

public class Location {
	
	private String name; // name of the Location
	private Direction currentDirection; // current view of the Location
	private HashMap<Direction, String> views; // Pairs of direction and image URL representing views of the Location
	private HashMap<Direction, Location> neighbours; //Neighbouring Locations which can be entered from this Location
	private ArrayList<Item> locationContent; //content of the location
	
	/**
	 * A constructor for a Location 
	 * 
	 * Requires a name and Map representing
	 * the views of the Location 
	 * 
	 * @param name any String name for the Location
	 * @param views Map of pairs of direction and image URL representing views of the Location
	 * throws IllegalArgumentException if collection of location views is invalid
	 */
	public Location(String name, HashMap<Direction, String> views)
	{
		if(views.isEmpty() || views.size()<4) {
			throw new IllegalArgumentException("Location views are invalid");
		}
		this.name = name;
		this.views = views;
		locationContent = new ArrayList<Item>();
		neighbours = new HashMap<Direction, Location>();
		currentDirection = Direction.NORTH;	// default Direction is set to North
	}


	/**
	 * Gets a view of the Location
	 * to the left of the current one.
	 * 
	 * @return string url of the image 
	 */
	public String moveLeft()
	{
		Direction nextDirection = currentDirection.getLeft();
		currentDirection = nextDirection;
		String url = views.get(currentDirection);
		
		return url;
		
	}
	
	
	/**
	 * Gets a view of the Location
	 * to the right of the current one.
	 * 
	 * @return string url of the image 
	 */
	public String moveRight()
	{
		Direction nextDirection = currentDirection.getRight();
		currentDirection = nextDirection;
		String url = views.get(currentDirection);
		
		return url;	
		
	}
	
	
	/**
	 * Gets the opposite view of the 
	 * Location.
	 * 
	 * @return string url of the image 
	 */
	public String moveOpposite()
	{
		Direction nextDirection = currentDirection.getOpposite();
		currentDirection = nextDirection;
		String url = views.get(currentDirection);
		
		return url;
	}
	
	
	/**
	 * Enters next Location if there is 
	 * a neighbour set to the current Direction.
	 * 
	 * @return next Location or this Location if moving forward is not possible
	 */
	public Location moveForward()
	{   
		Location nextLocation;
		//Checks if there is a neighbour set on current Direction
		if(neighbours.containsKey(currentDirection) && !neighbours.get(currentDirection).equals(null)) {
			nextLocation = neighbours.get(currentDirection);
			//Makes sure that right Direction of the next Location will be entered
		    nextLocation.setCurrentDirection(currentDirection); 
		    return nextLocation;
		}
	
		return this;
	}
	
	
	/**
	 * Adds neighbour to the Location
	 * 
	 * This enables moving from this Location to its 
	 * neighbour and back. 
	 * 
	 * @param neighbour Location to be added as neighbour to this Location
	 * @param direction Direction which identifies position of neighbouring Location
	 */
	public void addNeighbour(Location neighbour, Direction direction)
	{
		// adds neighbour Location and associates it with a Direction
		this.neighbours.put(direction, neighbour);
		/* This Location is also added as neighbour to the neighbour Location
		 * It is associated with the opposite Direction. For example, if B has 
		 * neighbour A to the North, A has neighbour B to the South.
		 */
		Direction oppDirection = direction.getOpposite();
		neighbour.neighbours.put(oppDirection, this);
		
	}
	
	
	/**
	 * Gets Map with views of the Location
	 * 
	 * @return Map with pairs of String image urls and associated Directions
	 */
	public Map<Direction, String> getViews()
	{
		return views;
	}
	
	
	/**
	 * Checks if a Location is a neighbour of this Location
	 * 
	 * @param location Location 
	 * @return true if Location is a neighbour of this Location
	 */
	public boolean isNeighbour(Location location) {
		
		if(neighbours.containsValue(location))
		{
			return true;
		}
		
		return false;		
	}
	
	
	/**
	 * Gets currentDirection
	 * 
	 * @return Direction currentDirection
	 */
	public Direction getCurrentDirection()
	{
		return currentDirection;
	}
	
	
	/**
	 * Gets Map of the neighbouring Locations
	 * 
	 * @return HashMap with pairs of neighbouring Locations and Directions they are set on
	 */
	public HashMap<Direction, Location> getNeighbours()
	{
		return neighbours;
	}
	
	
	/**
	 * Sets currentDirection to given Direction
	 * 
	 * @param direction NORTH, SOUTH, EAST or WEST
	 */
	public void setCurrentDirection(Direction direction)
	{
		currentDirection = direction;
	}
	
	
	/**
	 * Gets name of the Location
	 * 
	 * @return name of the Location
	 */
	public String getName()
	{
		return name;
	}
	
	
	/**
	 * Adds Item to the Location
	 * 
	 * @param item Item to be added to the Location
	 */
	public void addContent(Item item)
	{
		locationContent.add(item);
		
	}
	
	/**
	 * Removes item from location
	 * 
	 * @param pickedItem Item to be removed from the Location
	 */
	public void removeItem(String itemName)
	{   
		Iterator<Item> it = locationContent.iterator();
		while(it.hasNext()) {
			Item item = it.next();
			//Searches for an item with parameter name
			String name = item.getName();
			if(name.equals(itemName)) {
				it.remove();// removes Item from the collection
			}
		}
			
	}
	
	/**
	 * Gets Item from the Location
	 * 
	 * @param itemName Strin name of an item
	 * @return item or null, if not found
	 */
	public Item getItem(String itemName)
	{   
		
        Item item;
		Iterator<Item> it = locationContent.iterator();
		while(it.hasNext()) {
			item = it.next();
			//Searches for an item with parameter name
			String name = item.getName();
			if(name.equals(itemName)) {
				return item;
			}
		}
	    return null;
	}
	
	
	/**
	 * Gets locationContent
	 * 
	 * @return Collection holding information of content present in the Location 
	 */
	public ArrayList<Item> getLocationContent()
	{
		return locationContent;
	}
	
	/**
	 * Gets names of the items 
	 * 
	 * @return ArrayList<String> collection of names of the items present in the Location
	 */
	public ArrayList<String> getItemNames()
	{   
		ArrayList<String> names = new ArrayList<String>();
		if(locationContent.size() > 0) {
		    Iterator<Item> it = locationContent.iterator();
		    while(it.hasNext()) {
			    Item item = it.next();
			    String name = item.getName();
			    if(!name.equals(null)) {
				    names.add(name);
			}
		     }
		}
		
		return names;
	}
	
	/**
	 * Gets urls of the items
	 * 
	 * @return ArrayList<String> collection of urls of the items present in the Location
	 */
	public ArrayList<String> getItemUrl()
	{
		ArrayList<String> urls = new ArrayList<String>();
		if(locationContent.size() > 0) {
		    Iterator<Item> it = locationContent.iterator();
		    while(it.hasNext()) {
			    Item item = it.next();
			    String url = item.getUrl();
			    if(!url.equals(null)) {
				    urls.add(url);
			}
		     }
		}
		
		return urls;
	}
			
		
	}
	
	 
	
	

	
	


