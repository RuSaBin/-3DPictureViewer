package model;

import java.util.ArrayList;
import java.util.HashMap;

import model.Location;


/**
 * Main class of the model. World creates all the Locations and controls relationship (as neighbours) 
 * and navigation between them.
 * Main class is also responsible for putting content to Location and keeping count of collected items.
 * 
 * 
 * @author Ruta Binkyte-Sadauskiene e-mail:s1792395@sms.ed.ac.uk
 * @version 1.0
 */
public class World {
	

	private HashMap<String, Item> collectedItems; // Collection of collected items String names and items
	private Location currentLocation; // Currently displayed Location
	
	
	/**
	 * Constructor for the World
	 */
	public World()
	{
		collectedItems = new HashMap<String, Item>();
	    setUpLocations();// Creates locations and content
	}
	
	/**
	 * Creates locations and content
	 * Sequence of Directions to be paired 
	 * with image urls is: NORTH, SOUTH, WEST, EAST
	 */
	public void setUpLocations()
	{
		try {	    
			    // Creates HashMaps with pairs of image url and associates Directions
		    	// to be passed to the constructors of Locations
			
				HashMap<Direction, String> views = createLocationViews("outsideNorth.jpg","outsideSouth.jpg",
					"outsideWest.jpg", "outsideEast.jpg");
			
				HashMap<Direction, String> views1 = createLocationViews("sarbievijusNorth.jpg","sarbievijusSouth.jpg",
					"sarbievijusWest.jpg", "sarbievijusEast.jpg");
			
				HashMap<Direction, String> views2 = createLocationViews("sDaukantasNorth.jpg", "sDaukantasSouth.jpg",
					"sDaukantasWest.jpg", "sDaukantasEast.jpg");
			
				HashMap<Direction, String> views3 = createLocationViews("pSkargaNorth.jpg", "pSkargaSouth.jpg",
					"pSkargaWest.jpg", "pSkargaEast.jpg");
		
				HashMap<Direction, String> views4 = createLocationViews("observatoryNorth.jpg", "observatorySouth.jpg",
					"observatoryWest.jpg", "observatoryEast.jpg");
		
		
				//Creates Locations
				Location outside = new Location("outside", views);	
				Location sarbievijusYard = new Location("sarbievijus", views1);	
				Location sDaukantasYard = new Location("sDaukantas", views2);	
				Location pSkargaYard = new Location("pSkarga", views3);
				Location observatoryYard = new Location("observatory", views4);
		
				//Adds neigbours to the Locations
				outside.addNeighbour(sarbievijusYard, Direction.NORTH);
				sarbievijusYard.addNeighbour(sDaukantasYard, Direction.NORTH);
				sarbievijusYard.addNeighbour(pSkargaYard, Direction.EAST);
				pSkargaYard.addNeighbour(observatoryYard, Direction.SOUTH);
		
				//Adds content to the locations
				sarbievijusYard.addContent(new Item("Sarbievijus", "sarbievijus.png"));
				pSkargaYard.addContent(new Item("Petras Skarga", "pSkarga.png"));
				sDaukantasYard.addContent(new Item("Simonas Daukantas", "sDaukantas.png"));
				observatoryYard.addContent(new Item("Telescope", "telescope.png"));
				outside.addContent(new Item("Basket", "basket.png"));
				
				//Sets currentLocation 
				currentLocation = outside;
			
		    
		    }
		catch(Exception e) {
			
			System.out.println(e);
			
		}
		
			
	}
	

	/**
	 * Gets view to the left of current one
	 * in the same Location.
	 * 
	 * @return string url of the image
	 */
	public String goLeft()
	{
		String url = currentLocation.moveLeft();
		return url;
	}
	
	
	/**
	 * Gets view to the right of current one
	 * in the same Location.
	 * 
	 * @return string url of the image
	 */
	public String goRight()
	{
		return currentLocation.moveRight();
	}
	
	
	/**
	 * Gets view of the neighbouring
	 * Location.
	 * 
	 * @return string url of the image
	 */
	public String goForward()
	{
		Location nextLocation = currentLocation.moveForward();
		currentLocation = nextLocation;// sets new Location as currentLocation
		//Location needs to be entered facing its currentDirection
		Direction direction = nextLocation.getCurrentDirection();
		String url = nextLocation.getViews().get(direction);
	
		return url;
	}
	
	
	/**
	 * Gets Location that is set to current
	 * 
	 * @return  Location currentLocation
	 */
	public Location getCurrentLocation()
	{
		return currentLocation;
	}
	
	
	/**
	 * Gets image representing current view
	 * of the currentLocation
	 * 
	 * @return String url of the image
	 */
	public String getCurrentView()
	{
		Direction direction = currentLocation.getCurrentDirection();
		String url = currentLocation.getViews().get(direction);
		return url;
	}
	
	
	/**
	 * Checks if moving forward to next Location
	 * is possible
	 * 
	 * @return true if moving forward is possible
	 */
	public boolean canMoveForward()
	{
		Direction neighbour = currentLocation.getCurrentDirection();
		// checks if current Location has neighbour this direction
		boolean canMove = currentLocation.getNeighbours().containsKey(neighbour);
		return canMove;
	}
	
	
	/**
	 * Gets names of the items, present in current Location
	 * 
	 * @return ArrayList<String> with item names
	 */
	public ArrayList<String> getItemNames()
	{
		return currentLocation.getItemNames();
	}
	
	
	/**
	 * Gets names of the collected items
	 * 
	 * @return ArrayList with String names of collected items
	 */
	public ArrayList<String> getCollectedItemNames()
	{
		ArrayList<String> collectedItemNames = new ArrayList<String>();
		collectedItemNames.addAll(collectedItems.keySet());
		return collectedItemNames;
	}
	
	
	/**
	 * Gets urls of the items, present in current Location
	 * 
	 * @return ArrayList<String> with item urls
	 */
	public ArrayList<String> getItemUrl()
	{
		return currentLocation.getItemUrl();
	}
	
	
	/**
	 * Picks item from the location and adds to a collection
	 * 
	 * @param itemName String name of the item
	 */
	public void pickItem(String itemName)
	{   
		Item item = currentLocation.getItem(itemName);//Gets names of items present in current location
		if(item != null) {
			collectedItems.put(itemName, item);//Adds to collected items collection
			currentLocation.removeItem(itemName);//Removes corresponding item from the location
		}
		
	}
	
	
	/**
	 * Takes item form collected items collection
	 * and puts in the current location
	 * 
	 * @param itemName String name of the item
	 */
	public void putItem(String itemName)
	{
		if(collectedItems.containsKey(itemName)) {
		   Item item = collectedItems.get(itemName);
		   if(item != null) {
		       currentLocation.addContent(item);//Adds to the location
		       collectedItems.remove(itemName, item);//Removes item from the collection
		   }
	    }
	}
	
	
	/**
	 * Gets number of items in current location
	 * 
	 * @return int number of items 
	 */
	public int getLocationItemNumber()
	{
		int number = currentLocation.getLocationContent().size();
		return number;
	   
	}
	
	/**
	 * Creates collection of views to be passed to
	 * the constructor of Location
	 * Sequence of Directions is NORTH, SOUTH, WEST, EAST
	 * @return HashMap with Direction and String image urls pairs
	 * @throws IllegalArgumentException if image url is invalid
	 */
	private HashMap<Direction, String> createLocationViews(String url1,
			 String url2,  String url3, String url4)
	{    
		 //Checks if url is not empty
		 if(url1 == "" || url2 == "" || url3 == "" || url4 =="") {
			 
			 throw new IllegalArgumentException("Image url is empty");
		 }
		 HashMap<Direction, String> views = new HashMap<>();
		 views.put(Direction.NORTH, url1);
		 views.put(Direction.SOUTH, url2);
	     views.put(Direction.WEST, url3);
		 views.put(Direction.EAST, url4);
		 return views;
	}
	
	
		
	
		

}
