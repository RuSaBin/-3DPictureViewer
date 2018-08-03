




import java.util.ArrayList;
import java.util.Iterator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.World;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.SepiaTone;
import javafx.scene.image.Image;

/**
 * The Controller class connects model classes with GUI interface. It passes user input commands to the methods defined
 * in the model.
 * 
 * @author Ruta Binkyte-Sadauskiene e-mail:s1792395@sms.ed.ac.uk
 * @version 1.0
 *
 */
public class WorldController {
	
	World world = new World();// Creates the model 
	
	
	@FXML
	private ImageView imageView;// Displays images of the locations
	
	@FXML
	private ImageView item1View;// Displays one of three items

	@FXML
	private ImageView item2View;// Displays one of three items
	
	@FXML
	private ImageView item3View;// Displays one of three items
	
	@FXML
	private Button left;
	
	@FXML
	private Button right;
	
	@FXML
	private Button forward;
	
	@FXML
	private Label title;
	
	@FXML
	private AnchorPane background;
	
	@FXML
	private Label go;
	
	@FXML
	private Menu pickItem;
	
	@FXML
	private Menu putItem;
	
	@FXML
	private Label maxItems;
	
	
	/**
	 * Sets the initial view of the application.
	 */
	public void initialize() 
	{   
		//Gets url of location view to be displayed
		String url = world.getCurrentView();
        Image image = new Image(url);
        imageView.setImage(image);
        //Updates items and button view
        updateView();
        
	}
	
	
    /**
     * Shows image to the left of current view
     * 
     * @param event button click
     */
    public void goLeft(ActionEvent event) {
          
    	  //Gets url of location view to be displayed
          String viewNow = world.goLeft();
          Image image = new Image(viewNow);
          imageView.setImage(image);
          //indicates that moving forward is possible
          canGoForward();
          
    }
    
    
    /**
     * Shows image to the right of current view
     * 
     * @param event button click
     */
    public void goRight(ActionEvent event) {
        
    	//Gets url of location view to be displayed
        String viewNow = world.goRight();
        Image image = new Image(viewNow);
        imageView.setImage(image);
        //indicates that moving forward is possible
        canGoForward();

  }
    
    
    /**
     * Shows image representing new Location
     * if moving forward is allowed
     * 
     * @param event button click
     */
    public void goForward(ActionEvent event) 
    {
        
    	//Gets url of location view to be displayed
        String viewNow = world.goForward();
        //Creates image using give path
        Image image = new Image(viewNow);
        imageView.setImage(image);
        //Updates item and button views
        updateView();
       
    }
  
    
    /**
     * Dynamically adds menuItems to pickItem Menu
     */
    private void addPickItemMenu() 
    {   
    	//Gets set of names of items present in current Location
    	ArrayList<String> itemNames = new ArrayList<String>();
    	itemNames.addAll(world.getItemNames());
    	//creates menu items corresponding to items present in the Location
    	if(!itemNames.isEmpty()) {
    	    for (String itemName: itemNames) {
    	
		        MenuItem menuItem = new MenuItem(itemName);
		        menuItem.setOnAction(e-> handlePickItem(itemName));
    	        pickItem.getItems().add(menuItem);
    	      
    	    }
    	}
    }
    
    /**
     * Dynamically adds menuItems to putItem Menu
     */
    private void addPutItemMenu()
    {   
    	//Gets set of names of picked items 
    	ArrayList<String> itemNames = new ArrayList<String>();
    	itemNames.addAll(world.getCollectedItemNames());
    	//Creates menu items corresponding to picked items
    	if(!itemNames.isEmpty()) {
    	    for (String itemName: itemNames) {
    	
		        MenuItem menuItem = new MenuItem(itemName);
		        menuItem.setOnAction(e-> handlePutItem(itemName));
		        putItem.getItems().add(menuItem);
    	        
    	    }
    	} 
    }
    
    
    /**
     * Picks items from current Location
     * 
     * @param itemName String name of an item
     */
    private void handlePickItem(String itemName)
    {
    	world.pickItem(itemName);
    	updateView();//Updates visible items
    	
    }
    
    /**
     * Puts item in current Location
     * 
     * @param itemName String name of an item
     */
    private void handlePutItem(String itemName)
    {   
    	//Checks if maximum items number per Location is exceeded
    	if(world.getLocationItemNumber()<3) {
    	    world.putItem(itemName);
    	    updateView();//updates visible items
    	}
    	else {
    		maxItems.setVisible(true);//Displays message that Location is full
    	}
    }
    
    
    /**
     * Updates item views, creates
     * Menu item lists, updates buttons
     * and labels.
     * 
     */
    private void updateView()
    {
    	//Updates item views 
    	
    	//Clears Item views
    	item1View.setImage(null);
    	item2View.setImage(null);
    	item3View.setImage(null);
    	
    	//Creates item views 
    	updateItemsViews();
    	
    	
    	//Updates Menu items
    	
    	//Empties menus
    	pickItem.getItems().clear();
    	putItem.getItems().clear();
    	
    	//Adds PickItem Menu items
    	addPickItemMenu();
    	
    	//Adds PutItem Menu items
    	addPutItemMenu();
    	
    	
    	//Updates Forward button view and Label
    	canGoForward();
    	
    	//Resets maxItems message
    	maxItems.setVisible(false);
    }
    
    /**
     * Displays items in the locations 
     */
    public void updateItemsViews() 
    {
    	//Creates Set of image views for the items
    	ArrayList<ImageView> itemViews = new ArrayList<ImageView>();
    	itemViews.add(item1View);
    	itemViews.add(item2View);
    	itemViews.add(item3View);
    	
    	//Creates a copy of ArrayList with image urls from the Location
    	ArrayList<String> itemsUrls = new ArrayList<String>();
    	itemsUrls.addAll(world.getItemUrl());
       
    	//Iterates through ImageViews
        for(ImageView view:itemViews) {
        	//Iterates through item urls
        	Iterator<String> it = itemsUrls.iterator();
        	if(it.hasNext()) {
        		
        		String itemUrl = it.next();
        		//Creates an image with provided path
        		Image image = new Image(itemUrl);
        		view.setImage(image);//Sets image on one of ImageViews
        		//Removes item url that is already used from the set
        		it.remove();
        	}
        	    
        }
    	
    }
    
    
    /**
     * Indicates that moving forward is possible
     */
    private void canGoForward()
    {
    	//Shows text "go!" if moving forward is possible in current direction
        if(world.canMoveForward()) {
            go.setText("GO!");
            forward.setDisable(false);
        }
        //If moving to next location is impossible, the button is disabled
        else if(!world.canMoveForward()) {
            go.setText("");
            forward.setDisable(true);
        }
    }
    
    /**
	 * Adds sepia effect to imageView
	 */
	public void applySepia()
	{
		SepiaTone sepiaTone = new SepiaTone();
        sepiaTone.setLevel(0.7);
        imageView.setEffect(sepiaTone);
	}
    
    
}

    

    
    
    
    
    
    

