<%-- 
    Document   : account
    Created on : 09/10/2017, 7:42:05 PM
    Author     : Skye
--%>

<%@page import="uts.wsd.soap.client.BookingApp"%>
<%@page import="uts.wsd.soap.client.BookingSOAP"%>
<%@page import="javax.xml.ws.handler.MessageContext"%>
<%@page import="uts.wsd.BookingApplication"%>
<%@page import="uts.wsd.User"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account</title>
    </head>

    <%
        BookingSOAP bookingApp = new BookingApp().getBookingSOAPPort();
    %>
    <body>
        <%
            User user = (User) session.getAttribute("user");
            if (request.getParameter("name") != null) {
                String name = request.getParameter("name");
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                String dateOfBirth = request.getParameter("dateOfBirth");
                String userType = request.getParameter("userType");
                user.setName(name);
                user.setEmail(email);
                user.setPassword(password);
                user.setDateOfBirth(dateOfBirth);


        %>
     
        <%            }
        %>


        <h1>Current Details</h1>
        <p>Your name: <%= user.getName()%></p>
        <p>Your Email: <%= user.getEmail()%></p> 
        <p>Your date of birth: <%= user.getDateOfBirth()%></p>
        <p>You are a: <%= user.getUserType()%></p>


        <p><a href="accountEdit.jsp">Edit your details</a>    ||    <a href="cancelAccount.jsp"> Cancel account </a></p>

        <p>Return to the <a href="index.html">main page</a>.</p>
    </body>
</body>
</html>
