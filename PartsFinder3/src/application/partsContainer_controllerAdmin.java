package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class partsContainer_controllerAdmin {
	
    private Connection con;

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
	    private ImageView partContainer_image;

	    @FXML
	    private VBox partsDisplay;

	    @FXML
	    private Label partsLocation;

	    @FXML
	    private Label partsName;

	    @FXML
	    private Label partsSrp;

	    @FXML
	    private Label partsStock;
	    
	    @FXML
	    void deletePart(MouseEvent event) throws ClassNotFoundException, SQLException {
	    	deletePart();
	    }
    
    public void setData (Parts part) throws IOException {
    	InputStream InputStream =part.getImage();
    	javafx.scene.image.Image image = new javafx.scene.image.Image(InputStream);
    	partContainer_image.setImage(image);
    	partsLocation.setText(part.getLocation());
    	partsName.setText(part.getName());
    	partsSrp.setText(String.valueOf(part.getSrp()));
    	partsStock.setText(String.valueOf(part.getStock()));
    }
    
    public void AdminsetData (Parts part) throws IOException {
    	InputStream InputStream =part.getImage();
    	javafx.scene.image.Image image = new javafx.scene.image.Image(InputStream);
    	partContainer_image.setImage(image);
    	partsLocation.setText(part.getLocation());
    	partsName.setText(String.valueOf(part.getId()));
    	partsSrp.setText(String.valueOf(part.getSrp()));
    	partsStock.setText(String.valueOf(part.getStock()));
    }

    
    public void deletePart() throws ClassNotFoundException, SQLException {
    	
        int result = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete?", "Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
             if(result == JOptionPane.YES_OPTION){
             	connectSQL();
                PreparedStatement pst;
                ResultSet rs;
                
            	int id = Integer.valueOf(partsName.getText());
            	
            	pst = con.prepareStatement("DELETE FROM parts WHERE parts_ID = ?");
            	
            	pst.setInt(1, id);
            	
            	pst.executeUpdate();

            	

    	
    }
    }
}
