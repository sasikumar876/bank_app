package bank.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import bank.dao.AccountDAO;

@WebServlet("/DeleteAccountServlet")
public class DeleteAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNo = request.getParameter("accountNo");
        AccountDAO accountDAO = new AccountDAO();
        boolean isDeleted = false;
        try {
            isDeleted = accountDAO.deleteAccountIfZeroBalance(accountNo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (isDeleted) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("delscuccuss.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Account not found, has non-zero balance, or error occurred.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
        }
    }
}
