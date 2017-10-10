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
    %>

    <body>

        <div class ="title">
            <h1>UTS Tutor</h1>
        </div>
        <div class="navigationBar">
            <ul>
                <li><a href="main.jsp">Main</a></li>
                <li><a class="active" href="account.jsp">Account</a></li>
                <li><a href="booking.jsp">Booking</a></li>
                <li style="float:right"><a href="logoutAction.jsp">Logout</a></li>
                <li style="float:right"><div class="user"> Logged in as: <%=user.getName()%> [<%=user.getUserType().value()%>]</div></li>
            </ul>
        </div>
        <table align="center">
            <div class="detailsTitle">
                <h1>Authorization required to confirm 'Account Cancellation' </h1>
            </div>
            <form action="cancelAccountAction.jsp" method="POST">
                <div class="detailsContent">
                    <input type="hidden" value="submitEdit" name="submitted"> 
                    <tr><td> <table>
                                <tr>
                                    <td>Username:</td>
                                    <td><input type="string" placeholder="Username" name="username"></td>
                                </tr>
                                <tr>
                                    <td>Password:</td>
                                    <td><input type="password" placeholder="Password" name="password"></td>
                                </tr>                

                            </table> </td></tr>
                    <tr><td><input type="submit" class="btn" value="Cancel Account"></td></tr>
                </div>

            </form>
            <div class ="errorpage">
                <%

                    String errors = (String) session.getAttribute("cancelAccountError");

                    if (errors != null) {
                        if (!errors.equals("")) {
                            out.print(errors);
                        }
                    }
                    session.setAttribute("cancelAccountError", null);
                %>
            </div>
        </table>
    </body>
</html>
