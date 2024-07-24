<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f8d7da;
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
            color: #721c24;
            font-size: 24px;
            margin-bottom: 20px;
        }
        p {
            color: #721c24;
            font-size: 18px;
        }
        button {
            background-color: #f44336;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        button:hover {
            background-color: #d32f2f;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Account Not Deleted</h1>
        <p> Account having Amount.First Withdraw Amount.</p>
       
        <button onclick="window.history.back()">Go Back</button>
    </div>
</body>
</html>
