package dbhandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import application.Cinema;
import application.Movie;
import application.User;
import javafx.scene.control.Label;

public class CinemaDatabaseConnecter implements DatabaseConnecter
{
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
	
	public void DeleteCinemaFromDatabase(String cinemaID) 
	{
		Connection conn = null;
	    PreparedStatement stmt = null;
        try 
        {
	        conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = conn.createStatement();
            int rowsaff = statement.executeUpdate("DELETE FROM cinema WHERE cinema_id = " + cinemaID);
            
            statement.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
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
	/*
	 * public List<Label> movieDetails() throws SQLException {
	 * 
	 * Connection conn = null; PreparedStatement stmt = null; ResultSet rs = null;
	 * 
	 * String query =
	 * "SELECT m.title, c.name AS cinema_name, GROUP_CONCAT(s.showtime SEPARATOR ', ') AS showtimes "
	 * + "FROM movies m " + "JOIN showtimes s ON m.movie_id = s.movie_id " +
	 * "JOIN cinema c ON s.cinema_id = c.cinema_id " + "GROUP BY m.title, c.name;";
	 * 
	 * ResultSet resultSet = conn.createStatement().executeQuery(query);
	 * 
	 * List<Label> allLabels = new ArrayList<Label>(); if (resultSet.next()) {
	 * String movieName = resultSet.getString("title"); String cinemaName =
	 * resultSet.getString("cinema_name"); String showtimes =
	 * resultSet.getString("showtimes");
	 * 
	 * allLabels.add(null); allLabels.add(null); allLabels.add(null);
	 * allLabels.get(0).setText("Movie Name: " + movieName);
	 * allLabels.get(1).setText("Cinema Name: " + cinemaName);
	 * allLabels.get(2).setText("Showtimes:\n" + showtimes); } conn.close(); return
	 * allLabels; }
	 */
}