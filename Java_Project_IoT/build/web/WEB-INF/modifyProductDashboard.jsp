<%-- 
    Document   : modifyProductDashboard
    Created on : May 2, 2024, 1:45:42 AM
    Author     : dalyk
--%>

<%@page import="java.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <style><%@include file="/WEB-INF/style.css"%></style>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>ModProduct</title>
    </head>
    <body>
    <center>
        <div class="container-fluid row h-100 p-4">
            <h1 class="d-flex justify-content-center">Modifier ce produit</h1>
            <div class=" d-flex py-5 justify-content-center">
                <form action="<%= request.getContextPath() %>/productListDashboard/update" method="get">
                    <c:if test="${1==1}">
                        <input type="hidden" name="Id" value="<c:out value='${existingProduct.getId()}' />" />
                    </c:if>
                    <label>   Nom : </label><br>
                    <input type="text" name="productName" required><br>
                    <label>   Description : </label><br>
                    <input type="text" name="productDesc" required><br>
                    <label>   Quantité : </label><br>
                    <input type="text" name="productQte" required><br>
                    <label>   Prix : </label><br>
                    <input type="text" name="productPrice" required><br>
                    <label>   Catégorie : </label><br>
                    <select class="btn btn-secondary" id="productCategorie" name="productCategorie">
                                <option value="Accessoires">Accessoires</option>
                                <option value="Fournitures_Scolaires">Fournitures Scolaires</option>
                                <option value="Vehicule">Véhicule</option>
                                <option value="Produits_Cosmetiques">Produits Cosmétiques</option>
                                <option value="Vetements">Vêtements</option>
                                <option value="Tech">Tech</option>
                                <option value="Electricite">Eléctricité</option>
                                <option value="Electro_menager">Electro-ménager</option>
                                <option value="Meuble">Meuble</option>
                                <option value="Art_Culinaire">Art Culinaire</option>
                                <option value="Jardinage">Jardinage</option>
                            </select><br>
                    <p style="background-color: red"><% if(request.getAttribute("message") == null)out.print(""); 
                    else out.print("password doesn't match");%></p>

                    <input class="btn btn-secondary" type="submit" value="Confirm change"/>
                </form>
            </div>
        </div>
    </center>
    </body>
</html>
