package application;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbhandler.MovieDatabaseConnecter;
import dbhandler.UserDatabaseConnecter;

public class MovieDetailsPage extends Application 
{
	private User CustomerInfo;
    private int movieId;
    @FXML public Node GoHome;
    @FXML private Label movieNameLabel;
    @FXML private Label cinemaNameLabel;
    @FXML private Label showtimesLabel;
	@FXML private TextField ShowingIDField;
	@FXML private TextField SeatCountField;
    @FXML private Node reserveSeats;
    @FXML public Text actionTarget;

    public void setMovieDetails(String movieName, String cinemaName, String showtimes) {
        movieNameLabel.setText("Movie Name: " + movieName);
        cinemaNameLabel.setText("Cinema Name: " + cinemaName);
        showtimesLabel.setText("Showtimes:\n" + showtimes);
    }
    public void initialize(User user){
		this.CustomerInfo = factory.createUser(user);
    }
    public MovieDetailsPage() {}
    public MovieDetailsPage(int movieId) {
        this.movieId = movieId;
    }
    @Override
    public void start(Stage primaryStage) throws SQLException 
    {
        Label movieNameLabel = new Label();
        Label cinemaNameLabel = new Label();
        Label showtimesLabel = new Label();

        List<Label> allLabels = new ArrayList<Label>();
        MovieDatabaseConnecter MovieDB = new MovieDatabaseConnecter();
        allLabels = MovieDB.movieDetails(movieId);
        VBox root = new VBox(10);
        root.getChildren().addAll(allLabels);

        Scene scene = new Scene(root, 400, 200);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Movie Details");
        primaryStage.show();
    }

	public void setMovieId(int movieId2) {
		movieId=movieId2;
	}
	
    @FXML
    public void onBooking() {
        String showiD = ShowingIDField.getText();
        String seatCounut = SeatCountField.getText();
        try {
        	int ShowID = Integer.parseInt(showiD);
            int SeatCount = Integer.parseInt(seatCounut);
            //TODO: check show and seat is available

            //TODO: if Ticket formed load next page, else give error
            Tickets Controlleritems = factory.createTempTicket(CustomerInfo, ShowID, SeatCount);
        	loadTicketPage(Controlleritems);
        }
        catch (NumberFormatException e) {
        	actionTarget.setText("Enter only Numerical Values.");
        }
    }
	@FXML
    public void LoadMain() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/uipackage/CustomerMainPage.fxml"));
		    Parent root = loader.load();
			CustomerController controller = loader.getController();
		    if (controller != null)
		        controller.initialize(CustomerInfo);
		    Stage stage = (Stage) GoHome.getScene().getWindow();
		    Scene scene = new Scene(root);
		    scene.getStylesheets().add(getClass().getResource("/uipackage/designLayout.css").toExternalForm());
		    stage.setScene(scene);
		    stage.show();
		} catch (Exception e) {
		    e.printStackTrace();
		}
    }
	
	public void loadTicketPage(Tickets Controlleritems) 
    {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/uipackage/TicketPage.fxml"));
            Parent root = loader.load();

            TicketController controller = loader.getController();
            	if (controller != null)
                controller.initialize(Controlleritems);

            Stage stage = (Stage) reserveSeats.getScene().getWindow();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/uipackage/designLayout.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
