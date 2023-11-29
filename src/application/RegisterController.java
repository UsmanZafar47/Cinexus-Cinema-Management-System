package application;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.collections.FXCollections;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.sql.SQLException;
import java.util.Arrays;


public class RegisterController {

    @FXML private TextField nameField;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private TextField cnicField;
    @FXML private TextField emailField;
    @FXML private ComboBox<String> roleComboBox;

    
    public void initialize() {
        roleComboBox.setItems(FXCollections.observableArrayList("Cinema Manager", "Admin", "Event Organizer", "Customer"));
    }
    
    @FXML
    private void handleRegisterAction() throws SQLException {
        String name = nameField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText(); // Consider hashing this
        String cnic = cnicField.getText();
        String email = emailField.getText();
        String role = roleComboBox.getValue();


        
        boolean success = UserRegistration.registerUser(username, password, name, cnic, email, role);
        if (success) {
            System.out.println("Registered Successfully");
        } else {
            System.out.println("Registration failed");
            // Registration failed
        }
    }
}
