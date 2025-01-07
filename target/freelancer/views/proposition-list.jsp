<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<sec:authentication property="principal" var="user" />

<!DOCTYPE html>
<html lang="en">
<head>
    <spring:url value="src/bootstrap" var="bts"></spring:url>
    <link rel="stylesheet" href="${bts}/css/bootstrap.min.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Propositions List</title>
    <style>
        body {
            background-color: rgb(13,110,253);
            font-family: Arial, sans-serif;
            color: #333;
            padding: 20px;
        }

        .container {
            margin-top: 30px;
            padding: 20px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
        }

        h2 {
            color: #0d6efd;
            font-weight: bold;
            margin-bottom: 20px;
            text-align: center;
        }

        .table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }

        .table th, .table td {
            padding: 15px;
            text-align: left;
            border: 1px solid #ddd;
        }

        .table th {
            background-color: #f8f9fa;
            color: #333;
            font-weight: bold;
        }

        .table tr:nth-child(odd) {
            background-color: rgba(0, 0, 0, 0.05);
        }

        .table tr:nth-child(even) {
            background-color: rgba(255, 255, 255, 0.9);
        }
    </style>
</head>
<body>
<div class="container">
    <h2>List of Propositions</h2>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Project ID</th>
                <th>Freelancer Name</th>
                <th>Amount</th>
                <th>Duration</th>
                <th>Description</th>
               
            </tr>
        </thead>
        <tbody>
            <c:forEach var="prop" items="${propositions}">
                <tr>
                    <td>${prop.proj.id}</td>
                    <td>${prop.freelancer.nom}</td>
                    <td>${prop.montant}</td>
                    <td>${prop.duree}</td>
                    <td>${prop.description}</td>
                    
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<c:if test="${not empty unauthorizedError}">
    <script>
        window.onload = function() {
            alert("${unauthorizedError}");
        };
    </script>
</c:if>
</body>
</html>
