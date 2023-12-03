package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;
import dbhandler.ShowingDatabaseConnecter;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ArrangeShowingsController 
{
	private User CinemaManagerInfo;
    @FXML private TextField MovieField;
    @FXML private TextField CinemaField;
    @FXML private DatePicker ShowingDate;
    @FXML public Text actionTarget;
    @FXML public Node AddShowButton;
    @FXML public Node DeleteShowing;
    @FXML private TextField RemoveShowings;
    @FXML public Node GoHome;

    
    public void initialize(User user){
		this.CinemaManagerInfo = factory.createUser(user);
    }
    
    @FXML
    public void onAddingShowing()
    {
    	String movie = MovieField.getText();
        String cinema = CinemaField.getText();
        LocalDate date = ShowingDate.getValue();
        
        if(movie.isBlank() || cinema.isBlank() || date == null)
        {
        	actionTarget.setText("Please enter all required information.");
        }
        else
        {
        	try {
                int movi = Integer.parseInt(movie);
                int cinma = Integer.parseInt(cinema);
                Showings NewShow = factory.createNewShowing(Integer.parseInt(movie), Integer.parseInt(cinema), ""+date);

                if (NewShow.getShowtime_id() == -1) 
    	            actionTarget.setText("Show not Added. Please Check Movies and Cinema IDs.");
    	        else
    	            loadNewPage("ArrangeShowings", AddShowButton);
        	} 
        	catch (NumberFormatException e) {
            	actionTarget.setText("Please enter Numerical IDs.");
            }
        }
    }
    
    @FXML
    public void DeleteShowing() {
    	ShowingDatabaseConnecter ShowDB = new ShowingDatabaseConnecter();
    	ShowDB.DeleteShowFromDatabase(RemoveShowings.getText());
    	loadNewPage("ArrangeShowings", DeleteShowing);
    }
    
    @FXML
    public void LoadMain() {
    	loadNewPage("CinemaManagerMainPage", GoHome);
    }
    public void loadNewPage(String page, Node button) 
    {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/uipackage/"+ page +".fxml"));
            Parent root = loader.load();

            if(page.equals("CinemaManagerMainPage"))
			{
				CinemaManagerController controller = loader.getController();
	            if (controller != null)
	                controller.initialize(CinemaManagerInfo);
			}
            else
			{
            	ArrangeShowingsController controller = loader.getController();
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
