<%-- 
    Document   : main
    Created on : 25/09/2017, 3:06:03 PM
    Author     : Skye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
    <%
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String dob = request.getParameter("dob");
        String sot = request.getParameter("sot");
    %>
    
        <% if(sot == "student") { %>
    
        
        
        <%} else { %>
        
        
        <%} %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Main</title>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>
