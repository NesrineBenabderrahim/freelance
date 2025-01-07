<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>
<html lang="fr">
<head>
    <spring:url value="/src/bootstrap" var="bts"></spring:url>
    <link rel="stylesheet" href="${bts}/css/bootstrap.min.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Freelance Projects</title>
    <style>
        body {
            padding-top: 56px;
        }
        .card {
            margin-bottom: 20px;
            border: none;
            border-radius: 12px;
            overflow: hidden;
            transition: transform 0.3s, box-shadow 0.3s;
        }
        .card:hover {
            transform: scale(1.05);
            box-shadow: 0 6px 15px rgba(0, 0, 0, 0.2);
        }
        .card-header {
            color: white;
            padding: 15px;
            font-size: 1.2em;
            text-align: center;
            font-weight: bold;
        }
        .card-header:nth-child(even) {
            background-color: #007bff;
        }
        .card-header:nth-child(odd) {
            background-color: #28a745;
        }
        .card-body {
            padding: 20px;
            font-size: 0.9em;
            background-color: #f9f9f9;
        }
        .card-footer {
            background-color: #f1f1f1;
            padding: 10px;
            text-align: right;
        }
        .btn-primary {
            border-radius: 20px;
        }
        .p22 {
            color: white;
        }
    </style>
    
</head>
<body>
<jsp:include page="header.jsp" />
<sec:authentication property="principal" var="u" />
<section id="projects" class="py-5">
    <center><p class="p22">Connect with talented freelancers and find the perfect project.</p></center>

    <div class="container">
        <h2 class="text-center mb-4">List of Projects</h2>
        <div class="row">
            <c:forEach items="${projs}" var="p">
                <div class="col-md-4">
                    <div class="card">
                        <div class="card-header">
                            ${p.titre}
                        </div>
                        <div class="card-body">
                            <p><strong>Description:</strong> ${p.description}</p>
                            <p><strong>Budget:</strong> ${p.budget} €</p>
                            <p><strong>Deadline:</strong> ${p.dateLimite}</p>
                            <p><strong>Hours:</strong> ${p.nbreHeures}</p>
                            <p><strong>Technologies:</strong> ${p.technologies}</p>
                            <p>
                                <strong>Status:</strong>
                                <span id="status-${p.id}">${p.statut}</span>
                            </p>
                            <p><strong>Client:</strong> ${p.client.nom}</p>
                        </div>
                        <div class="card-footer">
                            <c:if test="${p.client.nom == u.nom}">
    						<a href="projects/editStatusForm/${p.id}" class="btn btn-dark">Edit Status</a>
                            </c:if>
                            <c:if test="${p.statut == 'Open'}">
                                <a href="addPropositionForm/${p.id}" class="btn btn-success">Add Proposal</a>
                            </c:if>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</section>
</body>
</html>
