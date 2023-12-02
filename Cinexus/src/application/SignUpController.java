package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.collections.FXCollections;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SignUpController 
{
    @FXML private TextField nameField;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField repasswordField;
    @FXML private TextField cnicField;
    @FXML private TextField emailField;
    @FXML private ComboBox<String> roleComboBox;
    @FXML public Text actionTarget;
    @FXML public Node loginButton;
    @FXML public Node signupButton;

    
    public void initialize() 
    {
        roleComboBox.setItems(FXCollections.observableArrayList("Cinema Manager", "Admin", "Event Organizer", "Customer"));
    }
    
    @FXML
    public void onSignUp()
    {
    	String name = nameField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();
        String repassword = repasswordField.getText();
        String cnic = cnicField.getText();
        String email = emailField.getText();
        String role = roleComboBox.getValue();
        
        if((password.equals(repassword)))
        {	
	        User NewUser = factory.createNewUser(name, username, password, cnic, email, role);
	
	        if (NewUser.getUserID() == -1) 
	            actionTarget.setText("Registered Failed");
	        else
	            actionTarget.setText("Registration Successfully! You Can Now Log In.");
        }
        else
            actionTarget.setText("Passwords do not match.");
    }
    
    @FXML
    public void loadLogin() 
    {
    	try {
            Parent root = FXMLLoader.load(getClass().getResource("/uipackage/Login.fxml"));
            Stage stage = (Stage) loginButton.getScene().getWindow();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/uipackage/designLayout.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
