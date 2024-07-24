<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Failure</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        h1 {
            color: #ff0000;
        }
        p {
            font-size: 18px;
            color: #333;
        }
        .button {
            margin-top: 20px;
        }
        .button a {
            text-decoration: none;
            color: white;
            background-color: #ff0000;
            padding: 10px 20px;
            border-radius: 5px;
            transition: background-color 0.3s;
        }
        .button a:hover {
            background-color: #cc0000;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>${param.message}</h1>
        <p>There was an error processing your deposit. Please try again.</p>
        <div class="button">
            <a href="customerdashboard.jsp">Go to Dashboard</a>
        </div>
    </div>
</body>
</html>
