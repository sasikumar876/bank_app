<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: left;
        }
        th {
            background-color: #f4f4f4;
        }
    </style>
</head>
<body>
    <h2>Customer Details</h2>

    <table>
        <thead>
            <tr>
                <th>Account Number</th>
                <th>Name</th>
                <th>Email</th>
                <th>Date of Birth</th>
                <th>Address</th>
                <th>Phone</th>
                <th>Gender</th>
                <th>Account Type</th>
                <th>Join Date</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="customer" items="${customers}">
                <tr>
                    <td>${customer.accountNo}</td>
                    <td>${customer.name}</td>
                    <td>${customer.email}</td>
                    <td>${customer.dob}</td>
                    <td>${customer.address}</td>
                    <td>${customer.phone}</td>
                    <td>${customer.gender}</td>
                    <td>${customer.accountType}</td>
                    <td>${customer.joinDate}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</body>
</html>
