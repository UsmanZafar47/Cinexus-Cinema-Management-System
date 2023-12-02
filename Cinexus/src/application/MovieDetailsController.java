package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MovieDetailsController {
    @FXML
    private Label movieNameLabel;

    @FXML
    private Label cinemaNameLabel;

    @FXML
    private Label showtimesLabel;

    // You can add any necessary initialization or logic here

    // Method to update the UI components with movie details
    public void updateUI(String movieName, String cinemaName, String showtimes) {
        movieNameLabel.setText("Movie Name: " + movieName);
        cinemaNameLabel.setText("Cinema Name: " + cinemaName);
        showtimesLabel.setText("Showtimes:\n" + showtimes);
    }
}
