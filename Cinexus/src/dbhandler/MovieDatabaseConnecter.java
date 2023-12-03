package dbhandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import application.Movie;
import application.User;
import javafx.scene.control.Label;

public class MovieDatabaseConnecter implements DatabaseConnecter
{
	public String get(int id,  String columnName)
	{
		Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    try 
	    {
	        conn = DriverManager.getConnection(URL, USER, PASSWORD);
	        String sql = "SELECT "+columnName+" FROM movies WHERE movie_id = " + id;
	        stmt = conn.prepareStatement(sql);
	        rs = stmt.executeQuery();
	        if (rs.next()) 
	        {
	        	String colVal = rs.getString(columnName);
	        	return colVal;
	        }
	        return "not there";
	    } 
	    catch (SQLException e) 
	    {
	        e.printStackTrace();
	        return "Stackerror";
	    } 
	    finally 
	    {
	        try {
	            if (rs != null) rs.close();
	            if (stmt != null) stmt.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	public List<Movie> fetchMoviesFromDatabase() 
	{
        List<Movie> movies = new ArrayList<>();

		Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
        try 
        {
	        conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT movie_id, title FROM movies");

            while (resultSet.next()) {
                int id = resultSet.getInt("movie_id");
                String title = resultSet.getString("title");

                Movie movie = new Movie(id, title, 0, " ");
                movies.add(movie);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return movies;
    }
	
	public static List<Label> movieDetails(int movieId) throws SQLException {

	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    try {
	        conn = DriverManager.getConnection(URL, USER, PASSWORD);

	        String query = "SELECT m.title, c.name AS cinema_name, GROUP_CONCAT(s.showtime SEPARATOR ', ') AS showtimes " +
	                "FROM movies m " +
	                "JOIN showtimes s ON m.movie_id = s.movie_id " +
	                "JOIN cinema c ON s.cinema_id = c.cinema_id " +
	                "WHERE m.movie_id = ? " +  // Filter by movie_id
	                "GROUP BY m.title, c.name;";

	        // Create a PreparedStatement with the query
	        stmt = conn.prepareStatement(query);
	        stmt.setInt(1, movieId); // Set the movie_id parameter

	        rs = stmt.executeQuery();

	        List<Label> allLabels = new ArrayList<Label>();

	        if (rs.next()) {
	            String movieName = rs.getString("title");
	            String cinemaName = rs.getString("cinema_name");
	            String showtimes = rs.getString("showtimes");

	            Label label1 = new Label("Movie Name: " + movieName);
	            Label label2 = new Label("Cinema Name: " + cinemaName);
	            Label label3 = new Label("Showtimes:\n" + showtimes);

	            allLabels.add(label1);
	            allLabels.add(label2);
	            allLabels.add(label3);
	        }

	        return allLabels;
	    } finally {
	        if (conn != null) conn.close();
	    }
	}
//	public List<Label> movieDetails() throws SQLException 
//	{
//
//		Connection conn = null;
//	    PreparedStatement stmt = null;
//	    ResultSet rs = null;
//
//		String query = "SELECT m.title, c.name AS cinema_name, GROUP_CONCAT(s.showtime SEPARATOR ', ') AS showtimes " +
//		        "FROM movies m " +
//		        "JOIN showtimes s ON m.movie_id = s.movie_id " +
//		        "JOIN cinema c ON s.cinema_id = c.cinema_id " +
//		        "GROUP BY m.title, c.name;";
//		
//		ResultSet resultSet = conn.createStatement().executeQuery(query);
//
//        List<Label> allLabels = new ArrayList<Label>();
//		if (resultSet.next()) {
//		    String movieName = resultSet.getString("title");
//		    String cinemaName = resultSet.getString("cinema_name");
//		    String showtimes = resultSet.getString("showtimes");
//
//		    allLabels.add(null);
//		    allLabels.add(null);
//		    allLabels.add(null);
//		    allLabels.get(0).setText("Movie Name: " + movieName);
//		    allLabels.get(1).setText("Cinema Name: " + cinemaName);
//		    allLabels.get(2).setText("Showtimes:\n" + showtimes);
//		}
//		conn.close();
//		return allLabels;
//	}
}