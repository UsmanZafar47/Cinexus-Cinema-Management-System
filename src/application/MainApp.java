package application;
//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//
//public class MainApp extends Application {
//
//    
//    public void start(Stage primaryStage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
//        primaryStage.setTitle("Login");
//        primaryStage.setScene(new Scene(root, 700, 375));
//        primaryStage.show();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}
//

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//public class MainApp extends Application {
//
//    @Override
//    public void start(Stage primaryStage) {
//        try {
//            // Load the FXML file
//            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml")); // Replace with the actual path to your FXML file
//
//            // Set the scene and stage
//            Scene scene = new Scene(root);
//            primaryStage.setTitle("Cinexus Cinemas Login");
//            primaryStage.setScene(scene);
//            primaryStage.show();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}


public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("IntialScreen.fxml")); // Load the initial screen
            Scene scene = new Scene(root);
            primaryStage.setTitle("Cinexus Cinemas");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    public static void main(String[] args) {
        launch(args);
    }
}


