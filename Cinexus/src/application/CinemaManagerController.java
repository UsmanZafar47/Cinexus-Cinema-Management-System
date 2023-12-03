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

        Label cinemaInfoLabel = new Label("Cinema ID: " + cinema.getCinemaid() + 
        		"\nCinema Name: " + cinema.getName() + 
        		"\nCinema Location: " + cinema.getLocation());
        cinemaInfoLabel.setStyle("-fx-font-weight: bold;");

        container.setMargin(cinemaInfoLabel, new Insets(0, 10, 0, 10));
        container.setStyle("-fx-background-color: #FFD700;");
        entry.getChildren().add(container);
        entry.getChildren().addAll(cinemaInfoLabel);
        
        return entry;
    }

    private Cinema[] addCinemaToArray(Cinema[] cinemas, Cinema newCinema) 
    {
        int length = cinemas.length;
        Cinema[] newCinemas = new Cinema[length + 1];

        System.arraycopy(cinemas, 0, newCinemas, 0, length);
        newCinemas[length] = newCinema;

        return newCinemas;
    }
    
    @FXML
    public void addCinema() {
    	loadNewPage("AddCinema", AddCinema);
    }
    
    @FXML
    public void addDeleteShowings() {
    	loadNewPage("ArrangeShowings", ArrangeShowings);
    }
    
    @FXML
    public void DeleteCinema() {
        CinemaDatabaseConnecter CinemaDB = new CinemaDatabaseConnecter();
        CinemaDB.DeleteCinemaFromDatabase(CenimaIDforDelete.getText());
    	loadNewPage("CinemaManagerMainPage", DeleteCinema);
    }
    public void loadNewPage(String page, Node button) 
    {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/uipackage/" + page + ".fxml"));
            Parent root = loader.load();

            if(page.equals("AddCinema"))
			{
            	AddCinemaController controller = loader.getController();
	            if (controller != null)
	                controller.initialize(CinemaManagerInfo);
			}
            else if(page.equals("ArrangeShowings"))
			{
            	ArrangeShowingsController controller = loader.getController();
	            if (controller != null)
	                controller.initialize(CinemaManagerInfo);
			}
			else if(page.equals("CinemaManagerMainPage"))
			{
				CinemaManagerController controller = loader.getController();
	            if (controller != null)
	                controller.initialize(CinemaManagerInfo);
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
