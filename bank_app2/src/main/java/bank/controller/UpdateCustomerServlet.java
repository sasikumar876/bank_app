package bank.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/updateCustomer")
public class UpdateCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/db1";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "Sasi@11154";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get parameters from request
        String accountNo = request.getParameter("accountNo");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String dob = request.getParameter("dob");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String gender = request.getParameter("gender");

        // Update query
        String sql = "UPDATE customer SET Name = ?, Email = ?, DOB = ?, Address = ?, Phone = ?, Gender = ? WHERE AccountNo = ?";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, dob);
            stmt.setString(4, address);
            stmt.setString(5, phone);
            stmt.setString(6, gender);
            stmt.setString(7, accountNo);

            // Execute update
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                // Redirect to a success page or back to displayDetails.jsp
                response.sendRedirect(request.getContextPath() + "/displayDetails.jsp?accountNo=" + accountNo);
            } else {
                // Handle update failure
                response.getWriter().println("<html><body><h2>Failed to update customer details for account number " + accountNo + "</h2></body></html>");
            }
        } catch (SQLException e) {
            // Handle SQL exceptions
            throw new ServletException("Error updating customer details", e);
        }
    }
}
