import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

/**
 * Main program of the application. Launches the application and the GUI from fxml file.
 * 
 * @author Paul Anderson
 * @version 2017
 */
public class MainProgram extends Application {
	

	public void start(Stage stage) {
		
		try {
		
		    // this is a longer version of Example 2 with some debugging
			// information to help you if you are having trouble loading the fxml.
			// When you run this from a jar file, the results will be different
			// from when you run it from Eclipse, so you should try both.
			
			// Export / Java / Runnable jar file
			// Don't forget to set the "launch configuration to the appropriate source file"
			
			// you will need to run the jar file from the command line to see the messages:
			// java -jar MYFILE.jar

			// this is the pathname of the fxml file *relative to the class file*:
			// - the class files for the default package are at the top level
			// - any fxml files under the src directory are at the top level
			// - subpackages and subdirectories are at correspondingly lower levels
			// In this case:
			// - the class file is in the default package (at the top)
			// - the fxml is under the src directory (at the top)
			// so the simple filename will suffice
			String viewerFxml = "WorldViewer.fxml";
			FXMLLoader fxmlLoader = new FXMLLoader();
			System.out.println( "fxml path: " + viewerFxml );
			
			// this is the name of the class
			String theClassName = this.getClass().getSimpleName();
			System.out.println( "main class name: " + theClassName );
		
			// this is where the running class file is located
			// it will look for the fxml file relative to this pathname
			URL url = this.getClass().getResource(theClassName + ".class");
			System.out.println( "main class location: " + url.toString() );
			
			// this is where the application will look for the fxml file
			// if you are running from Eclipse, this should be under the "bin" directory
			// Eclipse should have copied your fxml file to there from the src directory
			URL fxmlResourceURL = this.getClass().getResource(viewerFxml);	
			System.out.println( "fxml location: " + fxmlResourceURL.toString() );

			// if you think the pathnames are right, and the jart doesn't work
			// you can see what is in your jar with:
			// jar tf MYFILE.jar
			
			AnchorPane page = (AnchorPane) fxmlLoader.load(fxmlResourceURL.openStream());
			Scene scene = new Scene(page);
			scene.getStylesheets().add(MainProgram.class.getResource("stylesheet.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
			
			
			WorldController controller = (WorldController) fxmlLoader.getController();      			
			controller.initialize();//Sets initial scene
			controller.applySepia();//Applies sepia filter to Location images
 
			stage.show();
        
		} catch (IOException ex) {
		   Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
		   System.exit(1);
		}
	}
	
    public static void main(String args[]) {
     	launch(args);
     	System.exit(0);
    }
}


