package dbhandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MovieReviewConnecter {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/Cinexus"; // Replace with your database URL
    private static final String USER = "root"; // Replace with your database username
    private static final String PASSWORD = "4326"; // Replace with your database password

    // Establish a database connection
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load the MySQL JDBC driver
            return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found", e);
        }
    }

    // Close the database connection
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                // Handle the exception as needed
                e.printStackTrace();
            }
        }
    }
}
