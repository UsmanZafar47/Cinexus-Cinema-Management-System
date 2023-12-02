package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MovieListController {
    @FXML
    private VBox movieListView;

    public void initialize() {
        // Fetch movie data from the database
        List<Movie> movies = fetchMoviesFromDatabase();

        // Create movie entries and add them to the movieListView
        for (Movie movie : movies) {
            HBox movieEntry = createMovieEntry(movie);
            movieListView.getChildren().add(movieEntry);
        }
    }

    private List<Movie> fetchMoviesFromDatabase() {
        // Define your database connection parameters
        String jdbcUrl = "jdbc:mysql://localhost:3306/Cinexus";
        String dbUser = "root";
        String dbPassword = "4326";

        List<Movie> movies = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT movie_id, title FROM movies");

            while (resultSet.next()) {
                int id = resultSet.getInt("movie_id");
                String title = resultSet.getString("title");

                // Create a new movie object and add it to the list
                Movie movie = new Movie(id, title, 0, " "); // Duration and rating are set to default values
                movies.add(movie);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return movies;
    }
    private HBox createMovieEntry(Movie movie) {
        HBox entry = new HBox(10);
        VBox container = new VBox(5); // Adjust the spacing as needed


        // Movie ID and Title
        Label movieInfoLabel = new Label("Movie ID: " + movie.getId() + "\nTitle: " + movie.getTitle());
        movieInfoLabel.setStyle("-fx-font-weight: bold;");
        
        // Buttons
        Button bookSeatsButton = new Button("Book Seats");
        Button viewMovieButton = new Button("View Movie");

        bookSeatsButton.setStyle("-fx-background-color: #000000; -fx-text-fill: white;"); // Green background color
        viewMovieButton.setStyle("-fx-background-color: #000000; -fx-text-fill: white;"); // Red background color
        container.getChildren().addAll(movieInfoLabel, bookSeatsButton, viewMovieButton);

        container.setMargin(movieInfoLabel, new Insets(0, 10, 0, 10)); // Adjust the values as needed
        container.setMargin(bookSeatsButton, new Insets(0, 10, 0, 10));
        container.setMargin(viewMovieButton, new Insets(0, 10, 0, 10));
        // Set the background color for the container
        container.setStyle("-fx-background-color: #FFD700;"); // Dark yellow background color

        // Add the container to the entry HBox
        entry.getChildren().add(container);
        // Add buttons and label to the entry
        entry.getChildren().addAll(movieInfoLabel, bookSeatsButton, viewMovieButton);

        
        return entry;
    }

    private Movie[] addMovieToArray(Movie[] movies, Movie newMovie) {
        int length = movies.length;
        Movie[] newMovies = new Movie[length + 1];

        System.arraycopy(movies, 0, newMovies, 0, length);
        newMovies[length] = newMovie;

        return newMovies;
    }
}
