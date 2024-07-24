<%@ page import="java.util.List" %>
<%@ page import="bank.dao.TransactionDAO" %>
<%@ page import="bank.dao.Transaction" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Transactions for Account</title>
    <style>
        /* Define your styles here */
        /* For example: */
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
        }
        h2 {
            margin-bottom: 20px;
        }
        .form-container {
            max-width: 600px;
            margin: 20px auto;
            padding: 20px;
            border: 1px solid #ddd;
            background-color: #f9f9f9;
            border-radius: 5px;
        }
        .form-container label {
            display: block;
            margin-bottom: 10px;
        }
        .form-container input[type="text"] {
            width: calc(100% - 20px);
            padding: 8px;
            margin-bottom: 10px;
        }
        .form-container input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            cursor: pointer;
            border-radius: 5px;
        }
        .form-container input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h2>Enter Account Number to View Transactions:</h2>
        <form action="viewTransaction.jsp" method="get">
            <label for="accountNo">Account Number:</label>
            <input type="text" id="accountNo" name="accountNo" required>
            <input type="submit" value="View Transactions">
        </form>
    </div>

    <%-- Process and display transactions --%>
    <% 
        String accountNo = request.getParameter("accountNo");
        if (accountNo != null && !accountNo.isEmpty()) {
            TransactionDAO transactionDAO = new TransactionDAO();
            List<Transaction> transactions = transactionDAO.getTransactionsByAccountNo(accountNo);
            
            if (transactions.isEmpty()) {
    %>
                <h2>No transactions found for account: <%= accountNo %></h2>
    <%     } else { %>
                <h2>Transactions for Account: <%= accountNo %></h2>
                <table>
                    <tr>
                        <th>Transaction ID</th>
                        <th>Date and Time</th>
                        <th>Type</th>
                        <th>Amount</th>
                    </tr>
                    <% for (Transaction transaction : transactions) { %>
                        <tr>
                            <td><%= transaction.getTransactionID() %></td>
                            <td><%= transaction.getDatetime() %></td>
                            <td><%= transaction.getTransactionType() %></td>
                            <td><%= transaction.getAmount() %></td>
                        </tr>
                    <% } %>
                </table>
    <%     } 
        } else {
            // Handle case where accountNo is null or empty
            System.out.println("Account Number is null or empty");
        }
    %>
</body>
</html>
