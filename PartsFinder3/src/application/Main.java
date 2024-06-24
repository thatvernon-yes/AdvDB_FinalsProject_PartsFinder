package application;
	


import java.sql.SQLException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("userLogin.fxml"));
			//Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(new Scene(root));
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException  {
 
//String sample = "sample";

//	System.out.println(" SELECT * FROM `parts` WHERE location LIKE " +  "\"" + "%"  + location + "%" + "\"" + " AND parts_name LIKE " + "\"" + "%"  + partName + "%" + "\"" + " AND parts_srp = " +  upperPrice + " BETWEEN " + lowerPrice   );
//String test = "SELECT * FROM parts ORDER BY FIELD (location, " +  "\"" + sample + "\"" + ", " +  "\"" + sample + "\"" + ", "+  "\"" + sample + "\"" +", " +  "\"" + sample + "\"" + ", " +  "\"" + sample + "\"" + ", " +  "\"" + sample + "\"" + ", " +  "\"" + sample + "\"" + ")";
//System.out.println(test);	
	launch(args);		 
		
	}
}
