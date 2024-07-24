package bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResetPasswordDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/db1";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Sasi@11154";

    private static final String SELECT_PASSWORD_BY_ACCOUNT_NO = "SELECT Password FROM customer WHERE AccountNo = ?";
    private static final String UPDATE_PASSWORD_SQL = "UPDATE customer SET Password = ? WHERE AccountNo = ?";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public boolean verifyOldPassword(String accountNo, String oldPassword) {
        boolean isValid = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PASSWORD_BY_ACCOUNT_NO)) {
            preparedStatement.setString(1, accountNo);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("Password");
                if (storedPassword.equals(oldPassword)) {
                    isValid = true;
                }
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return isValid;
    }

    public boolean updatePassword(String accountNo, String newPassword) {
        boolean rowUpdated = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PASSWORD_SQL)) {
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, accountNo);
            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
