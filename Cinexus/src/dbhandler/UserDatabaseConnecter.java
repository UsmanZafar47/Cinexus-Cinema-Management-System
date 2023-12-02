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

public class UserDatabaseConnecter implements DatabaseConnecter
{
	public String getID(String username,  String password)
	{
		Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    try 
	    {
	        conn = DriverManager.getConnection(URL, USER, PASSWORD);

	        String sql = "SELECT user_id FROM users WHERE username = ? AND password = ?";

	        stmt = conn.prepareStatement(sql);
	        stmt.setString(1, username);
	        stmt.setString(2, password);

	        rs = stmt.executeQuery();

	        if (rs.next()) {
	            String userID = rs.getString("user_id");
	            return userID;
	        }
	        return "Invalid";
	    } 
	    catch (SQLException e) 
	    {
	        e.printStackTrace();
	        return "Invalid";
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
	public String get(int id,  String columnName)
	{
		Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    try 
	    {
	        conn = DriverManager.getConnection(URL, USER, PASSWORD);

	        String sql = "SELECT "+columnName+" FROM users WHERE user_id = " + id;

	        stmt = conn.prepareStatement(sql);
	        rs = stmt.executeQuery();

	        if (rs.next()) {
	            String colVal = rs.getString(columnName);
	            return colVal;
	        }
	        return "Invalid";
	    } 
	    catch (SQLException e) 
	    {
	        e.printStackTrace();
	        return "Invalid";
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
}