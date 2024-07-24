package bank.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.Customer;

@WebServlet("/registerCustomerServlet")
public class RegisterCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/db1";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "Sasi@11154";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String mobile = request.getParameter("mobile");
        String email = request.getParameter("email");
        String accountType = request.getParameter("accountType");
        double balance = Double.parseDouble(request.getParameter("balance"));
        String dob = request.getParameter("dob");
        String gender = request.getParameter("gender");

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            String accountNo = generateAccountNo();
            String password = generatePassword();

            String sql = "INSERT INTO customer (AccountNo, Name, Password, Email, DOB, Address, Phone, Gender, AccountType, JoinDate, Balance) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, accountNo);
            pstmt.setString(2, name);
            pstmt.setString(3, password);
            pstmt.setString(4, email);
            pstmt.setString(5, dob);
            pstmt.setString(6, address);
            pstmt.setString(7, mobile);
            pstmt.setString(8, gender != null ? gender : "");
            pstmt.setString(9, accountType);
            pstmt.setTimestamp(10, new Timestamp(System.currentTimeMillis()));
            pstmt.setDouble(11, balance);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                // Registration successful, redirect to dashboard with success message
                response.sendRedirect("admindashboard.jsp?registered=true");
            } else {
                response.getWriter().println("Customer registration failed");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new ServletException("Database connection failed", e);
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private String generateAccountNo() {
        return String.valueOf(System.currentTimeMillis());
    }

    private String generatePassword() {
        return "tempPassword"; // Replace with actual password generation logic
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Customer> customers = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            String sql = "SELECT AccountNo, Name, Address, Phone, Email, Gender, AccountType, DOB FROM customer";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer(sql, sql, sql, sql, sql, sql, sql, sql, sql, sql, 0);
                customer.setAccountNo(rs.getString("AccountNo"));
                customer.setName(rs.getString("Name"));
                customer.setAddress(rs.getString("Address"));
                customer.setMobile1(rs.getString("Phone"));
                customer.setEmail(rs.getString("Email"));
                customer.setGender(rs.getString("Gender"));
                customer.setAccountType(rs.getString("AccountType"));
                customer.setDob(rs.getString("DOB")); // Assuming DOB is stored as string in database
                customers.add(customer);
            }

            request.setAttribute("customers", customers);
            request.getRequestDispatcher("admindashboard.jsp").forward(request, response);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new ServletException("Error fetching customers from database", e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
