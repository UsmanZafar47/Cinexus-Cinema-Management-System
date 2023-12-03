package dbhandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import application.Cinema;
import application.Movie;
import application.Showings;
import application.User;
import javafx.scene.control.Label;

public class ShowingDatabaseConnecter implements DatabaseConnecter
{
	public int getID(int movieID, int cinemaID)
	{
		Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    try 
	    {
	        conn = DriverManager.getConnection(URL, USER, PASSWORD);
	        String sql = "SELECT showtime_id FROM showtimes WHERE cinema_id = ? AND movie_id = ?";
	        stmt = conn.prepareStatement(sql);
	        stmt.setInt(1, cinemaID);
	        stmt.setInt(2, movieID);
	        rs = stmt.executeQuery();

	        if (rs.next()) {
	            int showtime_id = rs.getInt("showtime_id");
	            return showtime_id;
	        }
	        return -1;
	    } 
	    catch (SQLException e) 
	    {
	        e.printStackTrace();
	        return -1;
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
	
	public List<Cinema> fetchCinemaFromDatabase(int Userid) 
	{
        List<Cinema> cinemas = new ArrayList<>();

		Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
        try 
        {
	        conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM cinema WHERE manager_id = " + Userid);

            while (resultSet.next()) {
                int cinema_id = resultSet.getInt("cinema_id");
                String name = resultSet.getString("name");
                String location = resultSet.getString("location");
                int manager_id = resultSet.getInt("manager_id");
                
                Cinema cinema = new Cinema(cinema_id, name, location, manager_id);
                cinemas.add(cinema);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cinemas;
    }
	
	public void InsertinDB(Showings show)
	{
		Connection conn = null;
	    PreparedStatement stmt = null;

	    try 
	    {
	        conn = DriverManager.getConnection(URL, USER, PASSWORD);

	        String sql = "INSERT into showtimes (cinema_id, movie_id, showtime) "
	        		+ "VALUES (?, ?, ?)";

	        stmt = conn.prepareStatement(sql);
	        stmt.setInt(1, show.getCinema_id());
	        stmt.setInt(2, show.getMovie_id());
	        stmt.setString(3, ""+show.getShowtime());
	        
	        int rows = stmt.executeUpdate();
	        if (rows > 0)
	        	System.out.println("Show inserted successfully.");
	        else 
	        	System.out.println("Show insertion failed.");
	        if (stmt != null) stmt.close();
	        if (conn != null) conn.close();
	    } 
	    catch (SQLException e) 
	    {
	        if (e instanceof SQLIntegrityConstraintViolationException) { }
	        else 
	        	e.printStackTrace();
	    }
	}
	
	public void DeleteShowFromDatabase(String ShowID) 
	{
			Connection conn = null;
		    PreparedStatement stmt = null;
	        try 
	        {
		        conn = DriverManager.getConnection(URL, USER, PASSWORD);
	            Statement statement = conn.createStatement();
	            int rowsaff = statement.executeUpdate("DELETE FROM showtimes WHERE showtime_id = " + ShowID);
	            
	            statement.close();
	            conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
    }	
}