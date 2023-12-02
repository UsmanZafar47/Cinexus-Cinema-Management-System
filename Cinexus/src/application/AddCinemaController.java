package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;

import java.util.List;

import dbhandler.CinemaDatabaseConnecter;
import javafx.collections.FXCollections;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddCinemaController 
{
	private User CinemaManagerInfo;
    @FXML private TextField nameField;
    @FXML private TextField LocationField;
    @FXML private TextField NoofSeatsField;
    @FXML public Text actionTarget;
    @FXML public Node AddCinemaButton;
    @FXML public Node GoHome;

    
    public void initialize(User user){
		this.CinemaManagerInfo = factory.createUser(user);
    }
    
    @FXML
    public void onAddingCinema()
    {
    	String name = nameField.getText();
        String location = LocationField.getText();
        int noseats = Integer.parseInt(NoofSeatsField.getText());
        
        Cinema NewCinema = factory.createNewCinema(name, location, noseats, CinemaManagerInfo.getUserID());
	
	        if (NewCinema.getCinemaid() == -1) 
	            actionTarget.setText("Cinema not Added.");
	        else
	            actionTarget.setText("Cinema Added Successfully!");
    }
    
    @FXML
    public void loadMain() 
    {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/uipackage/CinemaManagerMainPage.fxml"));
            Parent root = loader.load();

			CinemaManagerController controller = loader.getController();
            if (controller != null)
                controller.initialize(CinemaManagerInfo);
            
            Stage stage = (Stage) GoHome.getScene().getWindow();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/uipackage/designLayout.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
