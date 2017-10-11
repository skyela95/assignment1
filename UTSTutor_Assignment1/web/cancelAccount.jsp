<%-- 
    Document   : cancelAccount
    Created on : 10/10/2017, 7:37:12 PM
    Author     : Adam
--%>

<%@page import="uts.wsd.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<link rel="stylesheet" type="text/css" href="CSS\style.css">
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
        if (user == null) {
            response.sendRedirect("login.jsp");
            bookingApp.logout();
            return;
        }
        
        String errors = (String) session.getAttribute("cancelAccountError");

        if (errors != null) {
            if (!errors.equals("")) {
                out.print(errors);
            }
        } 
        session.setAttribute("cancelAccountError", null);        
    %>

    <body>
        <h1>Authorize your details to confirm 'Account Cancellation' </h1>
        <form action="cancelAccountAction.jsp" method="POST">
            <input type="hidden" value="submitEdit" name="submitted"> 
            <table>
                <tr>
                    <td>Username:</td>
                    <td><input type="string" placeholder="Username" name="username"></td>
                </tr>
                    <td>Password:</td>
                    <td><input type="password" placeholder="Password" name="password"</td>
                </tr>
            </table>

            <tr><td></td><td><input type="submit" value="Cancel"></td></tr>
        </form>
    </body>
</html>
