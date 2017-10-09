<%-- 
    Document   : account
    Created on : 09/10/2017, 7:42:05 PM
    Author     : Skye
--%>

<%@page import="uts.wsd.User"%>
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
                user.setName(name);
                user.setEmail(email);
                user.setPassword(password);
                user.setDateOfBirth(dateOfBirth);


        %>
        <p>Details updated.</p>
        <%            }
        %>


        <h1>My Account</h1>
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
                    <td>Password</td><td><input type="string" value="<%= user.getPassword()%>" name="password">
                    </td>
                </tr>
                <tr>
                    <td>Date of Birth</td>
                    <td>

                    </td>
                </tr>                
                <tr><td></td><td><input type="submit" value="Save"></td></tr>
            </table>

            <p>Return to the <a href="index.jsp">main page</a>.</p>

    </body>
</body>
</html>
