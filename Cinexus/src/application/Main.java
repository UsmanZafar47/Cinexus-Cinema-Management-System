package application;

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
import javafx.application.Application;

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
		try 
		{
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("/uipackage/Login.fxml"));

            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add(getClass().getResource("/uipackage/designLayout.css").toExternalForm());
	        primaryStage.setMinWidth(800);
	        primaryStage.setMinHeight(600);
	        primaryStage.setMaxWidth(800);
	        primaryStage.setMaxHeight(600);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Cinexus Cinemas");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            	loadNewPage("SignUp", loginButton, loggedinUser);
            else if(loggedinUser.Role.equalsIgnoreCase("cinema Manager"))
            	loadNewPage("SignUp", loginButton, loggedinUser);
            else
                actionTarget.setText("Login Successful as a " + loggedinUser.Role + " but not in db");            	
        }
    }
    
    @FXML
    public void loadSignup() 
    {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/uipackage/SignUp.fxml"));
            Parent root = loader.load();
            
            Stage stage = (Stage) signupButton.getScene().getWindow();
            Scene scene = new Scene(root);

            scene.getStylesheets().add(getClass().getResource("/uipackage/designLayout.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void loadNewPage(String page, Node button, User user) {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/uipackage/" + page + ".fxml"));
            Parent root = loader.load();
            	CustomerController controller = loader.getController();
            
            if (controller != null)
                controller.setLoginUser(user);
            
            Stage stage = (Stage) button.getScene().getWindow();
            Scene scene = new Scene(root);

            scene.getStylesheets().add(getClass().getResource("/uipackage/designLayout.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
	}   
}