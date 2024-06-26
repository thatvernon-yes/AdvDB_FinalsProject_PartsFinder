package application;

import javafx.scene.image.Image;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.io.InputStream;


import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;



public class addPartController implements Initializable{
	
    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;
    private ImageIcon fomat =null;
    File f = null;
    String path = null;
    String fname = null;
    int s = 0;
    byte[] pimage = null;
    
    
    
    
	
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
    private Button addBtn;

    @FXML
    private TextField category_txt;

    @FXML
    private TextField description_txt;

    @FXML
    private ChoiceBox<String> location_choiceBox;

    @FXML
    private TextField partName_txt;

    @FXML
    private TextField srp_txt;

    @FXML
    private TextField stock_txt;
    
    @FXML
    private ImageView imageLabel;
    

    @FXML
    private TextField imagePath;
    
    private File selectedFile;
    
 
    
    @FXML
    void browse(ActionEvent event) throws FileNotFoundException, ClassNotFoundException, SQLException {
    	connectSQL();
 		
    	
    	FileChooser fc = new FileChooser();
    	fc.setInitialDirectory(new File("C:\\"));
    	selectedFile = fc.showOpenDialog(null);
  
		InputStream stream = new FileInputStream(selectedFile);
		Image image = new Image(stream);
		imageLabel.setImage(image);
		imagePath.setText(selectedFile.getPath());


    }
    

    @FXML
    void addPart(ActionEvent event) throws FileNotFoundException, SQLException, ClassNotFoundException {
    	
    	connectSQL();
    	
    	
    	String partName = partName_txt.getText();
    	
    	String stock = stock_txt.getText();
    	int partStock = Integer.parseInt(stock);
    	
    	String partDescription = description_txt.getText();
    	
    	String srp = srp_txt.getText();
    	float partSrp = Float.parseFloat(srp);
    	
    	String address = (String) location_choiceBox.getValue();
    	String category = category_txt.getText();
    	
//    	System.out.println(partName + " " + partStock + " " + partDescription + " " + partSrp + " " + address + " " + category + "" + path);
    	
    	String path = selectedFile.getPath();
    	FileInputStream fis = new FileInputStream(selectedFile);
    	
    	
    	pst = con.prepareStatement("INSERT INTO parts (`parts_ID`, `parts_name`, `parts_stock`, `parts_description`, `parts_srp`, `address`, `category`, `image`) "
    			+ "VALUES (NULL, ?,?,?,?,?,?,? )");
    	
    	pst.setString(1, partName);
    	pst.setInt(2, partStock);
    	pst.setString(3, partDescription);
    	pst.setFloat(4, partSrp);
    	pst.setString(5, address);
    	pst.setString(6, category);
    	pst.setBinaryStream(7, fis);
    	
    	pst.executeUpdate();
    	
    	JOptionPane.showMessageDialog(null, "Part Added Succesfully");
    	
    	
    	
    	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
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
		
		location_choiceBox.getItems().addAll(locations);

}
		
	}