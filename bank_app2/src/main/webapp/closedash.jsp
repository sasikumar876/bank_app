<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Dashboard</title>
    <style>
        body {
            font-family: Times New Roman, serif; /* User preference */
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f8f9fa;
            margin: 0;
        }
        .container {
            text-align: center;
            background-color: #ffffff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 16px rgba(0,0,0,0.2);
            max-width: 400px;
            width: 80%;
        }
        h1 {
            color: #333;
            font-size: 24px;
            margin-bottom: 20px;
        }
        p {
            color: #333;
            font-size: 18px;
            text-align: left;
            padding-left: 20px;
        }
        .orange-block {
            background-color: orange; /* User preference */
            padding: 10px;
            border-radius: 5px;
            margin-bottom: 10px;
        }
        .balance {
            font-weight: bold;
            color: red; /* User preference */
        }
        .button-container {
            display: flex;
            justify-content: space-between;
            margin-top: 20px;
        }
        .close-button {
            background-color: #f44336;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
        }
        .close-button:hover {
            background-color: #d32f2f;
        }
        .back-button {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
        }
        .back-button:hover {
            background-color: #0056b3;
        }
        /* Styles for pop-up overlay */
        .overlay {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            display: none;
            justify-content: center;
            align-items: center;
        }
        .popup {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 16px rgba(0,0,0,0.2);
            max-width: 80%;
            width: 400px;
            display: none;
        }
        .close-popup {
            position: absolute;
            top: 10px;
            right: 10px;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>CUSTOMER DASHBOARD</h1>
        
        <div class="orange-block">
            <p>Account Number: ${customer.accountNo}</p>
            <p>Name: ${customer.name}</p>
            <p>Email: ${customer.email}</p>
            <p>Date of Birth: ${customer.dob}</p>
            <p>Address: ${customer.address}</p>
            <p>Phone: ${customer.phone}</p>
            <p>Gender: ${customer.gender}</p>
            <p>Account Type: ${customer.accountType}</p>
            <p>Join Date: ${customer.joinDate}</p>
        </div>
        
        <p class="balance">Balance: ${customer.balance}</p>
        
        <div class="button-container">
            <button class="close-button" onclick="closeAccount('${customer.accountNo}', ${customer.balance})">Close Account</button>
            <button class="back-button" onclick="backToHome()">Back to Home</button>
        </div>
    </div>

    <!-- Pop-up overlay -->
    <div id="popupOverlay" class="overlay">
        <div id="popupContent" class="popup">
            <span class="close-popup" onclick="closePopup()">&times;</span>
            <h2>Account Closure</h2>
            <p>Are you sure you want to close your account?</p>
            <button class="close-button" onclick="confirmCloseAccount('${customer.accountNo}')">Confirm</button>
            <button class="back-button" onclick="closePopup()">Cancel</button>
        </div>
    </div>

    <script>
        function closeAccount(accountNo, balance) {
            if (balance === 0) {
                // Display the pop-up overlay
                document.getElementById('popupOverlay').style.display = 'flex';
                document.getElementById('popupContent').style.display = 'block';
            } else {
                alert("Cannot close account. Balance should be zero.");
            }
        }

        function confirmCloseAccount(accountNo) {
            // Perform account closure action (e.g., call servlet to close account)
            window.location.href = 'closeAccountServlet?accountNo=' + accountNo; // Redirect to servlet
        }

        function closePopup() {
            // Hide the pop-up overlay
            document.getElementById('popupOverlay').style.display = 'none';
            document.getElementById('popupContent').style.display = 'none';
        }

        function backToHome() {
            window.location.href = 'customerdashboard.jsp'; // Replace with your home page URL
        }
    </script>
</body>
</html>
