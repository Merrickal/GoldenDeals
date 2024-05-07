<%-- 
    Document   : addProductDashboard
    Created on : Apr 27, 2024, 11:50:18 AM
    Author     : dalyk
--%>

<%@ page import="java.sql.*" %>
<%@page import="java.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <style><%@include file="/WEB-INF/style.css"%></style>
    </head>
    <body style="overflow: hidden">
        <!--Navbar-->
        <nav class="navbar navbar-expand-lg bg-body-tertiary     p-0" style="z-index: 600">
            <div class="container-fluid" style="background-color:rgb(0,71,171);height:70px" >
                <a class="navbar-brand" href="#" style="font-size: 30px" onclick="redirectToTarget()">ClickUp</a> 
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
                                Dashboard
                            </a>
                            <a href="productListDashboard" class="list-group-item list-group-item-action py-2 text-white ripple active" 
                               aria-current="true" style="background-color:rgb(8,38,64)">
                                Products
                            </a>
                            <a href="usersListDashboard" class="list-group-item list-group-item-action py-2 text-white ripple" 
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
                <form action="insert" method="post">
                    <table class="table">
                        <tr><input type="text" placeholder="Nom " name="Nom"></tr><br>
                        <tr><input type="text" placeholder="Prenom" name="Prenom"></tr><br>
                        <tr><input type="text" placeholder="Email" name="Email"></tr><br>
                        <tr><input type="text" placeholder="Password" name="Password"></tr><br>
                        <tr><input type="submit" class="btn btn-secondary" name="submit" value="Ajouter Utilisateur"></tr><br>
                    </table>
                </form>
            </div>          
        </div>