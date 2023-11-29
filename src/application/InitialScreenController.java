package application;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.collections.FXCollections;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import java.util.Arrays;
import java.awt.Button;
import java.sql.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class InitialScreenController {


	@FXML
    private Node loginButton;

    @FXML
    private Node signupButton;

    @FXML
    private Node addcinemaButton;

    @FXML
    private void loadLogin() {
        loadScene("Login.fxml", loginButton);
    }

    @FXML
    private void loadSignup() {
        loadScene("UserRegisteration.fxml", signupButton);
    }

    
    @FXML
    private void loadAddCinema() {
        loadScene("AddCinema.fxml", signupButton);
    }

    
    private void loadScene(String fxmlFile, Node node) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
