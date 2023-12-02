package application;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import dbhandler.DatabaseConnecter;
import javafx.application.Application;

public class CustomerController
{
	User CustomerInfo;
	@FXML public TextField ticketNoField;
    @FXML public Node Trackticket;
    @FXML public Node Movie1BS;
    @FXML public Node Movie2BS;
    @FXML public Node Movie3BS;
    @FXML public Node Movie1VM;
    @FXML public Node Movie2VM;
    @FXML public Node Movie3VM;

	public void setLoginUser(User user) 
	{
		this.CustomerInfo = factory.createUser(user);
	}
    
    @FXML
    public void trackTicket() {
        String username = ticketNoField.getText();
    	loadNewPage("SignUp", Trackticket);
    }
    
    public void loadNewPage(String page, Node button) 
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
    
    private void ViewMovie(ActionEvent event)
    {
    	loadNewPage("MovieDetails", Movie1VM);
		/*
		 * if (event.getSource() == Movie1) { loadNewPage("SignUp", Movie1, ); } else if
		 * (event.getSource() == Movie2) { loadNewPage("SignUp", Movie2); } else if
		 * (event.getSource() == Movie3) { loadNewPage("SignUp", Movie3); }
		 */
    }
}
