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

public class UserDatabaseConnecter implements DatabaseConnecter
{
	public int getID(String username,  String password)
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
	            int userID = rs.getInt("user_id");
	            return userID;
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
	public void InsertUser(User user)
	{
		Connection conn = null;
	    PreparedStatement stmt = null;

	    try 
	    {
	        conn = DriverManager.getConnection(URL, USER, PASSWORD);

	        String sql = "INSERT into users (username, password, name, cnic, email, role) "
	        		+ "VALUES (?, ?, ?, ?, ?, ?)";

	        stmt = conn.prepareStatement(sql);
	        stmt.setString(1, user.getUsername());
	        stmt.setString(2, user.getPassword());
	        stmt.setString(3, user.getName());
	        stmt.setString(4, user.getEmail());
	        stmt.setString(5, user.getcnic());
	        stmt.setString(6, user.getUserRole());
	        
	        int rows = stmt.executeUpdate();
	        if (rows > 0) {
	            System.out.println("User inserted successfully.");
	        } else {
	            System.out.println("User insertion failed.");
	        }
	    } 
	    catch (SQLException e) 
	    {
	        e.printStackTrace();
	    } 
	    finally 
	    {
	        try {
	            if (stmt != null) stmt.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
}