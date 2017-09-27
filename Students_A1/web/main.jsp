<%-- 
    Document   : main
    Created on : 25/09/2017, 3:06:03 PM
    Author     : Skye
--%>

<%@page import="uts.wsd.Student" contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="student" class="uts.wsd.Student" scope="session" />
<jsp:setProperty name="student" property="*" />

<!DOCTYPE html>
<%
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String dob = request.getParameter("dob");
    String usertype = request.getParameter("usertype");
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Main</title>
    </head>

    <% if (usertype == "student") {%>    
    <p>Welcome! <%=name%>.</p>

    <%} else {%>
    <p>Welcome! <%=name%>.</p>

    <%}%>
    <body>

    </body>
</html>
