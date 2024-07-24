package bank.controller; // Adjust your package name accordingly

import java.io.IOException;
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

@WebServlet("/closeAccountServlet")
public class CloseAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNo = request.getParameter("accountNo");
        
        if (accountNo != null && !accountNo.isEmpty()) {
            boolean accountClosed = closeAccount(accountNo);
            
            if (accountClosed) {
                // Redirect to success page or handle success message
                response.sendRedirect("accountClosed.jsp"); // Replace with your success page
            } else {
                // Redirect to error page or handle error message
                response.sendRedirect("error.jsp"); // Replace with your error page
            }
        } else {
            // Handle invalid request (no account number provided)
            response.sendRedirect("error.jsp"); // Replace with appropriate error handling
        }
    }

    private boolean closeAccount(String accountNo) {
        boolean accountClosed = false;
        
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db2", "root", "Ashok@123");
             PreparedStatement pstmtSelect = conn.prepareStatement("SELECT balance FROM customer WHERE accountNo = ?");
             PreparedStatement pstmtDelete = conn.prepareStatement("DELETE FROM customer WHERE accountNo = ?")) {
            
            conn.setAutoCommit(false); // Set auto-commit to false to manage transaction
            
            // Retrieve balance for the given account
            pstmtSelect.setString(1, accountNo);
            ResultSet rs = pstmtSelect.executeQuery();
            
            if (rs.next()) {
                double balance = rs.getDouble("balance");
                
                if (balance == 0.00) {
                    // If balance is 0.00, proceed with account deletion
                    pstmtDelete.setString(1, accountNo);
                    int rowsAffected = pstmtDelete.executeUpdate();
                    
                    if (rowsAffected > 0) {
                        accountClosed = true;
                        conn.commit(); // Commit transaction if deletion is successful
                    } else {
                        conn.rollback(); // Rollback transaction if no rows affected
                    }
                } else {
                    // Handle case where balance is not 0.00 (cannot delete)
                    // You may redirect to an error page or handle this case as per your application's logic
                    System.out.println("Account balance is not zero. Cannot delete.");
                }
            } else {
                // Handle case where accountNo doesn't exist
                // You may redirect to an error page or handle this case as per your application's logic
                System.out.println("Account number not found.");
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace(); // Handle exceptions appropriately in production
        }
        
        return accountClosed;
    }
}
