package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieDetailsPage extends Application {
    private int movieId;

    public MovieDetailsPage() {
        // You can initialize any default values or leave it empty
    }

    public MovieDetailsPage(int movieId) {
        this.movieId = movieId;
    }

    @Override
    public void start(Stage primaryStage) {
        // Create UI components
        Label movieNameLabel = new Label();
        Label cinemaNameLabel = new Label();
        Label showtimesLabel = new Label();

        try {
            // Connect to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/Cinexus", "root", "4326");

            // Query to fetch movie details and showtimes
            String query = "SELECT m.title, c.name AS cinema_name, GROUP_CONCAT(s.showtime SEPARATOR ', ') AS showtimes " +
                    "FROM movies m " +
                    "JOIN showtimes s ON m.movie_id = s.movie_id " +
                    "JOIN cinema c ON s.cinema_id = c.cinema_id " +
                    "GROUP BY m.title, c.name;";

            // Execute the query
            ResultSet resultSet = connection.createStatement().executeQuery(query);

            // Process the result
            if (resultSet.next()) {
                String movieName = resultSet.getString("title");
                String cinemaName = resultSet.getString("cinema_name");
                String showtimes = resultSet.getString("showtimes");

                movieNameLabel.setText("Movie Name: " + movieName);
                cinemaNameLabel.setText("Cinema Name: " + cinemaName);
                showtimesLabel.setText("Showtimes:\n" + showtimes);
            }

            // Close the database connection
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Create a layout for the page
        VBox root = new VBox(10);
        root.getChildren().addAll(movieNameLabel, cinemaNameLabel, showtimesLabel);

        // Create the scene
        Scene scene = new Scene(root, 400, 200);

        // Set the scene in the primary stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Movie Details");
        primaryStage.show();
    }

    public static void main(String[] args) {
        // Provide the movie ID when initializing the page
        int movieId = 1; // Replace with the desired movie ID
        launch(args);
    }
}
