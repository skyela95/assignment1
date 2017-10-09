<%-- 
    Document   : loginAction
    Created on : 08/10/2017, 4:11:26 PM
    Author     : Adam
--%>

<%@page import="uts.wsd.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Attempting to login...</title>
    </head>

    <% String filePath = application.getRealPath("\\");%>
    <jsp:useBean id ="bookingApp" class="uts.wsd.BookingApplication" scope="application">
        <jsp:setProperty name="bookingApp" property="filePath" value="<%=filePath%>"/>
    </jsp:useBean>


    <%
        String username = request.getParameter("name");
        String password = request.getParameter("password");

        User user = bookingApp.login(username, password);
        System.out.println("Login Action User: " + user == null);

        if (user != null) {
            session.setAttribute("user", user);;
            session.setAttribute("loginFailed", false);
            response.sendRedirect("main.jsp"); 
        } else {
            session.setAttribute("loginFailed", true);
            response.sendRedirect("login.jsp");

        }
    %>
    <body>

    </body>
</html>
