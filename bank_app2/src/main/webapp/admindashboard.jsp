<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 80%;
            margin: auto;
            overflow: hidden;
        }
        header {
            background: #333;
            color: #fff;
            padding-top: 30px;
            min-height: 70px;
            border-bottom: #77b300 3px solid;
        }
        header a {
            color: #fff;
            text-decoration: none;
            text-transform: uppercase;
            font-size: 16px;
        }
        header ul {
            padding: 0;
            list-style: none;
        }
        header li {
            float: left;
            display: inline;
            padding: 0 20px 0 20px;
        }
        header #branding {
            float: left;
        }
        header #branding h1 {
            margin: 0;
        }
        header nav {
            float: right;
            margin-top: 10px;
        }
        .button {
            height: 38px;
            padding-left: 20px;
            padding-right: 20px;
            color: #fff;
            border: 0;
            cursor: pointer;
            border-radius: 5px;
            font-size: 14px;
            text-transform: uppercase;
            margin: 10px 5px;
            display: flex; /* Use flexbox */
            justify-content: center; /* Center horizontally */
            align-items: center; /* Center vertically */
        }
        .button-blue {
            background-color: #333;
        }
        .button-blue:hover {
            background-color: red;
        }
        .button-green {
            background-color: #28a745;
        }
        .button-green:hover {
            background-color: red;
        }
        .button-red {
            background-color: #dc3545;
        }
        .button-red:hover {
            background-color: red;
        }
        .button-orange {
            background-color: #fd7e14;
        }
        .button-orange:hover {
            background-color: red;
        }
        .content {
            padding: 20px;
            background: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        form {
            margin: 20px 0;
        }
        label, input, select {
            display: block;
            margin-bottom: 10px;
        }
        input, select {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
        }
        .button-container {
            display: flex;
            justify-content: center;
            gap: 10px;
        }
        .large-button {
            font-size: 18px; /* Increase font size */
            padding: 15px 25px; /* Increase padding */
        }
    </style>
</head>
<body>
<header>
    <div class="container">
        <div id="branding">
            <h1>Admin Dashboard</h1>
        </div>
        <nav>
            <ul>
                <li><a href="index.html">Logout</a></li>
            </ul>
        </nav>
    </div>
</header>
<div class="container">
    <div class="content">
        <h2>Register New Customer</h2>
        <form id="registerForm" action="registerCustomerServlet" method="post" onsubmit="return handleFormSubmit(event)">
           
            
            <label for="name">Full Name</label>
            <input type="text" id="name" name="name" required>
            
            <label for="address">Address</label>
            <input type="text" id="address" name="address" required>
            
            <label for="mobile">Mobile No</label>
            <input type="text" id="mobile" name="mobile" required>
            
            <label for="email">Email ID</label>
            <input type="email" id="email" name="email" required>
            
            <label for="gender">Gender</label>
            <input type="text" id="gender" name="gender" required>
            
            <label for="accountType">Type of Account</label>
            <select id="accountType" name="accountType" required>
                <option value="saving">Saving Account</option>
                <option value="current">Current Account</option>
            </select>
            
            <label for="balance">Initial Balance</label>
            <input type="number" id="balance" name="balance" min="1000" required>
            
            <label for="dob">Date of Birth</label>
            <input type="date" id="dob" name="dob" required>
            
            <input type="submit" value="Register" class="button button-blue">
        </form>
        
        <div class="button-container">
            <form action="customerDetails.html" method="post" style="display:inline;">
                <input type="submit" value="Customer Details" class="button button-orange large-button">
            </form>
            <form action="editCustomer.jsp" method="post" style="display:inline;">
                <input type="submit" value="Edit Account" class="button button-green large-button">
            </form>
            <form action="del.jsp" method="post" style="display:inline;">
                <input type="submit" value="Delete Account" class="button button-red large-button" onclick="return confirm('Are you sure you want to delete this customer?');">
            </form>
        </div>
    </div>
</div>
<script>
    function validateForm() {
        var balance = document.getElementById('balance').value;
        if (balance < 1000) {
            alert('Initial balance must be at least 1000');
            return false;
        }
        return true;
    }

    function handleFormSubmit(event) {
        event.preventDefault(); // Prevent default form submission
        if (validateForm()) {
            alert('Customer successfully registered'); // Show success message
            document.getElementById('registerForm').submit(); // Manually submit the form
        }
    }
</script>
</body>
</html>
