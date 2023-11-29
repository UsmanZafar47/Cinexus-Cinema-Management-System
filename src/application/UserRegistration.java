package application;

import java.sql.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.collections.FXCollections;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import java.util.Arrays;


public class UserRegistration {

    public static boolean registerUser(String username, String password, String name, String cnic, String email, String role) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnecter.getConnection(); // Your existing connection method
            String sql = "INSERT INTO users (username, password, name, cnic, email, role) VALUES (?, ?, ?, ?, ?, ?)";
            
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password); // Hash the password in a real-world application
            stmt.setString(3, name);
            stmt.setString(4, cnic);
            stmt.setString(5, email);
            stmt.setString(6, role);

            int affectedRows = stmt.executeUpdate();

            return affectedRows == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }
}
