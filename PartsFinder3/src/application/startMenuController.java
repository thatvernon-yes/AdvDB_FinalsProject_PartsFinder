package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class startMenuController implements Initializable{
	
    private Stage stage;

    @FXML
    private Button btnAdmin;

    @FXML
    private Button btnCustomer;


    @FXML
    void adminSide(ActionEvent event) throws IOException{

    		try {
    			Parent root = FXMLLoader.load(getClass().getResource("adminLogin.fxml"));
    			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    		
    			stage.setScene(new Scene(root));
    			stage.show();
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
        }
    

    @FXML
    void customerSide(ActionEvent event) throws IOException{
		try {
			Parent root = FXMLLoader.load(getClass().getResource("userLogin.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		
			stage.setScene(new Scene(root));
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
