package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.time.LocalDate;
import java.util.List;

import dbhandler.ShowingDatabaseConnecter;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TicketController 
{
	private Tickets TicketInfo;
    @FXML public Text actionTarget;
    @FXML public Node PayButton;
    @FXML public Node ConfirmButton;
    @FXML public Node GoHome;
    @FXML private Label MovieNameLabel;
    @FXML private Label CinemaNameLabel;
    @FXML private Label UserNameLabel;
    @FXML private Label TicketStatusLabel;
    @FXML private Label UserCNICLabel;
    @FXML private Label CinemaLocationLabel;
    @FXML private Label ShowtimeLabel;
    @FXML private Label SeatCountLabel;
    @FXML private Label PriceLabel;

    
    public void initialize(Tickets carriedOverInfo){
		this.TicketInfo = factory.createTicket(carriedOverInfo);
    }
    public void setTicketDetails() {
    	MovieNameLabel.setText("Movie Name: " + TicketInfo.getMovie().getTitle());
    	CinemaNameLabel.setText("Cinema Name: " + TicketInfo.getCinema().getName());
    	UserNameLabel.setText("Name: " + TicketInfo.getUser().getName());
    	UserCNICLabel.setText("CNIC: " + TicketInfo.getUser().getCNIC());
    	CinemaLocationLabel.setText("Cinema Location: " + TicketInfo.getCinema().getLocation());
    	ShowtimeLabel.setText("Show Time: " + TicketInfo.getShowing().getShowtime());
    	SeatCountLabel.setText("No. Seats Booked:\n" + TicketInfo.getSeatCount());
    	PriceLabel.setText("Total Price:\n" + TicketInfo.getPrice());
    }
    @FXML
    public void LoadMain() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/uipackage/CustomerMainPage.fxml"));
		    Parent root = loader.load();
			CustomerController controller = loader.getController();
		    if (controller != null)
		        controller.initialize(TicketInfo.getUser());
		    Stage stage = (Stage) GoHome.getScene().getWindow();
		    Scene scene = new Scene(root);
		    scene.getStylesheets().add(getClass().getResource("/uipackage/designLayout.css").toExternalForm());
		    stage.setScene(scene);
		    stage.show();
		} catch (Exception e) {
		    e.printStackTrace();
		}
    }
    public void onBooking()
    {
    	int tickID = TicketInfo.InsertintoDB("Reserved");
        actionTarget.setText("Your TicketNo. is " +tickID);
    }
    public void onPayment()
    {
    	int tickID = TicketInfo.InsertintoDB("Booked");
        actionTarget.setText("Your TicketNo. is " +tickID);
    }
}
