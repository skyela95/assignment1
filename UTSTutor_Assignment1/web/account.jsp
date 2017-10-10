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

<link rel="stylesheet" type="text/css" href="CSS\style.css">
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
            if (user == null) {
                response.sendRedirect("login.jsp");
            } else {
        %>

        UTS Tutor
        <div class="navigationBar">
            <ul>
                <li><a href="main.jsp">Main</a></li>
                <li><a class="active" href="account.jsp">Account</a></li>
                <li style="float:right"><a href="logout.jsp">Logout</a></li>
            </ul>
        </div>


        <h1>Current Details</h1>
        <p>Your Name: <%= user.getName()%></p>
        <p>Your Email: <%= user.getEmail()%></p> 
        <p>Your Date of Birth: <%= user.getDateOfBirth()%></p>
        <p>You are a: <%= user.getUserType()%></p>

        <p><a href="accountEdit.jsp">Edit Details</a>


        <p><a href="cancelAccount.jsp"> CANCEL ACCOUNT </a>  </p>

        <%
            }
        %>
    </body>
</body>
</html>
