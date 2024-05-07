<%-- 
    Document   : mainPage
    Created on : May 3, 2024, 7:58:35 PM
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
        <title>Golden Deals Main Page</title>
        <script>
            function updateValue(value) {
              document.getElementById("rangeValue").textContent = value;
            }
         </script>
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
        <div class="container-fluid d-flex flex-column">
            <center>
                <div class="">
                    <form class="d-flex flex-column" action="<%= request.getContextPath() %>/mainPage/filterProducts" method="get">
                        <div class="d-flex justify-content-center">
                            <h3 class="m-1 ">Price range bigger than</h3>
                            <h3  class="m-1" id="rangeValue"><% if(request.getAttribute("productPriceRange")==null) out.println("5000");else out.println(request.getAttribute("productPriceRange"));%></h3>
                            <h3 class="m-1" >DT</h3>
                        </div>
                            <div class="d-flex">
                                <input class="flex-grow-1 mx-3" type="range" name="productPriceRange" value="<%= request.getAttribute("productPriceRange")%>" min="0" max="10000" oninput="updateValue(this.value)"/>
                                <input type="checkbox" id="ProductPricecheck" name="ProductPricecheck" value="ProductPricechecked" <% if(request.getAttribute("ProductPricecheck")!=null) out.println("checked");
                        else out.println("");%>/>
                            </div>
                            <div class="d-flex">
                                <select class="btn btn-secondary m-3 flex-grow-1" id="productCategorie" name="productCategorie">
                                    <option value="Accessoires">Accessoires</option>
                                    <option value="Fournitures_Scolaires">Fournitures Scolaires</option>
                                    <option value="Vehicule">Véhicule</option>
                                    <option value="Produits_Cosmetiques">Produits Cosmétiques</option>
                                    <option value="Vetements">Vêtements</option>
                                    <option value="Tech">Tech</option>
                                    <option value="Electricite">Eléctricité</option>
                                    <option value="Electro_menager">Electro ménager</option>
                                    <option value="Meuble">Meuble</option>
                                    <option value="Art_Culinaire">Art Culinaire</option>
                                    <option value="Jardinage">Jardinage</option>
                                </select>
                                <input type="checkbox" id="ProductCategoriecheck" name="ProductCategoriecheck" value="ProductCategoriechecked" <% if(request.getAttribute("ProductCategoriecheck")!=null) out.println("checked");
                        else out.println("");%>/>
                            </div>
                            <input class="btn btn-secondary mx-3" type="submit"/>
                        
                    </form>
                    <script>
                        
                        const rangeInput = document.getElementById("myRange");
                        const hiddenInput = document.getElementById("hiddenValue");
                        rangeInput.addEventListener("change", function() {
                          hiddenInput.value = this.value;
                        });
                    </script>
                </div>
            </center>
            <input type="hidden" name="IdUser" value="<%= request.getAttribute("IdUser") %>" />
            <div class="d-flex row justify-content-center">
                <c:forEach var="product" items="${listProducts}">
                    <div class="card my-1" style="width: 18rem;">
                        <center>
                            <img src="<%=request.getContextPath() + "/Images/"%><c:out value="${product.getName()}"/>.jpg" width='200' height='200'">
                        </center>
                        <div class="card-body">
                          <h5 class="card-title"><c:out value="${product.getName()}" /></h5>
                          <p class="card-text"><c:out value="${product.getDescription()}"/></p>
                          <a href="<%=request.getContextPath()%>/mainPage/viewProduct?IdProduct=<c:out value="${product.getId()}"/>" class="btn btn-secondary">Preview</a>
                        </div>
                      </div>               
                </c:forEach>
            </div>
        </div>                      
    </body>
</html>
