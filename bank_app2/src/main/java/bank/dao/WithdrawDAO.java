package bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class WithdrawDAO {
    private Connection conn;

    public WithdrawDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean withdrawAmount(String accountNo, double withdrawAmount) throws SQLException {
        String updateSql = "UPDATE customer SET balance = balance - ? WHERE accountNo = ?";
        String logSql = "INSERT INTO transaction (accountNo, datetime, transactionType, amount) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement updateStmt = conn.prepareStatement(updateSql);
             PreparedStatement logStmt = conn.prepareStatement(logSql)) {
            
            // Update balance
            updateStmt.setDouble(1, withdrawAmount);
            updateStmt.setString(2, accountNo);
            int rowsUpdated = updateStmt.executeUpdate();
            
            // Log transaction
            if (rowsUpdated > 0) {
                logStmt.setString(1, accountNo);
                logStmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
                logStmt.setString(3, "Withdrawal");
                logStmt.setDouble(4, withdrawAmount);
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
