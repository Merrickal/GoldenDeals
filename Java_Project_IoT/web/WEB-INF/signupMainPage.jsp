<%-- 
    Document   : signupMainPage
    Created on : May 4, 2024, 7:41:22 PM
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
    </head>
    <body>
    <center>
        <div class="container-fluid row h-100 p-4">
            <h1 class="d-flex justify-content-center">Inscrivez Vous</h1>
            <div class=" d-flex py-5 justify-content-center">
                <form action="<%= request.getContextPath() %>/mainPage/signup" method="get">
                    <label>   Nom : </label><br>
                    <input type="text" name="Nom" required><br>
                    <label>   Prenom : </label><br>
                    <input type="text" name="Prenom" required><br>
                    <label>   Email : </label><br>
                    <input type="text" name="Email" required><br>
                    <label>   Password : </label><br>
                    <input type="text" name="Password" required><br>
                    <label>   Confirm password : </label><br>
                    <input type="text" name="Confirm_Password" required><br>
                    <p style="background-color: red"><% if(request.getAttribute("message") == null)out.print(""); 
            else out.print("password doesn't match");%></p>

                    <input class="btn btn-secondary my-2 px-4" type="submit" value="Signup"/>
                    <a class="nav-link" href="<%= request.getContextPath() %>/mainPage/loginWhenEmpty">Login</a>
                </form>
            </div>
    </center>
</body>
</html>