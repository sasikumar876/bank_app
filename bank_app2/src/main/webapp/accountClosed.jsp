<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Account Closed</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
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
            background-color: orange;
            padding: 10px;
            border-radius: 5px;
            margin-bottom: 10px;
        }
        .balance {
            font-weight: bold;
            color: red;
        }
        .button-container {
            display: flex;
            justify-content: center;
            margin-top: 20px;
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
    </style>
</head>
<body>
    <div class="container">
        <h1>Account Closed</h1>
        
        <div class="orange-block">
            <p>Your account has been successfully closed.</p>
        </div>
        
        <div class="button-container">
            <button class="back-button" onclick="backToHome()">Back to Home</button>
        </div>
    </div>

    <script>
        function backToHome() {
            window.location.href = 'index.jsp'; // Replace with your home page URL
        }
    </script>
</body>
</html>
