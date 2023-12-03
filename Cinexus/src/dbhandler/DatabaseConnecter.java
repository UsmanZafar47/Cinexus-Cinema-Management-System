package dbhandler;

import java.sql.*;

public interface DatabaseConnecter 
{
	static final String URL = "jdbc:mysql://localhost/Cinexus";
	static final String USER = "root";
	static final String PASSWORD = "mySQLroot";
	
    String get(int userID, String columnName);
}