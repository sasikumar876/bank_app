<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
        }
        .title {
            text-align: center;
            background-color: #333;
            color: white;
            padding: 20px 0;
            font-size: 32px; /* Larger font size */
            font-weight: bold; /* Ensure bold text */
            text-transform: uppercase; /* Convert text to uppercase */
            font-family: "Times New Roman", Times, serif; /* Times New Roman font */
            margin-bottom: 20px;
        }
        .navbar {
            background-color: #192a56; /* Dark blue background color */
            overflow: hidden;
            width: 250px; /* Set width for left-side navbar */
            height: 100vh; /* Full height of viewport */
            position: fixed; /* Fixed position to stay on the left */
            top: 0;
            left: 0;
            padding-top: 80px; /* Adjust padding to accommodate title height */
        }
        .navbar a {
            display: block;
            color: white;
            padding: 16px;
            text-decoration: none;
            font-size: 18px;
            text-align: center; /* Center align text */
        }
        .navbar a:hover {
            background-color: #273c75; /* Darker blue on hover */
        }
        .dashboard {
            margin-left: 250px; /* Adjust content margin to accommodate navbar */
            padding: 20px;
        }
        .dashboard h1 {
            margin-bottom: 20px;
            text-align: center;
        }
        .details {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr)); /* Responsive grid layout */
            gap: 15px; /* Gap between grid items */
        }
        .detail-item {
            background-color: #ffffff; /* White background for each detail */
           padding: 10px;
    border: 1px solid #ddd; /* Light border */
    border-radius: 5px; /* Rounded corners */
    margin-bottom: 10px; /* Bottom margin for spacing */
    min-width: 250px; /* Adjust minimum width as needed */
}
.detail-item p {
    margin: 5px 0;
}
    </style>
</head>
<body>
    <div class="title">
        CUSTOMER DASHBOARD
    </div>

    <div class="navbar">
        <a href="customerdashboard.jsp">Home</a>
        <a href="deposit.jsp">Deposit</a>
        <a href="withdraw.jsp">Withdraw</a>
        <a href="viewTransaction.jsp">View Transactions</a>
        <a href="resetPassword.jsp">Reset Password</a>
        <a href="closedash.jsp">Close Account</a>
        <a href="index.html">Logout</a>
    </div>

    <div class="dashboard">
        <h1>Welcome, ${customer.name}!</h1>
        <div class="details">
            <div class="detail-item">
                <p><strong>Account Number:</strong> ${customer.accountNo}</p>
            </div>
            <div class="detail-item">
                <p><strong>Email:</strong> ${customer.email}</p>
            </div>
            <div class="detail-item">
                <p><strong>Date of Birth:</strong> ${customer.dob}</p>
            </div>
            <div class="detail-item">
                <p><strong>Address:</strong> ${customer.address}</p>
            </div>
            <div class="detail-item">
                <p><strong>Phone:</strong> ${customer.phone}</p>
            </div>
            <div class="detail-item">
                <p><strong>Gender:</strong> ${customer.gender}</p>
            </div>
            <div class="detail-item">
                <p><strong>Account Type:</strong> ${customer.accountType}</p>
            </div>
            <div class="detail-item">
                <p><strong>Join Date:</strong> ${customer.joinDate}</p>
            </div>
            <div class="detail-item">
                <p><strong>Balance:</strong> ${customer.balance}</p>
            </div>
        </div>
    </div>

</body>
</html>
