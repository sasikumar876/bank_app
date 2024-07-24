package bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDetailsDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/db1";
    private static final String USER = "root";
    private static final String PASSWORD = "Sasi@11154";

    public List<Customer> getCustomerDetails(long accountNumber) throws SQLException {
        List<Customer> customers = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            String sql = "SELECT AccountNo, Name, Email, DOB, Address, Phone, Gender, AccountType, JoinDate FROM customer WHERE AccountNo = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, accountNumber);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer();
                customer.setAccountNo(rs.getLong("AccountNo"));
                customer.setName(rs.getString("Name"));
                customer.setEmail(rs.getString("Email"));
                customer.setDob(rs.getDate("DOB"));
                customer.setAddress(rs.getString("Address"));
                customer.setPhone(rs.getString("Phone"));
                customer.setGender(rs.getString("Gender"));
                customer.setAccountType(rs.getString("AccountType"));
                customer.setJoinDate(rs.getDate("JoinDate"));
                customers.add(customer);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error in fetching customer details.");
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return customers;
    }
}
