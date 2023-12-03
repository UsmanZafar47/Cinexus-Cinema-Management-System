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
    @FXML private TextArea feedbackTextArea;
    
    @FXML private TextField idTextField;

    @FXML private RadioButton movieReviewRadioButton;
    
    @FXML private RadioButton cinemaFeedbackRadioButton;

    @FXML private ToggleGroup feedbackTypeGroup;

    @FXML private void submitFeedback() {
    	
        String feedbackText = feedbackTextArea.getText();
        int id = Integer.parseInt(idTextField.getText());

        String feedbackType;
        if (movieReviewRadioButton.isSelected()) {
            feedbackType = "movie_review";
        } else if (cinemaFeedbackRadioButton.isSelected()) {
            feedbackType = "cinema_feedback";
        } else {
            return;
        }

        updateFeedbackInDatabase(id, feedbackText, feedbackType);
        System.out.println("Feedback submitted successfully!");
    }


    private void updateFeedbackInDatabase(int id, String feedbackText, String feedbackType) {
        try {
            Connection connection = MovieReviewConnecter.getConnection();
            String insertOrUpdateQuery = "";
            
            if (feedbackType.equals("movie_review")) {
                insertOrUpdateQuery = "INSERT INTO movie_reviews (user_id, movie_id, review_text) VALUES (?, ?, ?) " +
                                      "ON DUPLICATE KEY UPDATE review_text = ?";
            } else if (feedbackType.equals("cinema_feedback")) {
                insertOrUpdateQuery = "INSERT INTO cinema_feedback (user_id, cinema_id, feedback_text) VALUES (?, ?, ?) " +
                                      "ON DUPLICATE KEY UPDATE feedback_text = ?";
            }
            
            PreparedStatement preparedStatement = connection.prepareStatement(insertOrUpdateQuery);
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
