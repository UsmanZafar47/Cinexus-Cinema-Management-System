package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbhandler.DatabaseConnecter;

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

	        String sql = "SELECT userID FROM users WHERE username = ? AND password = ?";

	        stmt = conn.prepareStatement(sql);
	        stmt.setString(1, username);
	        stmt.setString(2, password);

	        rs = stmt.executeQuery();

	        if (rs.next()) {
	            String userID = rs.getString("userID");
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

	        String sql = "SELECT "+columnName+" FROM users WHERE userID = " + id;

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
}