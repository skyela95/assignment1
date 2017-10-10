<%-- 
    Document   : account
    Created on : 09/10/2017, 7:42:05 PM
    Author     : Skye
--%>

<%@page import="com.sun.faces.facelets.util.Path.context(String)"%>
<%@page import="javax.xml.ws.handler.MessageContext"%>
<%@page import="uts.wsd.BookingApplication"%>
<%@page import="uts.wsd.User"%>
<%@page import="uts.wsd.soap.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account</title>
    </head>
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
        <p>Details updated.</p>
        <%            }
        %>


        <h1>Current Details</h1>
        <p>Your name: <%= user.getName()%></p>
        <p>Your Email: <%= user.getEmail()%></p> 
        <p>Your birthday: <%= user.getDateOfBirth()%></p>
        <p>You are a: <%= user.getUserType()%></p>
        
        
        
        <h1>Change your account details here: </h1>
        <form action="account.jsp" method="POST">

            <table>
                <tr>
                    <td>Email</td>
                    <td><input type="string" value="<%= user.getEmail()%>" name="email"></td>
                </tr>
                <tr>
                    <td>Full name</td>
                    <td><input type="string" value="<%= user.getName()%>" name="name"></td>
                </tr>
                <tr>
                    <td>Password</td><td><input type="password" value="<%= user.getPassword()%>" name="password">
                    </td>
                </tr>
                <tr>
                    <td>Date of Birth</td>
                    <td>
                        <input type="string" value="<%= user.getDateOfBirth()%>" name="dateOfBirth">
                    </td>
                </tr>                
                <tr><td></td><td><input type="submit" value="Save"></td></tr>
            </table>

            <p>Return to the <a href="index.html">main page</a>.</p>
            <%-- 
            <p>Cancel Account <%= BookingSOAP.cancelAccount()%></p>
            --%>

    </body>
</body>
</html>
