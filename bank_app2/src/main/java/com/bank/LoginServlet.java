package com.bank;

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

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/db1";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "Sasi@11154";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userType = request.getParameter("userType");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load JDBC driver

            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            String sql = "";
            if ("admin".equals(userType)) {
                sql = "SELECT * FROM admin WHERE username=? AND password=?";
            } else if ("customer".equals(userType)) {
                sql = "SELECT * FROM customer WHERE Name=? AND password=?";
            }

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                if ("admin".equals(userType)) {
                    response.sendRedirect("admindashboard.jsp");
                } else if ("customer".equals(userType)) {
                    Customer customer = new Customer(
                        rs.getString("AccountNo"),
                        rs.getString("Name"),
                        rs.getString("Password"),
                        rs.getString("Email"),
                        rs.getString("DOB"),
                        rs.getString("Address"),
                        rs.getString("Phone"),
                        rs.getString("Gender"),
                        rs.getString("AccountType"),
                        rs.getString("JoinDate"),
                        rs.getDouble("Balance")
                    );
                    request.getSession().setAttribute("customer", customer);
                    response.sendRedirect("customerdashboard.jsp");
                }
            } else {
                PrintWriter out = response.getWriter();
                out.println("<html><body><p>Authentication failed. Please check your username and password.</p></body></html>");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new ServletException("Database connection failed", e);
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "GET method is not supported. Please use the form to submit your login details.");
    }
}
