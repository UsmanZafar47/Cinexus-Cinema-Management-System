package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
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
    private int movieId;

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
        allLabels = MovieDB.movieDetails();
        VBox root = new VBox(10);
        root.getChildren().addAll(allLabels);

        Scene scene = new Scene(root, 400, 200);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Movie Details");
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        int movieId = 1;
        launch(args);
    }
}
