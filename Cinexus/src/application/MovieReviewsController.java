//package application;
//
//import javafx.fxml.FXML;
//import javafx.scene.control.TextArea;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//import dbhandler.MovieReviewConnecter;
//
//public class MovieReviewsController {
//    @FXML
//    private TextArea movieReviewTextArea;
//    
//    @FXML
//    private TextArea cinemaFeedbackTextArea;
//
//    @FXML
//    private void submitMovieReview() {
//        String reviewText = movieReviewTextArea.getText();
//
//        // Perform database update to store or update the movie review
//        updateMovieReviewInDatabase(reviewText);
//
//        // Optionally, you can display a confirmation message to the user
//        System.out.println("Movie review submitted successfully!");
//    }
//    
//    @FXML
//    private void submitCinemaFeedback() {
//        String feedbackText = cinemaFeedbackTextArea.getText();
//
//        // Perform database update to store or update the cinema feedback
//        updateCinemaFeedbackInDatabase(feedbackText);
//
//        // Optionally, you can display a confirmation message to the user
//        System.out.println("Cinema feedback submitted successfully!");
//    }
//
//    private void updateMovieReviewInDatabase(String reviewText) {
//        try {
//            Connection connection = MovieReviewConnecter.getConnection(); // Implement your database connection logic
//            String insertOrUpdateQuery = "INSERT INTO movie_reviews (user_id, movie_id, review_text) VALUES (?, ?, ?) " +
//                                         "ON DUPLICATE KEY UPDATE review_text = ?";
//            PreparedStatement preparedStatement = connection.prepareStatement(insertOrUpdateQuery);
//            
//            // You need to set user_id and movie_id appropriately based on your application's user and movie context
//            int userId = 1;
//            int movieId = 1;
//            
//            preparedStatement.setInt(1, userId);
//            preparedStatement.setInt(2, movieId);
//            preparedStatement.setString(3, reviewText);
//            preparedStatement.setString(4, reviewText);
//            
//            preparedStatement.executeUpdate();
//            
//            preparedStatement.close();
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    
//    private void updateCinemaFeedbackInDatabase(String feedbackText) {
//        try {
//            Connection connection = MovieReviewConnecter.getConnection(); // Implement your database connection logic
//            String insertOrUpdateQuery = "INSERT INTO cinema_feedback (user_id, cinema_id, feedback_text) VALUES (?, ?, ?) " +
//                                         "ON DUPLICATE KEY UPDATE feedback_text = ?";
//            PreparedStatement preparedStatement = connection.prepareStatement(insertOrUpdateQuery);
//            
//            // You need to set user_id and cinema_id appropriately based on your application's user and cinema context
//            int userId = 1;
//            int cinemaId = 1;
//            
//            preparedStatement.setInt(1, userId);
//            preparedStatement.setInt(2, cinemaId);
//            preparedStatement.setString(3, feedbackText);
//            preparedStatement.setString(4, feedbackText);
//            
//            preparedStatement.executeUpdate();
//            
//            preparedStatement.close();
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}

package application;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dbhandler.MovieReviewConnecter;

public class MovieReviewsController {
    @FXML
    private TextArea feedbackTextArea;
    
    @FXML
    private TextField idTextField;

    @FXML
    private RadioButton movieReviewRadioButton;
    
    @FXML
    private RadioButton cinemaFeedbackRadioButton;

    @FXML
    private ToggleGroup feedbackTypeGroup;

    @FXML
    private void submitFeedback() {
    	
        String feedbackText = feedbackTextArea.getText();
        int id = Integer.parseInt(idTextField.getText());

        // Determine whether it's a movie review or cinema feedback based on the selected radio button
        String feedbackType;
        if (movieReviewRadioButton.isSelected()) {
            feedbackType = "movie_review";
        } else if (cinemaFeedbackRadioButton.isSelected()) {
            feedbackType = "cinema_feedback";
        } else {
            // No feedback type selected, handle this case as needed
            return;
        }

        // Perform database update to store or update the feedback
        updateFeedbackInDatabase(id, feedbackText, feedbackType);

        // Optionally, you can display a confirmation message to the user
        System.out.println("Feedback submitted successfully!");
    }


    private void updateFeedbackInDatabase(int id, String feedbackText, String feedbackType) {
        try {
            Connection connection = MovieReviewConnecter.getConnection(); // Implement your database connection logic
            String insertOrUpdateQuery = "";
            
            if (feedbackType.equals("movie_review")) {
                insertOrUpdateQuery = "INSERT INTO movie_reviews (user_id, movie_id, review_text) VALUES (?, ?, ?) " +
                                      "ON DUPLICATE KEY UPDATE review_text = ?";
            } else if (feedbackType.equals("cinema_feedback")) {
                insertOrUpdateQuery = "INSERT INTO cinema_feedback (user_id, cinema_id, feedback_text) VALUES (?, ?, ?) " +
                                      "ON DUPLICATE KEY UPDATE feedback_text = ?";
            }
            
            PreparedStatement preparedStatement = connection.prepareStatement(insertOrUpdateQuery);
            
            // You need to set user_id appropriately based on your application's user context
            int userId = 1;
            
           
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, id);
            preparedStatement.setString(3, feedbackText);
            preparedStatement.setString(4, feedbackText);
            
            preparedStatement.executeUpdate();
            
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
