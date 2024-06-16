package application;
	
import java.sql.SQLException;

import javax.crypto.ExemptionMechanism;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = FXMLLoader.load(getClass().getResource("test.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException  {
		
		
		//for testing
		Database DB = new Database();
		//DB.query("SELECT * FROM `parts` WHERE parts_ID = 1");
		System.out.println("      ");
		DB.getDisplayInfoQuery(); 
		
		//launch(args);
	}
}
