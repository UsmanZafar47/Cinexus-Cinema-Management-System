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
	public int getID(String name, String location, int id)
	{
		Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    try 
	    {
	        conn = DriverManager.getConnection(URL, USER, PASSWORD);
	        String sql = "SELECT cinema_id FROM cinema WHERE name = ? AND location = ? AND manager_id = ?";
	        stmt = conn.prepareStatement(sql);
	        stmt.setString(1, name);
	        stmt.setString(2, location);
	        stmt.setInt(3, id);

	        rs = stmt.executeQuery();

	        if (rs.next()) {
	            int cinemaID = rs.getInt("cinema_id");
	            return cinemaID;
	        }
	        return -1;
	    } 
	    catch (SQLException e) 
	    {
	        e.printStackTrace();
	        return -1;
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
	        String sql = "SELECT "+columnName+" FROM cinema WHERE cinema_id = " + id;
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
	
	public void AddSeats(int id) {
		Connection conn = null;
	    PreparedStatement stmt = null;
	    try {
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement statement = conn.createStatement();
        statement.executeUpdate("INSERT into seats (cinema_id) VALUES ("+id+")");

        if (stmt != null) stmt.close();
        if (conn != null) conn.close(); 
	    }
	    catch (SQLException e) {
	        e.printStackTrace(); } 
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
	public void InsertCinema(Cinema cinema)
	{
		Connection conn = null;
	    PreparedStatement stmt = null;

	    try 
	    {
	        conn = DriverManager.getConnection(URL, USER, PASSWORD);

	        String sql = "INSERT into cinema (name, location, manager_id) "
	        		+ "VALUES (?, ?, ?)";

	        stmt = conn.prepareStatement(sql);
	        stmt.setString(1, cinema.getName());
	        stmt.setString(2, cinema.getLocation());
	        stmt.setString(3, ""+cinema.getManager_id());
	        
	        int rows = stmt.executeUpdate();
	        if (rows > 0)
	            System.out.println("Cinema inserted successfully.");
	         else 
	            System.out.println("Cinema insertion failed.");
            if (stmt != null) stmt.close();
            if (conn != null) conn.close(); 
	    } 
	    catch (SQLException e) 
	    {
	        e.printStackTrace();
	    }
	}
}