<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            padding: 20px;
        }
        div {
            max-width: 600px;
            margin: 0 auto;
            background: #f9f9f9;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin-bottom: 5px;
        }
        input[type="text"],
        input[type="email"],
        input[type="date"],
        select {
            width: calc(100% - 20px);
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
        }
        .button {
            background-color: #4CAF50; /* Green */
            border: none;
            color: white;
            padding: 15px 32px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            cursor: pointer;
            border-radius: 4px;
        }
        .button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div>
        <h2>Customer Details</h2>
        <form action="updateCustomer" method="post">
            <label for="accountNo">Account No:</label>
            <input type="text" id="accountNo" name="accountNo" value="${accountNo}" readonly>
            <br><br>
            
            <label for="name">Full Name:</label>
            <input type="text" id="name" name="name" value="${name}" required>
            <br><br>
    
            <label for="email">Email ID:</label>
            <input type="email" id="email" name="email" value="${email}" required>
            <br><br>
    
            <label for="dob">Date of Birth:</label>
            <input type="date" id="dob" name="dob" value="${dob}" required>
            <br><br>
    
            <label for="address">Address:</label>
            <input type="text" id="address" name="address" value="${address}" required>
            <br><br>
    
            <label for="phone">Phone:</label>
            <input type="text" id="phone" name="phone" value="${phone}" required>
            <br><br>
    
            <label for="gender">Gender:</label>
            <input type="text" id="gender" name="gender" value="${gender}" required>
            <br><br>
    
            <label for="accountType">Account Type:</label>
            <input type="text" id="accountType" name="accountType" value="${accountType}" readonly>
            <br><br>
    
            <!-- Submit Button -->
            <input type="submit" value="Update Data" class="button">
        </form>
    </div>
</body>
</html>
