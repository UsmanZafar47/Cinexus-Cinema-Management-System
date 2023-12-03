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
import application.Tickets;
import application.User;
import javafx.scene.control.Label;

public class TicketDatabaseConnecter implements DatabaseConnecter
{
	public int getID(int ShowingID, int UserID)
	{
		Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    try 
	    {
	        conn = DriverManager.getConnection(URL, USER, PASSWORD);
	        String sql = "SELECT ticket_id FROM tickets WHERE user_id = ? AND showtime_id = ?";
	        stmt = conn.prepareStatement(sql);
	        stmt.setInt(1, UserID);
	        stmt.setInt(2, ShowingID);
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

	        String sql = "SELECT "+columnName+" FROM tickets WHERE ticket_id = " + id;

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
	
	public void InsertinDB(Tickets ticket, String status)
	{
		Connection conn = null;
	    PreparedStatement stmt = null;

	    try 
	    {
	        conn = DriverManager.getConnection(URL, USER, PASSWORD);
	        String sql = "INSERT into tickets (user_id, showtime_id, seatCount, price, status) "
	        		+ "VALUES (?, ?, ?, ?, ?)";

	        stmt = conn.prepareStatement(sql);
	        stmt.setInt(1, ticket.getUser().getUserID());
	        stmt.setInt(2, ticket.getShowing().getShowtime_id());
	        stmt.setString(3, ""+ticket.getSeatCount());
	        stmt.setDouble(4, (ticket.getPrice()));
	        stmt.setString(5, status);
	        
	        int rows = stmt.executeUpdate();
	        if (rows > 0)
	        	System.out.println("Ticket inserted successfully.");
	        else 
	        	System.out.println("Ticket insertion failed.");
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
	
	public void UpdateStatus(String id, String newstatus) 
	{
		Connection conn = null;
	    PreparedStatement stmt = null;
        try 
        {
	        conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = conn.createStatement();
            int rowsaff = statement.executeUpdate("UPDATE tickets SET status = "+newstatus+" WHERE ticket_id = " + id);
            
            statement.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }	
	public void MakePayment (int user_id, int amount)
	{
		Connection conn = null;
		PreparedStatement stmt = null;
	    try 
	    {
	        conn = DriverManager.getConnection(URL, USER, PASSWORD);
	        String sql = "INSERT into payments (user_id, amount) "
	        		+ "VALUES (?, ?)";

	        stmt = conn.prepareStatement(sql);
	        stmt.setInt(1, user_id);
	        stmt.setInt(2, amount);
	        
	        int rows = stmt.executeUpdate();
	        if (rows > 0)
	        	System.out.println("Payment made successfully.");
	        else 
	        	System.out.println("Payment failed.");
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
}
	
	