package bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bank.Customer;

public class AdminDashboardDAO {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/db1";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "Sasi@11154";

    public List<Customer> getAllCustomers() throws SQLException, ClassNotFoundException {
        List<Customer> customers = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            String sql = "SELECT AccountNo, Name, Address, Phone, Email, AccountType, DOB, Gender, JoinDate FROM customer";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer(sql, sql, sql, sql, sql, sql, sql, sql, sql, sql, 0);
                customer.setAccountNo(rs.getString("AccountNo"));
                customer.setName(rs.getString("Name"));
                customer.setAddress(rs.getString("Address"));
                customer.setPhone(rs.getString("Phone"));
                customer.setEmail(rs.getString("Email"));
                customer.setAccountType(rs.getString("AccountType"));
                customer.setDob(rs.getString("DOB"));
                customer.setGender(rs.getString("Gender"));
                customer.setJoinDate(rs.getString("JoinDate"));
                customers.add(customer);
            }

        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }

        return customers;
    }
}
