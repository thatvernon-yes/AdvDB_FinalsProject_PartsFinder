package application;



import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle; 
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;


public class Main_controller implements Initializable {

	@FXML
	private Circle tempCircle;
	//private TextField tempSearch;
	
    @FXML
    private TextField tempSearch;
    
//    @FXML
//    private GridPane partsDisplay_mainContainer;
    
    @FXML
    private GridPane parts_gridPane;
    
    ArrayList<Parts> partsDisplay;
    
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		try {
			Database DB = new Database();
			
			partsDisplay =  DB.createPartsClassesforDisplay();
			int column = 0;
			int row = 1;
	
			for(Parts part : partsDisplay) {
				
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("parts_container.fxml"));
				VBox partsDisplay = fxmlLoader.load();
				partsContainer_controller partsContainer_controller = fxmlLoader.getController();
				partsContainer_controller.setData(part);
				
				if(column == 5) {
					column = 0;
					++row;
				}
				
				parts_gridPane.add(partsDisplay, column++, row);
				GridPane.setMargin(parts_gridPane, new Insets(10));
				
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
			
		
	}
	
//	private ArrayList<Parts> partsDisplay() throws ClassNotFoundException, SQLException{
//		
//		Database DB = new Database();
//		
//		ArrayList<Parts> parts = new ArrayList<Parts>();
//		parts = DB.createPartsClassesforDisplay();
//		
//		return parts;
//		
//	}
	
	
}
