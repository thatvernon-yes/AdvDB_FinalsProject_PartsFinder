package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class adminLoginController implements Initializable {

    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;
    private Stage stage;



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
    private Button btnLogin;

    @FXML
    private PasswordField txtPass;

    @FXML
    private TextField txtUname;

    @FXML
    void login(ActionEvent event) throws IOException {
        String username = txtUname.getText();
        String password = txtPass.getText();

        if (username.equals("") && password.equals("")) {
            JOptionPane.showMessageDialog(null, "Username or Password Blank");
        } else {
            try {
                connectSQL();

                pst = con.prepareStatement("SELECT * FROM admin WHERE admin_username=? AND admin_password=?");

                pst.setString(1, username);
                pst.setString(2, password);

                rs = pst.executeQuery();

                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Login Successful");
                    
                    Stage stage = (Stage) btnLogin.getScene().getWindow();
                    stage.close();
                    
                    loadAdminView(event);
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Login Failed");
                    txtUname.setText("");
                    txtPass.setText("");
                    txtUname.requestFocus();
                }

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadAdminView(ActionEvent event) throws IOException{
		try {
			Parent root = FXMLLoader.load(getClass().getResource("adminSide.fxml"));
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
