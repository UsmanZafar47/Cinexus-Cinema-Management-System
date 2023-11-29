package application;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LoginController {


//    @FXML private TextField usernameField;
//    @FXML private PasswordField passwordField;
    @FXML private ComboBox<String> roleComboBox;
//    @FXML
//    private Text actionTarget;

	
	@FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private CheckBox rememberMeCheckBox;
    @FXML private Text forgotPasswordText;
    @FXML private Text signUpText;
    @FXML private Text actionTarget;

    
    @FXML private Button loginButton;
    
    public void initialize() {
        roleComboBox.setItems(FXCollections.observableArrayList("Cinema Manager", "Admin", "Event Organizer", "Customer"));
    }

        

    @FXML
    protected void onLoginButtonClicked() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String role = roleComboBox.getValue();
       // boolean rememberMe = rememberMeCheckBox.isSelected();
        
        boolean isAuthenticated = User.authenticate(username, password, role);
        if (isAuthenticated) {
            actionTarget.setText("Login successful as " + role + "!");
            // Handle successful login based on role
        } else {
            actionTarget.setText("Login failed.");
            // Handle failed login
        }
    }
    
//    protected void onForgotPasswordClicked(ActionEvent event) {
//        // Your forgot password logic here
//    }
//
//    @FXML
//    protected void onSignUpClicked(ActionEvent event) {
//        // Your sign up logic here
//    }
}



