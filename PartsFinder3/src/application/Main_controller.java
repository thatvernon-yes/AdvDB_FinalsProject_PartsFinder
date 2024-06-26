package application;



import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
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
	    
	    @FXML
	    private Button generalSearch_button;
	    
	    @FXML
	    private ChoiceBox<String> sort_choiceBox;
	    
	    @FXML
	    private Button sortConfirm_button;
	   
	    @FXML
	    private Label username_label;
	    
	    @FXML
	    private ImageView userImage;

	    ArrayList<Parts> partsDisplay;
	    
	    GraphRepresentation graph = new GraphRepresentation();
	    
	    Database DB = new Database();
	   
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
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
		

//-----FOR LISTING THE AVAILABLE LOCATION IN THE CHOICE BOX--------------------
		
		ArrayList <String> locations = new ArrayList<String>();
		ResultSet rs;
		
		//needs to location update for the database P E N D I N G
		try {
			rs = DB.RSquery("SELECT address FROM `parts`");
			while(rs.next()) {
				
				if(!locations.contains(rs.getString("address"))) {
					locations.add(rs.getString("address"));
				
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


		
//-----FOR LISTING THE PRICES IN THE CHOICE BOX--------------------

		ArrayList <Integer> prices = new ArrayList <Integer>();
		
		for(int i = 0; i <=10000; i+=100) {
			prices.add(i);
		}
		
		upperPriceLimit_choiceBox.getItems().addAll(prices);
		lowerPriceLimit_choiceBox.getItems().addAll(prices);
		
//-----FOR GRAPH REPRESENTATION OF A MAP--------------------		
		
		
        
//-----FOR LISTING THE PRICES IN THE CHOICE BOX--------------------    
        ArrayList <String> sortChoices = new ArrayList<String>();
        sortChoices.add("Name");
        sortChoices.add("Price");
        sortChoices.add("Location");
      	
        sort_choiceBox.getItems().addAll(sortChoices);
       
}
	
	public void setUsernameandLocation(String username, String location) {
		username_label.setText(username);
		location_choiceBox.setValue(location);
	}
	
	public void partsDisplayWithLocation (ActionEvent confirmLocation) {
		
		parts_gridPane.getChildren().clear(); //removes the current contents of the grid 

		String location = location_choiceBox.getValue();
		ArrayList<Parts> partsLocationDisplay = null;
		
		if (location_choiceBox.getValue() == null) {
			JOptionPane.showMessageDialog(null, "Please Select Location");
			
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
			
		}

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
		String partName = partsSearch_textField.getText();
		ArrayList<Parts> partsSortDisplay = null;
		
		if (partsSearch_textField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Search Field is Empty");
			
		}
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
		partsSearch_textField.clear();
		location_choiceBox.getSelectionModel().clearSelection();
		upperPriceLimit_choiceBox.getSelectionModel().clearSelection(); 
		lowerPriceLimit_choiceBox.getSelectionModel().clearSelection();
		sort_choiceBox.getSelectionModel().clearSelection();
		
		
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
	
	public void sortPrice(ActionEvent sortPrice) {
			
			
			int lowerPrice = 0;
			int upperPrice = 999999;
			
			
			ArrayList<Parts> partsSortDisplay = null;
			
			
			if(!(lowerPriceLimit_choiceBox.getValue()==null) && !(upperPriceLimit_choiceBox.getValue()==null)) {
				parts_gridPane.getChildren().clear(); //removes the current contents of the grid 

					try {	
						
						lowerPrice = lowerPriceLimit_choiceBox.getValue();
						upperPrice = upperPriceLimit_choiceBox.getValue();
						partsSortDisplay = DB.sortPrice(lowerPrice, upperPrice);
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
					
			}else if(!(lowerPriceLimit_choiceBox.getValue()==null) && (upperPriceLimit_choiceBox.getValue()==null)) {
				parts_gridPane.getChildren().clear(); //removes the current contents of the grid 

				
				try {	
					
					lowerPrice = lowerPriceLimit_choiceBox.getValue();
					
					partsSortDisplay = DB.sortLowerPriceOnly(lowerPrice);
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
				
			} else if((lowerPriceLimit_choiceBox.getValue()==null) && !(upperPriceLimit_choiceBox.getValue()==null)) {
				parts_gridPane.getChildren().clear(); //removes the current contents of the grid 

				try {	
					
					upperPrice = upperPriceLimit_choiceBox.getValue();
					partsSortDisplay = DB.sortUpperPriceOnly(upperPrice);
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
				
			} else {
				JOptionPane.showMessageDialog(null, "Please select a lower and upper price");
			}
	}
	
	public void generalSearch(ActionEvent genSearch) throws ClassNotFoundException, SQLException  {
		

		if (partsSearch_textField.getText() != null && location_choiceBox.getValue() != null && upperPriceLimit_choiceBox.getValue() != null && lowerPriceLimit_choiceBox.getValue() != null) {
			try {
				
				parts_gridPane.getChildren().clear(); //removes the current contents of the grid 

				String partName = partsSearch_textField.getText();
				String location = location_choiceBox.getValue();
				int upperPrice = upperPriceLimit_choiceBox.getValue();
				int lowerPrice = lowerPriceLimit_choiceBox.getValue();
				
				
				partsDisplay =  DB.generalSearchSortedDisplay(partName, location, upperPrice, lowerPrice);
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
		}
			
			else {
				JOptionPane.showMessageDialog(null, "Please enter all the search fields");
		}
	
	}

	public void sort(ActionEvent sort) throws ClassNotFoundException, SQLException {

		parts_gridPane.getChildren().clear(); //removes the current contents of the grid 
		
//				City A = Valenzuela
//				City B = Quezon City
//				City C = Manila
//				City D = Pasay
//				City E = Mandaluyong
//				City F = Cubao 
//				City G = Antipolo

		
		// ADDING CITIES TO THE GRAPH
		
		
        graph.addEdge("City A", "City B", 9);
        graph.addEdge("City A", "City C", 14);
        graph.addEdge("City A", "City D", 24);
        graph.addEdge("City A", "City E", 20);
        graph.addEdge("City A", "City F", 17);
        graph.addEdge("City A", "City G", 23);
        
        graph.addEdge("City B", "City C", 14);
        graph.addEdge("City B", "City D", 23);
        graph.addEdge("City B", "City E", 15);
        graph.addEdge("City B", "City F", 8);
        graph.addEdge("City B", "City G", 15);
        
     
        graph.addEdge("City C", "City D", 9);
        graph.addEdge("City C", "City E", 8);
        graph.addEdge("City C", "City F", 10);
        graph.addEdge("City C", "City G", 20);
       
          
        graph.addEdge("City D", "City E", 8);
        graph.addEdge("City D", "City F", 13);
        graph.addEdge("City D", "City G", 20);
        
        graph.addEdge("City F", "City E", 8);
        graph.addEdge("City F", "City G", 9);
        
        graph.addEdge("City E", "City G", 15);
//        graph.addEdge("City A", "City B", 9);
//        graph.addEdge("City A", "City C", 14);
//        graph.addEdge("City A", "City D", 24);
//        graph.addEdge("City A", "City E", 20);
//        graph.addEdge("City A", "City F", 17);
//        graph.addEdge("City A", "City G", 23);
//        
//        graph.addEdge("City B", "City C", 15);
//        graph.addEdge("City B", "City D", 24);
//        graph.addEdge("City B", "City E", 18);
//        graph.addEdge("City B", "City F", 11);
//        graph.addEdge("City B", "City G", 16);
//        
//     
//        graph.addEdge("City C", "City D", 9);
//        graph.addEdge("City C", "City E", 6);
//        graph.addEdge("City C", "City F", 10);
//        graph.addEdge("City C", "City G", 20);
//       
//          
//        graph.addEdge("City D", "City E", 8);
//        graph.addEdge("City D", "City F", 13);
//        graph.addEdge("City D", "City G", 20);
//        
//        graph.addEdge("City F", "City E", 7);
//        graph.addEdge("City F", "City G", 5);
//        
//        graph.addEdge("City E", "City G", 19);
		
		
		ArrayList<Parts> partsSortDisplay = null;
		String location = location_choiceBox.getValue();
		String choice = sort_choiceBox.getValue();
		
		
		if (sort_choiceBox.getValue() == null || location_choiceBox.getValue() == null) {
			JOptionPane.showMessageDialog(null, "Sort and Location Choicebox is empty");
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
		}
		else {
			try {
				
				partsSortDisplay = DB.SortedDisplay(choice, graph, location);
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
		

	}

	
//	public void test (ActionEvent confirmPartName) {
//		
//		parts_gridPane.getChildren().clear(); //removes the current contents of the grid 
//		ArrayList<Parts> partsSortDisplay = null;
//		
//		try {
//			
//			partsSortDisplay = DB.TEST();
//			int column = 0;
//			int row = 1;
//			
//			for(Parts part : partsSortDisplay) {
//				
//				FXMLLoader fxmlLoader = new FXMLLoader();
//				fxmlLoader.setLocation(getClass().getResource("parts_container.fxml"));
//				VBox partsDisplay = fxmlLoader.load();
//				partsContainer_controller partsContainer_controller = fxmlLoader.getController();
//				partsContainer_controller.setData(part);
//				
//				if(column == 3) {
//					column = 0;
//					++row;
//				}
//				
//				parts_gridPane.add(partsDisplay, column++, row);
//				GridPane.setMargin(parts_gridPane, new Insets(10));
//				
//			}
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
	
	
}
