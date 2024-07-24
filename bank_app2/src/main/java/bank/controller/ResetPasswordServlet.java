package bank.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.dao.ResetPasswordDAO;

@WebServlet("/ResetPasswordServlet")
public class ResetPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ResetPasswordDAO resetPasswordDAO;

    public void init() {
        resetPasswordDAO = new ResetPasswordDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNo = request.getParameter("accountNo");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");

        boolean isOldPasswordValid = resetPasswordDAO.verifyOldPassword(accountNo, oldPassword);
        if (isOldPasswordValid) {
            boolean isPasswordUpdated = resetPasswordDAO.updatePassword(accountNo, newPassword);
            if (isPasswordUpdated) {
                response.sendRedirect("passwordResetSuccess.jsp");
            } else {
                response.sendRedirect("passwordResetFailed.jsp");
            }
        } else {
            response.sendRedirect("passwordResetFailed.jsp");
        }
    }
}
