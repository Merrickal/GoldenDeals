<%-- 
    Document   : addProductDashboard
    Created on : Apr 27, 2024, 11:50:18 AM
    Author     : dalyk
--%>

<%@ page import="java.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <style><%@include file="/WEB-INF/style.css"%></style>
    </head>
    <body style="overflow: hidden">
        <center>
        <div class="container-fluid row h-100 p-4">
            <h1 class="d-flex justify-content-center">Ajouter un Produit</h1>
            <div class=" d-flex py-5 justify-content-center">
                <form action="insert" method="post">
                    <h4>   Nom : </h4>
                    <input class="my-2" type="text" placeholder="Nom Produit" name="productName" required><br>
                    <h4>   Description : </h4>
                    <input class="my-2" type="text" placeholder="Description Produit" name="productDesc"required><br>
                    <h4>   Quantité : </h4>
                    <input class="my-2" type="text" placeholder="Quantité Produit" name="productQte" required><br>
                    <h4>   Prix : </h4>
                    <input class="my-2 " type="text" placeholder="Prix Produit" name="productPrice" required><br>
                    <h4>   Catégorie : </h4>
                    <select class="btn btn-secondary my-2" id="productCategorie" name="productCategorie">
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
                            </select><br>             
                    <input type="submit" class="btn btn-secondary my-2" name="submit" value="Ajouter Produit">
                    <a class="nav-link" href="<%= request.getContextPath() %>/productListDashboard"><-Go Back To Dashboard</a>
                </form>
            </div>
        </div>
    </center>
</body>