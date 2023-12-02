//package application;
//
//import javafx.collections.FXCollections;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Node;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.CheckBox;
//import javafx.scene.control.ComboBox;
//import javafx.scene.control.PasswordField;
//import javafx.scene.control.TextField;
//import javafx.scene.text.Text;
//import javafx.stage.Stage;
//import dbhandler.DatabaseConnecter;
//import javafx.application.Application;
//
//public class Main extends Application 
//{
//	@FXML public ComboBox<String> roleComboBox;
//	@FXML public TextField usernameField;
//    @FXML public PasswordField passwordField;
//    @FXML public CheckBox rememberMeCheckBox;
//    @FXML public Text signUpText;
//    @FXML public Text actionTarget;
//    @FXML public Node loginButton;
//    @FXML public Node signupButton;
//	
//	@Override
//	public void start(Stage primaryStage) 
//	{
//        try {
//            Parent root = FXMLLoader.load(getClass().getResource("/uipackage/Login.fxml"));
//            Scene scene = new Scene(root);
//            
//            primaryStage.setTitle("Cinexus Cinemas");
//            primaryStage.setScene(scene);
//
//            primaryStage.setMinWidth(800);
//            primaryStage.setMinHeight(600);
//            primaryStage.setMaxWidth(800);
//            primaryStage.setMaxHeight(600);
//            
//            scene.getStylesheets().add(getClass().getResource("/uipackage/designLayout.css").toExternalForm());
//            
//            primaryStage.show();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//	}      
//
//    @FXML
//    public void onLogin() {
//        String username = usernameField.getText();
//        String password = passwordField.getText();
//        
//        String isAuthenticated;
//        
//		isAuthenticated = DatabaseConnecter.userAuthentication(username, password);
//		if (isAuthenticated.equalsIgnoreCase("Invalid")) {
//            actionTarget.setText("Invalid Username or Password");
//        } else 
//        {
//            if (isAuthenticated.equalsIgnoreCase("customer"))
//            	loadNewPage("SignUp");
//            else if(isAuthenticated.equalsIgnoreCase("admin"))
//            	loadNewPage("SignUp");
//            else if(isAuthenticated.equalsIgnoreCase("cinema Manager"))
//            	loadNewPage("SignUp");
//            else
//                actionTarget.setText("Login Successful as a " + isAuthenticated + " but not in db");            	
//        }
//    }
//    
//    @FXML
//    public void loadSignup() {
//    	loadNewPage("SignUp");
//    }
//    
//    public void loadNewPage(String page) {
//    	try {
//            Parent root = FXMLLoader.load(getClass().getResource("/uipackage/"+page+".fxml"));
//            Stage stage = (Stage) signupButton.getScene().getWindow();
//            Scene scene = new Scene(root);
//            scene.getStylesheets().add(getClass().getResource("/uipackage/designLayout.css").toExternalForm());
//            stage.setScene(scene);
//            stage.show();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//	
//	public static void main(String[] args) 
//	{
//		launch(args);
//	}
//}
package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application 
{
	@FXML public TextField usernameField;
    @FXML public PasswordField passwordField;
    @FXML public CheckBox rememberMeCheckBox;
    @FXML public Text signUpText;
    @FXML public Text actionTarget;
    @FXML public Node loginButton;
    @FXML public Node signupButton;
	
	@Override
	public void start(Stage primaryStage) 
	{
        try {
            // Load the movie list FXML
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("/uipackage/MovieList.fxml"));

            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add(getClass().getResource("/uipackage/designLayout.css").toExternalForm());
            // Set the scene in the primary stage
            primaryStage.setScene(scene);
            primaryStage.setTitle("Movie List");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
	}      

    @FXML
    public void onLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        
        User loggedinUser = factory.createUser(username, password);
        
		if (loggedinUser.id == -1)
            actionTarget.setText("Invalid Username or Password");
		else 
        {
            if (loggedinUser.Role.equalsIgnoreCase("customer"))
            	loadNewPage("CustomerMainPage", loginButton, loggedinUser);
            else if(loggedinUser.Role.equalsIgnoreCase("admin"))
            	loadNewPage("AdminMainPage", loginButton, loggedinUser);
            else if(loggedinUser.Role.equalsIgnoreCase("cinema Manager"))
            	loadNewPage("SignUp", loginButton, loggedinUser);
            else
                actionTarget.setText("Login Successful as a " + loggedinUser.Role + " but not in db");            	
        }
    }
    
    @FXML
    public void loadSignup() {
    	User GuestUser = factory.createUser();
        loadNewPage("SignUp", signupButton, GuestUser);
    }
    
    public void loadNewPage(String page, Node button, User user) {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/uipackage/" + page + ".fxml"));
            Parent root = loader.load();
            CustomerController controller = loader.getController();

            if (controller != null) {
                controller.setLoginUser(user);
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
