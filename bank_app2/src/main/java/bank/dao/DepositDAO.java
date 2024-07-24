package bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class DepositDAO {
    private Connection conn;

    public DepositDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean depositAmount(String accountNo, double depositAmount) throws SQLException {
        String updateSql = "UPDATE customer SET balance = balance + ? WHERE accountNo = ?";
        String logSql = "INSERT INTO transaction (accountNo, datetime, transactionType, amount) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement updateStmt = conn.prepareStatement(updateSql);
             PreparedStatement logStmt = conn.prepareStatement(logSql)) {
            
            // Update balance
            updateStmt.setDouble(1, depositAmount);
            updateStmt.setString(2, accountNo);
            int rowsUpdated = updateStmt.executeUpdate();
            
            // Log transaction
            if (rowsUpdated > 0) {
                logStmt.setString(1, accountNo);
                logStmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
                logStmt.setString(3, "Deposit");
                logStmt.setDouble(4, depositAmount);
                logStmt.executeUpdate();
                return true;
            } else {
                return false;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error updating balance and logging transaction for account: " + accountNo, e);
        }
    }
}
