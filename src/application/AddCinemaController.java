package application;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddCinemaController {

    @FXML private TextField cinemaNameField;
    @FXML private TextField cinemaLocationField;
    @FXML private TextField manager_IDArea;
    @FXML private TextField manager_nameArea;

    

    @FXML
    private void handleSubmit() {
        String name = cinemaNameField.getText();
        String location = cinemaLocationField.getText();
        String manager_id = manager_IDArea.getText();
        String manager_name = manager_nameArea.getText();

        // Check if payment is not null and approved
        
    }
    
    
    

    private void addNewCinema(String name, String location, String manager_id, String manager_name) {
        // Database connection should be handled properly, e.g., using a connection pool
        try (Connection conn = DatabaseConnecter.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                 "INSERT INTO cinema (name, location, manager_id, manager_name) VALUES (?, ?, ?, ?)"
             )) {
            stmt.setString(1, name);
            stmt.setString(2, location);
            stmt.setString(3, manager_id);
            stmt.setString(4, manager_name);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 1) {
                // Handle success (e.g., show confirmation message)
            } else {
                // Handle failure (e.g., show error message)
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exception (e.g., show error message)
        }
    }
}
