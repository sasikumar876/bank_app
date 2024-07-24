package bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO {
    private Connection conn;

    public CustomerDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean depositAmount(String accountNo, double depositAmount) {
        PreparedStatement stmt = null;
        boolean success = false;

        try {
            // SQL query to update balance
            String sql = "UPDATE customer SET Balance = Balance + ? WHERE AccountNo = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, depositAmount);
            stmt.setString(2, accountNo);

            // Execute the update
            int rowsAffected = stmt.executeUpdate();

            // Check if the update was successful
            if (rowsAffected > 0) {
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close PreparedStatement in finally block
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return success;
    }

    // Add more methods for other operations like customer registration, balance retrieval, etc.
}