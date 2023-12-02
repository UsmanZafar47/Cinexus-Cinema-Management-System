package dbhandler;

import java.sql.*;

public class DatabaseConnecter
{
<<<<<<< Updated upstream
	private static final String URL = "jdbc:mysql://localhost/Cinexus";
	private static final String USER = "root";
	private static final String PASSWORD = "mySQLroot";
=======
	static final String URL = "jdbc:mysql://localhost/Cinexus";
	static final String USER = "root";
	static final String PASSWORD = "4326";
>>>>>>> Stashed changes
	
	public static String userAuthentication(String username, String password)
	{
		Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	
	    try 
	    {
	        conn = DriverManager.getConnection(URL, USER, PASSWORD);

	        String sql = "SELECT role FROM users WHERE username = ? AND password = ?";

	        stmt = conn.prepareStatement(sql);
	        stmt.setString(1, username);
	        stmt.setString(2, password);

	        rs = stmt.executeQuery();

	        if (rs.next()) {
	            String Role = rs.getString("role");
	            return Role;
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
