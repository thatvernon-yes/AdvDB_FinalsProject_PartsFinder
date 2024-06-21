package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AdminSide_controller {

	  @FXML
	    private Button addParts_button;

	    @FXML
	    private Button deleteParts_button;

	    @FXML
	    private ChoiceBox<?> location_choiceBox;

	    @FXML
	    private Button partsSearch_button;

	    @FXML
	    private TextField partsSearch_textField;

	    @FXML
	    private GridPane parts_gridPane;

}
