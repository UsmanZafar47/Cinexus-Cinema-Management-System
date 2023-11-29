package application;

import java.sql.*;

public class DatabaseConnecter {

	/*
	 * public static void main(String[] args) throws Exception {
	 * 
	 * Class.forName("com.mysql.cj.jdbc.Driver");
	 * 
	 * System.out.println("Driver Loaded Successfully!");
	 * 
	 * Connection con=DriverManager.getConnection(
	 * "jdbc:mysql://localhost:3306/Cinexus","root","4326");
	 * 
	 * System.out.println("Connection Established!");
	 * 
	 * String sql = "INSERT INTO  users(user_id , username ,password) " +
	 * "VALUES (?, ?, ?)";
	 * 
	 * PreparedStatement statement = con.prepareStatement(sql); statement.setInt(1,
	 * 2); statement.setString(2, "Usman"); statement.setString(3, "ali");
	 * 
	 * 
	 * int rowsInserted = statement.executeUpdate(); if (rowsInserted > 0) {
	 * System.out.println("A new user registered!"); }
	 * 
	 * Statement stmt=con.createStatement(); ResultSet
	 * rs=stmt.executeQuery("select * from users");
	 * 
	 * while(rs.next()) { int id = rs.getInt(1); String username =
	 * rs.getString("username"); String password = rs.getString("password");
	 * 
	 * System.out.println(id+"  "+ username +"  "+password); } con.close();
	 * 
	 * }
	 */

	private static final String URL = "jdbc:mysql://localhost/cinexus";
	private static final String USER = "root";
	private static final String PASSWORD = "4326";

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}

}
