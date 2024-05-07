<%-- 
    Document   : usersListDashboard
    Created on : Apr 25, 2024, 8:15:50 AM
    Author     : dalyk
--%>
<%@page import="java.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <style><%@include file="/WEB-INF/style.css"%></style>
        <meta name="viewport" content="width=device-width, initial-scale=1">
    </head>
    <body style="overflow-x: hidden">
        <!--Navbar-->
        <nav class="navbar navbar-expand-lg bg-body-tertiary p-0" style="z-index: 600">
            <div class="container-fluid" style="background-color:rgb(0,71,171);height:70px" >
                <a class="navbar-brand" href="<%= request.getContextPath() %>/mainPage" style="font-size: 30px">GoldenDeals</a> 
            </div>
        </nav>
        <div class="row d-flex h-100">
            <!-- Sidebar -->
            <div class="col-3 d-flex">
                <nav id="sidebarMenu" class="collapse d-lg-block sidebar collapse h-100" style="background-color:rgb(8,38,64)" >
                    <div class="position-sticky" style="background-color:rgb(8,38,64)">
                        <div class="list-group list-group-flush mx-3 mt-4" style="background-color:rgb(8,38,64)">
                            <a href="LoginServlet" class="list-group-item list-group-item-action py-2 text-white ripple" 
                               aria-current="true" style="background-color:rgb(8,38,64)">
                            </a>
                            <a href="productListDashboard" class="list-group-item list-group-item-action py-2 text-white ripple" 
                               aria-current="true" style="background-color:rgb(8,38,64)">
                                Products
                            </a>
                            <a href="usersListDashboard" class="list-group-item list-group-item-action py-2 text-white ripple active" 
                               style="background-color:rgb(8,38,64)">
                              Customers
                            </a>
                            <a href="usersListDashboard" class="list-group-item list-group-item-action py-2 text-white ripple" 
                               style="background-color:rgb(8,38,64)">
                            </a>
                        </div>
                    </div>
                </nav>
            </div>
            <div class="container-fluid d-flex pt-2 col-xl-8 flex-column">
                <table class="table " style="width: 100%">
                    <tr>
                        <a href="usersListDashboard/new" type="button" class="btn btn-success">Ajouter Utilisateur</a>
                    </tr>
                    <tr>
                        <th>ID</th>
                        <th>Nom</th>
                        <th>Prenom</th>
                        <th>Email</th>
                        <th>Password</th>
                        <th>Actions</th>
                    </tr>
                       
                    <c:forEach var="user" items="${listUsers}" >
                    <tr>
                        <td><c:out value="${user.getId()}" /></td>
                        <td><c:out value="${user.getNom()}" /></td>
                        <td><c:out value="${user.getPrenom()}" /></td>
                        <td><c:out value="${user.getEmail()}" /></td>
                        <td><c:out value="${user.getPassword()}" /></td>
                        <td>
                            <a href="usersListDashboard/edit?Id=<c:out value="${user.getId()}"/>" class="btn btn-secondary">Modifier</a>
                            <a href="usersListDashboard/delete?Id=<c:out value="${user.getId()}"/>" class="btn btn-danger">Supprimer</a>
                            
                        </td>
                    </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </body>
</html>
