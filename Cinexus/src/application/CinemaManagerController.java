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
import dbhandler.CinemaDatabaseConnecter;
import dbhandler.MovieDatabaseConnecter;
import dbhandler.UserDatabaseConnecter;

public class CinemaManagerController
{
	private User CinemaManagerInfo;
	@FXML private TextField CenimaIDforDelete;
    @FXML private Node DeleteCinema;
    @FXML private Node AddCinema;
    @FXML private Node ArrangeShowings;
    @FXML private VBox CinemaListView;

    public void initialize(User user) 
    {
//        System.out.println(user.getUsername());
		this.CinemaManagerInfo = factory.createUser(user);
        
        CinemaDatabaseConnecter CinemaDB = new CinemaDatabaseConnecter();
        List<Cinema> Cinemas = CinemaDB.fetchCinemaFromDatabase(CinemaManagerInfo.getUserID());
        
        for (Cinema cinema : Cinemas) 
        {
            HBox cinemaEntry = createCinemaEntry(cinema);
            CinemaListView.getChildren().add(cinemaEntry);
        }
    }
    private HBox createCinemaEntry(Cinema cinema) 
    {
        HBox entry = new HBox(10);
        VBox container = new VBox(5);

        Label cinemaInfoLabel = new Label("Cinema ID: " + Cinema.getCinemaid() + 
        		"\nCinema Name: " + Cinema.getName() + 
        		"\nCinema Location: " + Cinema.getLocation());
        cinemaInfoLabel.setStyle("-fx-font-weight: bold;");

        container.setMargin(cinemaInfoLabel, new Insets(0, 10, 0, 10));
        container.setStyle("-fx-background-color: #FFD700;");
        entry.getChildren().add(container);
        entry.getChildren().addAll(cinemaInfoLabel);
        
        return entry;
    }
//
//    private Cinema[] addCinemaToArray(Cinema[] movies, Movie newMovie) 
//    {
//        int length = movies.length;
//        Cinema[] newMovies = new Cinema[length + 1];
//
//        System.arraycopy(movies, 0, newMovies, 0, length);
//        newMovies[length] = newMovie;
//
//        return newMovies;
//    }
    
    @FXML
    public void addCinema() {
    	loadNewPage("SignUp", AddCinema);
    }
    
    @FXML
    public void addDeleteShowings() {
    	loadNewPage("SignUp", ArrangeShowings);
    }
    
    @FXML
    public void DeleteCinema() {
        CinemaDatabaseConnecter CinemaDB = new CinemaDatabaseConnecter();
        CinemaDB.DeleteCinemaFromDatabase(CenimaIDforDelete.getText());
    }
    public void loadNewPage(String page, Node button) 
    {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/uipackage/" + page + ".fxml"));
            Parent root = loader.load();
            
            
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
