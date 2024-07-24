package bank.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.Customer;

@WebServlet("/listCustomerServlet")
public class ListCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/db1";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "Sasi@11154";

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
                String accountNo = rs.getString("AccountNo");
                String name = rs.getString("Name");
                String address = rs.getString("Address");
                String mobile = rs.getString("Phone");
                String email = rs.getString("Email");
                String gender = rs.getString("Gender");
                String accountType = rs.getString("AccountType");
                String dob = rs.getString("DOB");

                Customer customer = new Customer(accountNo, name, address, mobile, email, gender, accountType, dob,dob, dob, 0);
                customers.add(customer);
            }

            // Set customers list as an attribute in the request scope
            request.setAttribute("customers", customers);
            
            // Forward to the JSP page for displaying the list
            request.getRequestDispatcher("admindashboard.jsp").forward(request, response);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new ServletException("Database connection or query failed", e);
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
