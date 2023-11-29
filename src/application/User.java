package application;
import java.sql.*;



public class User {

    // Method to authenticate a user
	public static boolean authenticate(String username, String password, String role) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnecter.getConnection(); // Assume DatabaseConnector is your database connection class
                String sql = "SELECT password FROM users WHERE username = ? AND role = ?";
                // Set the parameters and proceed as before
                stmt.setString(2, role);
                
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);

            rs = stmt.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");
                String storedRole = rs.getString("role"); // Assuming the role is stored in the 'role' column

                // Check both password and role
                return storedPassword.equals(password) && storedRole.equalsIgnoreCase(role);
            }
            return false;
        } catch (SQLException e) {
            // Handle exceptions
            e.printStackTrace();
            return false;
        } finally {
            // Close all the connections
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
