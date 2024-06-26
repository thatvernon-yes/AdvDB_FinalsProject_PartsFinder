package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class userLoginController implements Initializable {

    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;
    private Stage stage;
    private Parent root;



    public void connectSQL() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Credentials
        String url = "jdbc:mysql://localhost:3306/toyotainventorysystem";
        String username = "root";
        String password = "";

        // Establish the connection
        this.con = DriverManager.getConnection(url, username, password);
    }

    @FXML
    private Button backBtn;
    
    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField txtPass;

    @FXML
    private TextField txtUname;
    
    @FXML
    private ChoiceBox<String> userLocation_choiceBox;

    @FXML
    void back(ActionEvent event) {
    	try {
    Parent root = FXMLLoader.load(getClass().getResource("startMenu.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    
    stage.setScene(new Scene(root));
    stage.show();
    
    
    } catch(Exception e) {
    e.printStackTrace();
    }

    }
    @FXML
    void login(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
    	
        String username = txtUname.getText();
        String password = txtPass.getText();
        String username2 = null;
        String password2 = null;
        String location2 = null;
        
//        

        // error when username and pass is valid but no value in choicebox
        if (username.equals("") || password.equals("")) {
            JOptionPane.showMessageDialog(null, "Username or Password Blank");
        } else {
            try {
                connectSQL();
                
//                String location = userLocation_choiceBox.getValue().toString();
                ResultSet rs2;

                pst = con.prepareStatement("SELECT * FROM customer WHERE customer_username = ? AND customer_password = ?");
                
                
//                String update = ("UPDATE customer SET location = "+"\"" + location + "\" WHERE customer_username = "+"\"" + username + "\"");
                

                
                pst.setString(1, username);
                pst.setString(2, password);

                rs2 = pst.executeQuery();
                
                while (rs2.next()) {
                 username2 = rs2.getString("customer_username");
                 password2 = rs2.getString("customer_password");
                 location2 = rs2.getString("location");
                }

                if(username == username2 && password == password2) {

                    if(userLocation_choiceBox.getValue() != null) {
                  
                        JOptionPane.showMessageDialog(null, "Login Successful");
                        
                        Stage stage = (Stage) btnLogin.getScene().getWindow();
                        stage.close();
                        
//                        System.out.println(rs);
                        
                        loadMainView(event);

                  
                  }
                    else {
                    	if(location2 != null){
                            userLocation_choiceBox.setValue(location2);
                    	}
                    	else {
                        	JOptionPane.showMessageDialog(null, "Please select location");
                    	}
                    }
                  
              }
            

                    
                                        
           
                    JOptionPane.showMessageDialog(null, "Login Failed");
                    txtUname.setText("");
                    txtPass.setText("");
                    txtUname.requestFocus();
                }

             catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
             }
            }
    }
    
    

    private void loadMainView(ActionEvent event) throws IOException, ClassNotFoundException, SQLException{
    	

    	connectSQL();

        String username = txtUname.getText();
        String location = userLocation_choiceBox.getValue().toString();
        

        
//        System.out.println(username + location);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Main2.fxml"));
        root = loader.load();
        
    	Main_controller mc = loader.getController();
    	
    	mc.setUsernameandLocation(username, location);
    	
        
		try {
//			Parent root = FXMLLoader.load(getClass().getResource("Main2.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		
			stage.setScene(new Scene(root));
			stage.show();
			

		} catch(Exception e) {
			e.printStackTrace();
		}
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	//-----FOR LISTING THE AVAILABLE LOCATION IN THE CHOICE BOX--------------------
		
    			ArrayList <String> locations = new ArrayList<String>();
    			ResultSet rs;
    			Database DB = new Database();
    			
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
    			
    			userLocation_choiceBox.getItems().addAll(locations);

    }
    

    
}
