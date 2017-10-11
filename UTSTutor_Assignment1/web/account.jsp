<%-- 
The main account page for the user - Can proceed to edit and canel
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
            if (user == null || bookingApp.getLoggedUser() == null) {
                response.sendRedirect("login.jsp");
            } else {
        %>

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


        <table align="center" margin-top="15px"><tr><td>
                    <div class="detailsTitle">
                        <h1><u>Current Details</u></h1>
                    </div>

                    <div class="detailsContent">
                        <table>
                            <tr>
                                <td>Name: </td><td><%= user.getName()%></td>
                            </tr>
                            <tr>
                                <td>Email: </td><td><%= user.getEmail()%> </td>
                            </tr>
                            <tr>
                                <td>Date of Birth: </td><td><%= user.getDateOfBirth()%></td>
                            </tr>
                            <tr>
                                <td>You are a: </td><td><%= user.getUserType()%></td>
                            </tr>
                        </table>
                </td></tr><tr></tr>
            <tr><td><form action="accountEdit.jsp"><input type="submit" class="btn" value="Edit Details"></form></td></tr>
            <tr><td><form action="cancelAccount.jsp"><input type="submit" class="btn" value="CANCEL ACCOUNT"></form></td></tr>
        </table>
        <%
            }
        %>
    </body>
</body>
</html>
