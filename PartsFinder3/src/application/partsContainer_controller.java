package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.io.InputStream;

public class partsContainer_controller {
	 
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
    
    public void setData (Parts part) throws IOException {
    	InputStream InputStream =part.getImage();
    	javafx.scene.image.Image image = new javafx.scene.image.Image(InputStream);
    	partContainer_image.setImage(image);
    	partsLocation.setText(part.getLocation());
    	partsName.setText(part.getName());
    	partsSrp.setText(String.valueOf(part.getSrp()));
    	partsStock.setText(String.valueOf(part.getStock()));
    }

}
