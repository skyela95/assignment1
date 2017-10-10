<%-- 
    Document   : cancelAccountAction
    Created on : 10/10/2017, 7:40:32 PM
    Author     : Adam
--%>

<%@page import="uts.wsd.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cancel Account</title>
    </head>

    <% String filePath = application.getRealPath("\\");%>
    <jsp:useBean id ="bookingApp" class="uts.wsd.BookingApplication" scope="application">
        <jsp:setProperty name="bookingApp" property="filePath" value="<%=filePath%>"/>
    </jsp:useBean>

    <%
        User user = (User) session.getAttribute("user");
        if (user == null || bookingApp.getLoggedUser() == null) {
            response.sendRedirect("login.jsp");
            bookingApp.logout();
            return;
        }

        String errors = "";
        String submit = request.getParameter("submitted");

        if (submit == null || !submit.equals("submitEdit")) {
            errors += "Error processing request";
            response.sendRedirect("cancelAccount.jsp");
            return;
        }

        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();

        if (!bookingApp.authorizeUser(username, password)) {
            errors += "Unable to authorize user";
        }

        if (errors.equals("")) {
            session.setAttribute("user", null);
            bookingApp.cancelAccount();
            response.sendRedirect("login.jsp");
            return;
        } else {
            session.setAttribute("cancelAccountError", errors);
        }
        response.sendRedirect("cancelAccount.jsp");


    %>
    <body>
    </body>
</html>
