<%-- 
    Document   : Cart
    Created on : May 3, 2024, 11:53:14 PM
    Author     : dalyk
--%>

<%@ page import="java.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.Base64" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <style><%@include file="/WEB-INF/style.css"%></style>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Golden Deals cart</title>
    </head>
    <body>
        
       <!-- NavBar -->
        <nav class="navbar navbar-expand-lg bg-body-tertiary">
            <div class="container-fluid">
                <a href="<%=request.getContextPath()%>/mainPage" class="navbar-brand">
                    <img src="<%=request.getContextPath() + "/Images/GoldenDeals.png"%>" alt="GOLDEN DEALS" height="60">
                </a>

              <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
              </button>
              <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                  <li class="nav-item">
                    <a class="nav-link active" href="<%=request.getContextPath()%>/mainPage/viewCart">Panier</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link active" href="<%=request.getContextPath()%>/mainPage/viewProfile?IdUser=<%= request.getAttribute("IdUser") %>">Mon Compte</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link active" href="<%=request.getContextPath()%>/mainPage/history?IdUser=<%= request.getAttribute("IdUser") %>">Historique</a>
                  </li>
                </ul>
                  <div class="d-flex justify-content-between">
                    <form action="<%= request.getContextPath() %>/mainPage/searchProducts" class="d-flex" method="get">
                        <input class="form-control me-2" type="search" name="productName" placeholder="Search" aria-label="Search">
                        <button class="btn btn-outline-dark" type="submit">Search</button>
                    </form>
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <c:if test="${request.getAttribute('IdUser') == 0}">
                            <li class="nav-item">
                                <a class="nav-link" href="${pageContext.request.contextPath}/mainPage/signup">Signup</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="${pageContext.request.contextPath}/mainPage/login">Login</a>
                            </li>
                        </c:if>
                        <c:if test="${request.getAttribute('IdUser') != 0}">
                            <li class="nav-item">
                                <a class="nav-link" href="${pageContext.request.contextPath}/mainPage/logout">Logout</a>
                            </li>
                        </c:if>

                    </ul>
                  </div>
              </div>
            </div>
          </nav>
        
        
        <table class="table">
            <tr>
                <th>Image Produit</th>
                <th>Nom</th>
                <th>Description</th>
                <th>Quantité Commandé</th>
                <th>Prix</th>
                <th>Actions</th>
                            
            </tr>
            <c:forEach var="order" items="${orders}">  
                <tr>
                    <td><img src="<%=request.getContextPath() + "/Images/"%><c:out value="${order.getName()}"/>.jpg" width='100' height='100'"></td>
                    <td><c:out value="${order.getName()}" /></td>
                    <td><c:out value="${order.getDescription()}" /></td>
                    <td><c:out value="${order.getQteOrder()}" /></td>
                    <td><c:out value="${order.getPrice()}" /></td>
                    <td>
                        <a href="<%= request.getContextPath() %>/mainPage/confirmOrder?IdCommande=<c:out value="${order.getId()}"/>" class="btn btn-success">Confirmer</a>
                        <a href="<%= request.getContextPath() %>/mainPage/deleteOrder?IdCommande=<c:out value="${order.getId()}"/>" class="btn btn-danger">Supprimer</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
