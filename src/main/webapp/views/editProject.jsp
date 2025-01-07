<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Edit Project Status</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
    <style>
        body {
            padding-top: 56px;
            background-color: rgb(13,110,253);
            font-family: Arial, sans-serif;
        }
        .container {
            margin-top: 50px;
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
        .form-label {
            font-weight: bold;
        }
        .form-control {
            border-radius: 6px;
        }
        .btn-primary {
            background-color: #0d6efd;
            border-color: #0d6efd;
            padding: 10px 20px;
            font-size: 16px;
            font-weight: bold;
            transition: background-color 0.3s ease, box-shadow 0.3s ease;
        }
        .btn-primary:hover {
            background-color: #0056b3;
            box-shadow: 0px 5px 15px rgba(0, 0, 0, 0.2);
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Edit Project Status</h2>
    <form:form action="${pageContext.request.contextPath}/projects/confirmEdit" method="post" modelAttribute="proj">
        <input type="hidden" name="id" value="${proj.id}" />

        <div class="mb-3">
            <label for="titre" class="form-label">Project Title:</label>
            <input type="text" id="titre" name="titre" class="form-control" value="${proj.titre}" readonly />
            
        </div>

        <div class="mb-3">
            <label for="currentStatus" class="form-label">Current Status:</label>
            <input type="text" id="currentStatus" class="form-control" value="${proj.statut}" readonly />
        </div>

        <div class="mb-3">
            <label for="statut" class="form-label">New Status:</label>
            <select id="statut" name="statut" class="form-control">
                <option value="Open" ${proj.statut == 'Open' ? 'selected' : ''}>Open</option>
                <option value="Close" ${proj.statut == 'Close' ? 'selected' : ''}>Close</option>
            </select>
        </div>

        <button type="submit" class="btn btn-primary w-100">Update Status</button>
    </form:form>
</div>
</body>
</html>
