package application;



import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;



public class Main_controller implements Initializable {

	  @FXML
	    private Button confirmSort_button;

	    @FXML
	    private ChoiceBox<String> location_choiceBox;

	    @FXML
	    private Button partSearchConfirm_button;

	    @FXML
	    private Button partSearchLocation_button;

	    @FXML
	    private TextField partsSearch_textField;

	    @FXML
	    private GridPane parts_gridPane;
	    
	    @FXML
	    private Button gridDefault_button;
	    
	    @FXML
	    private Button priceRangeConfirm_button;

	    @FXML
	    private ChoiceBox<Integer> upperPriceLimit_choiceBox;
	    
	    @FXML
	    private ChoiceBox<Integer> lowerPriceLimit_choiceBox;


   
	    ArrayList<Parts> partsDisplay;
    
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		Database DB = new Database();
		
//-----FOR DISPLAYING THE ITEMS FROM THE DATABASE -----------------------
		try {
		
			partsDisplay =  DB.createPartsClassesforDisplay();
			int column = 0;
			int row = 1;
	
			for(Parts part : partsDisplay) {
				
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("parts_container.fxml"));
				VBox partsDisplay = fxmlLoader.load();
				partsContainer_controller partsContainer_controller = fxmlLoader.getController();
				partsContainer_controller.setData(part);
				
				if(column == 3) {
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
//-----END OF "FOR DISPLAYING THE ITEMS FROM THE DATABASE"--------------------	
		

//-----FOR LISTING THE AVAILABLE LOCATION IN THE CHOICE BOX--------------------
		
		ArrayList <String> locations = new ArrayList<String>();
		ResultSet rs;
		
		//needs to location update for the database P E N D I N G
		try {
			rs = DB.RSquery("SELECT location FROM `parts`");
			while(rs.next()) {
				
				if(!locations.contains(rs.getString("location"))) {
					locations.add(rs.getString("location"));
				
				}
				
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		location_choiceBox.getItems().addAll(locations);

//-----END OF "FOR LISTING THE AVAILABLE LOCATION IN THE CHOICE BOX"--------------------	

		
//-----FOR LISTING THE PRICES IN THE CHOICE BOX--------------------

		
		
		
		
	}
	
	
	public void partsDisplayWithLocation (ActionEvent confirmLocation) {
		
		parts_gridPane.getChildren().clear(); //removes the current contents of the grid 
		
		Database DB = new Database();
		String location = location_choiceBox.getValue();
		ArrayList<Parts> partsLocationDisplay = null;
		
		try {
			
			partsLocationDisplay = DB.createPartsClassesforDisplay( location);
			int column = 0;
			int row = 1;
			
			for(Parts part : partsLocationDisplay) {
				
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("parts_container.fxml"));
				VBox partsDisplay = fxmlLoader.load();
				partsContainer_controller partsContainer_controller = fxmlLoader.getController();
				partsContainer_controller.setData(part);
				
				if(column == 3) {
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
	
	public void partsDisplaySortedName (ActionEvent confirmPartName) {
		
		parts_gridPane.getChildren().clear(); //removes the current contents of the grid 
		
		Database DB = new Database();
		String partName = partsSearch_textField.getText();
		ArrayList<Parts> partsSortDisplay = null;
		
		try {
			
			partsSortDisplay = DB.partsForSortedDisplay("parts_name",partName);
			int column = 0;
			int row = 1;
			
			for(Parts part : partsSortDisplay) {
				
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("parts_container.fxml"));
				VBox partsDisplay = fxmlLoader.load();
				partsContainer_controller partsContainer_controller = fxmlLoader.getController();
				partsContainer_controller.setData(part);
				
				if(column == 3) {
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
	
	public void restoreDefaultGrid (ActionEvent restore) {
		
		parts_gridPane.getChildren().clear(); //removes the current contents of the grid 
		Database DB = new Database();
		
		//-----FOR DISPLAYING THE ITEMS FROM THE DATABASE -----------------------
				try {
				
					partsDisplay =  DB.createPartsClassesforDisplay();
					int column = 0;
					int row = 1;
			
					for(Parts part : partsDisplay) {
						
						FXMLLoader fxmlLoader = new FXMLLoader();
						fxmlLoader.setLocation(getClass().getResource("parts_container.fxml"));
						VBox partsDisplay = fxmlLoader.load();
						partsContainer_controller partsContainer_controller = fxmlLoader.getController();
						partsContainer_controller.setData(part);
						
						if(column == 3) {
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
		//-----END OF "FOR DISPLAYING THE ITEMS FROM THE DATABASE"--------------------	
		
	}
	
}
