package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.List;
import javafx.application.Application;
import dbhandler.MovieDatabaseConnecter;
import dbhandler.UserDatabaseConnecter;

public class CustomerController
{
	private User CustomerInfo;
	@FXML private TextField ticketNoField;
    @FXML private Node Trackticket;
    @FXML private VBox movieListView;

	public void setLoginUser(User user) 
	{
		this.CustomerInfo = factory.createUser(user);
	}
	
    public void initialize() {
        MovieDatabaseConnecter MovieDB = new MovieDatabaseConnecter();
        List<Movie> movies = MovieDB.fetchMoviesFromDatabase();
        
        for (Movie movie : movies) 
        {
            HBox movieEntry = createMovieEntry(movie);
            movieListView.getChildren().add(movieEntry);
        }
    }
    private HBox createMovieEntry(Movie movie) 
    {
        HBox entry = new HBox(10);
        VBox container = new VBox(5);

        Label movieInfoLabel = new Label("Movie ID: " + movie.getId() + "\nTitle: " + movie.getTitle());
        movieInfoLabel.setStyle("-fx-font-weight: bold;");
        Button bookSeatsButton = new Button("Book Seats");
        Button viewMovieButton = new Button("View Movie");
        
        
        bookSeatsButton.setStyle("-fx-background-color: #FF0000; -fx-text-fill: white;");
        viewMovieButton.setStyle("-fx-background-color: #FF0000; -fx-text-fill: white;");

        container.getChildren().addAll(movieInfoLabel, bookSeatsButton, viewMovieButton);
        viewMovieButton.setOnAction(event -> viewMovie(movie.getId()));

        
        container.setMargin(movieInfoLabel, new Insets(0, 10, 0, 10));
        container.setMargin(bookSeatsButton, new Insets(0, 10, 0, 10));
        container.setMargin(viewMovieButton, new Insets(0, 10, 0, 10));

        container.setStyle("-fx-background-color: #FFD700;");

        entry.getChildren().add(container);
        entry.getChildren().addAll(movieInfoLabel, bookSeatsButton, viewMovieButton);
        
        return entry;
    }

    private Movie[] addMovieToArray(Movie[] movies, Movie newMovie) 
    {
        int length = movies.length;
        Movie[] newMovies = new Movie[length + 1];

        System.arraycopy(movies, 0, newMovies, 0, length);
        newMovies[length] = newMovie;

        return newMovies;
    }
    
    @FXML
    public void trackTicket() {
        String ticketNo = ticketNoField.getText();
        List<String> Controlleritems = null;
        Controlleritems.add(""+CustomerInfo.getUserID());
        Controlleritems.add(ticketNo);
        
    	loadNewPage("SignUp", Trackticket, Controlleritems);
    }

    @FXML
    private void viewMovie(int movieId) {
        try {
            // Load the FXML file for the "MovieDetailsPage" page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/uipackage/MovieDetailsPage.fxml"));
            Parent root = loader.load();

            // Get the controller for the "MovieDetailsPage" page
            MovieDetailsPage movieDetailsController = loader.getController();

            // Fetch movie details from the database using MovieDatabaseConnecter
            MovieDatabaseConnecter movieDB = new MovieDatabaseConnecter();
            List<Label> movieDetails = movieDB.movieDetails(movieId);

            // Extract movie details from the List<Label>
            String movieName = movieDetails.get(0).getText().replace("Movie Name: ", "");
            String cinemaName = movieDetails.get(1).getText().replace("Cinema Name: ", "");
            String showtimes = movieDetails.get(2).getText().replace("Showtimes:\n", "");

            // Call setMovieDetails to set the movie details in the UI
            movieDetailsController.setMovieDetails(movieName, cinemaName, showtimes);

            Scene scene = new Scene(root);
            // Get the stage from any node (e.g., "movieListView")
            Stage stage = (Stage) movieListView.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadNewPage(String page, Node button, List<String> nextControllerInfo) 
    {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/uipackage/" + page + ".fxml"));
            Parent root = loader.load();
            CustomerController controller = loader.getController();

            if (controller != null) {
                controller.setLoginUser(null);
            }
            
            Stage stage = (Stage) button.getScene().getWindow();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/uipackage/designLayout.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
