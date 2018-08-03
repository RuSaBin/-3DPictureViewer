package model;

/**
 * Class Item represents items which can be located in the Location
 * 
 * @author Ruta Binkyte-Sadauskiene e-mail:s1792395@sms.ed.ac.uk
 * @version 1.0
 */
public class Item {
	
	
	private String url; // image url
	private String name; // item name
	
	/**
	 * Constructor for the Item
	 * 
	 * @param name String item name
	 * @param url String image url
	 * @throws IllegalArgumentException if url is invalid
	 */
	public Item(String name, String url) 
	{
		if(url.length() == 0 || url == null) {
			throw new IllegalArgumentException(
					"Image url is empty");
		}
		this.name = name;
		this.url = url;
		
	}
	
	
	/**
	 * Gets image url
	 * 
	 * @return String url
	 */
	public String getUrl()
	{
		return url;
	}
	
	/**
	 * Gets item name
	 * 
	 * @return String name
	 */
	public String getName()
	{
		return name;
	}
	

}
