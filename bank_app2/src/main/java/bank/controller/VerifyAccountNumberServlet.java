package bank.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/verifyAccountNumber")
public class VerifyAccountNumberServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/db1";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "Sasi@11154";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String accountNo = request.getParameter("accountNo");

        // Validate accountNo (optional)
        if (accountNo == null || accountNo.isEmpty()) {
            out.println("<html><body><h2>Account number is required.</h2></body></html>");
            return;
        }

        try {
            // Load and register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish database connection
            try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
                String sql = "SELECT * FROM customer WHERE AccountNo = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, accountNo);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    // Customer details found, set attributes for the next JSP
                    request.setAttribute("accountNo", rs.getString("AccountNo"));
                    request.setAttribute("name", rs.getString("Name"));
                    request.setAttribute("email", rs.getString("Email"));
                    request.setAttribute("dob", rs.getString("DOB"));
                    request.setAttribute("address", rs.getString("Address"));
                    request.setAttribute("phone", rs.getString("Phone"));
                    request.setAttribute("gender", rs.getString("Gender"));
                    request.setAttribute("accountType", rs.getString("AccountType"));

                    // Forward to displayDetails.jsp to show details
                    request.getRequestDispatcher("/displayDetails.jsp").forward(request, response);
                } else {
                    // Handle case where account number does not exist
                    out.println("<html><body><h2>No customer found for account number " + accountNo + "</h2></body></html>");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            out.println("<html><body><h2>Error occurred: " + e.getMessage() + "</h2></body></html>");
            e.printStackTrace();
        }
    }
}
	