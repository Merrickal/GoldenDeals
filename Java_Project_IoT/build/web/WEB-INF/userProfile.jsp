<%-- 
    Document   : userProfile
    Created on : May 4, 2024, 5:07:27 AM
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
        <title>Golden Deals Profile Info</title>
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

         <div class="container-fluid d-flex justify-content-center py-5">
             <div class="d-flex justify-content-center col-5">
                 <div class="mx-3" style="max-width: 50vw;">
                     <h3>Nom :  <%= request.getAttribute("Nom") %></h3>
                     <h3>Prenom :  <%= request.getAttribute("Prenom") %></h3>
                     <h4 class="align-self-center">Email :  <%= request.getAttribute("Email") %></h4>
                     <hr class="my-4">
                     <h2>Mot de passe :  <%= request.getAttribute("Password") %></h2>
                     <div class="row flex-column justify-content-center">
                         <c:if test="${1==1}">
                             <input type="hidden" name="IdUser" value="<%= request.getAttribute("IdUser") %>" />
                             <input type="hidden" name="IdProduct" value="<%= request.getAttribute("IdProduct") %>" />
                             <input type="hidden" name="productName" value="<%= request.getAttribute("productName") %>" />
                             <input type="hidden" name="productDesc" value="<%= request.getAttribute("productDesc") %>" />
                             <input type="hidden" name="productPrice" value="<%= request.getAttribute("productPrice") %>" />
                         </c:if>

                         <a class="btn" href="<%= request.getContextPath() %>/usersListDashboard/edit?Id=<%= request.getAttribute("IdUser") %>" style="background-color: rgb(8, 172, 10); color: white;">Modifier Compte</a>

                     </div>
                 </div>
             </div>
         </div>
    </body>
</html>
