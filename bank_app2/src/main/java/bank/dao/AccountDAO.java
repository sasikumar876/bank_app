package bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/db1";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "Sasi@11154";

    private static final String CHECK_BALANCE_SQL = "SELECT Balance FROM customer WHERE accountNo = ?";
    private static final String DELETE_ACCOUNT_SQL = "DELETE FROM customer WHERE accountNo = ?";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public boolean deleteAccountIfZeroBalance(String accountNo) {
        boolean isDeleted = false;
        try (Connection connection = getConnection();
             PreparedStatement checkBalanceStmt = connection.prepareStatement(CHECK_BALANCE_SQL);
             PreparedStatement deleteAccountStmt = connection.prepareStatement(DELETE_ACCOUNT_SQL)) {
            
            // Check if the balance is zero
            checkBalanceStmt.setString(1, accountNo);
            ResultSet rs = checkBalanceStmt.executeQuery();
            if (rs.next()) {
                double balance = rs.getDouble("Balance");
                System.out.println("Balance for account " + accountNo + " is: " + balance);
                if (balance == 0.00) {
                    // Delete the account if the balance is zero
                    deleteAccountStmt.setString(1, accountNo);
                    isDeleted = deleteAccountStmt.executeUpdate() > 0;
                    System.out.println("Account " + accountNo + " deleted: " + isDeleted);
                }
            } else {
                System.out.println("Account " + accountNo + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isDeleted;
    }
}
