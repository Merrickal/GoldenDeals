<%-- 
    Document   : usersListDashboard
    Created on : Apr 25, 2024, 8:15:50 AM
    Author     : dalyk
--%>
<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="java.io.ByteArrayInputStream"%>
<%@page import="java.awt.Image"%>
<%@page import="javax.swing.ImageIcon"%>
<%@ page import="java.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.Base64" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <style><%@include file="/WEB-INF/style.css"%></style>
    </head>
    <body style="overflow-x: hidden">
        <!--Navbar-->
        <nav class="navbar navbar-expand-lg bg-body-tertiary     p-0" style="z-index: 600">
            <div class="container-fluid" style="background-color:rgb(0,71,171);height:70px" >
                    <a class="navbar-brand" href="<%= request.getContextPath() %>/mainPage" style="font-size: 30px" onclick="redirectToTarget()">GoldenDeals</a> 
            </div>
        </nav>
        <div class="row d-flex h-100">
            <!-- Sidebar -->
            <div class="col-3 d-flex">
                <nav id="sidebarMenu" class="collapse d-lg-block sidebar collapse h-100" style="background-color:rgb(8,38,64)" >
                    <div class="position-sticky" style="background-color:rgb(8,38,64)">
                        <div class="list-group list-group-flush mx-3 mt-4" style="background-color:rgb(8,38,64)">
                            <a href="<%= request.getContextPath() %>/LoginServlet" class="list-group-item list-group-item-action py-2 text-white ripple" 
                               aria-current="true" style="background-color:rgb(8,38,64)">
                            </a>
                            <a href="<%= request.getContextPath() %>/productListDashboard" class="list-group-item list-group-item-action py-2 text-white ripple active" 
                               aria-current="true" style="background-color:rgb(8,38,64)">
                                Products
                            </a>
                            <a href="<%= request.getContextPath() %>/usersListDashboard" class="list-group-item list-group-item-action py-2 text-white ripple" 
                               style="background-color:rgb(8,38,64)">
                              Customers
                            </a>
                            <a href="<%= request.getContextPath() %>/usersListDashboard" class="list-group-item list-group-item-action py-2 text-white ripple" 
                               style="background-color:rgb(8,38,64)">
                            </a>
                        </div>
                    </div>
                </nav>
            </div>
            
            <!-- Main Table-->
            <div class="container-fluid d-flex pt-2 col-xl-8 flex-column">
                <table class="table " style="width: 100%">
                    <tr>
                            <a href="productListDashboard/new" type="button" class="btn btn-success">Ajouter Produit</a>
                        </tr>
                        <tr>
                            <th>Image Produit</th>
                            <th>ID</th>
                            <th>Nom</th>
                            <th>Description</th>
                            <th>Catégorie</th>
                            <th>Quantité</th>
                            <th>Prix</th>
                            <th>Actions</th>
                            
                        </tr>
                        <c:forEach var="product" items="${listProducts}">   
                            <tr>
                                <td><img src="<%=request.getContextPath() + "/Images/"%><c:out value="${product.getName()}"/>.jpg" width='100' height='100'"></td>
                                <td><c:out value="${product.getId()}" /></td>
                                <td><c:out value="${product.getName()}" /></td>
                                <td><c:out value="${product.getDescription()}" /></td>
                                <td><c:out value="${product.getproductCategorie()}" /></td>
                                <td><c:out value="${product.getQte()}" /></td>
                                <td><c:out value="${product.getPrice()}" /></td>
                                <td>
                                    <a href="productListDashboard/edit?Id=<c:out value="${product.getId()}"/>" class="btn btn-secondary">Modifier</a>
                                    <a href="productListDashboard/delete?Id=<c:out value="${product.getId()}"/>" class="btn btn-danger">Supprimer</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div> 
            </div>
        </div>
    </body>
</html>
