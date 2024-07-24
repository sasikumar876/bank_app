package bank.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.dao.DepositDAO;

@WebServlet("/DepositServlet")
public class DepositServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Connection conn;

    public void init() throws ServletException {
        // Initialize database connection
        String jdbcUrl = "jdbc:mysql://localhost:3306/db1";
        String user = "root";
        String password = "Sasi@11154";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(jdbcUrl, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("Error initializing database connection", e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accountNo = request.getParameter("accountNo");
        String amountStr = request.getParameter("depositAmount");

        // Check if amountStr is null or empty
        if (amountStr == null || amountStr.trim().isEmpty()) {
            redirectToFailurePage(request, response, "Invalid Deposit Amount");
            return;
        }

        // Validate and parse deposit amount
        double depositAmount = 0.0;
        try {
            depositAmount = Double.parseDouble(amountStr);
        } catch (NumberFormatException e) {
            redirectToFailurePage(request, response, "Invalid Deposit Amount");
            return;
        }

        boolean success = false;

        // Perform deposit operation using DAO
        DepositDAO depositDAO = new DepositDAO(conn);
        try {
            success = depositDAO.depositAmount(accountNo, depositAmount);

            // Redirect based on success or failure
            if (success) {
                redirectToSuccessPage(request, response, "Deposit Successful");
            } else {
                redirectToFailurePage(request, response, "Deposit Failed");
            }
        } catch (SQLException e) {
            redirectToFailurePage(request, response, "Deposit Failed: Database Error");
        }
    }

    private void redirectToSuccessPage(HttpServletRequest request, HttpServletResponse response, String message) throws IOException {
        response.sendRedirect(request.getContextPath() + "/success.jsp?message=" + message);
    }

    private void redirectToFailurePage(HttpServletRequest request, HttpServletResponse response, String message) throws IOException {
        response.sendRedirect(request.getContextPath() + "/failure.jsp?message=" + message);
    }

    public void destroy() {
        // Cleanup resources
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
