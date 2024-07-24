package bank.dao;

// TransactionDAO.java

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/db1";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Sasi@11154";

    // Method to get transactions by account number
 // TransactionDAO.java
    public List<Transaction> getTransactionsByAccountNo(String accountNo) {
        // Method implementation to fetch transactions by account number
    

        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT transactionID, accountNo, datetime, transactionType, amount FROM transaction WHERE accountNo = ?";
        
        try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, accountNo);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                int transactionID = rs.getInt("transactionID");
                String accountNumber = rs.getString("accountNo");
                Timestamp datetime = rs.getTimestamp("datetime");
                String transactionType = rs.getString("transactionType");
                double amount = rs.getDouble("amount");
                
                Transaction transaction = new Transaction(transactionID, accountNumber, datetime, transactionType, amount);
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return transactions;
    }
}
