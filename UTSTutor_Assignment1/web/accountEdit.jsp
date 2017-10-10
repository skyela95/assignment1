<%-- 
    Document   : accountEdit
    Created on : 10/10/2017, 5:16:50 PM
    Author     : Adam
--%>

<%@page import="uts.wsd.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<link rel="stylesheet" type="text/css" href="CSS\style.css">
<!DOCTYPE html>
<html>    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Account</title>
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
                <h1>Change your account details here: </h1>
            </div>
            <form action="editAccountAction.jsp" method="POST">
                <input type="hidden" value="submitEdit" name="submitted"> 
                <div class="detailsContent">
                    <tr><td><table>
                                <tr>
                                    <td>Full name</td>
                                    <td><input type="string" value="<%= user.getName()%>" name="newname"></td>
                                </tr>
                                <tr>
                                    <td>Password</td>
                                    <td><input type="password" value="<%= user.getPassword()%>" name="newpassword"></td>
                                </tr>
                                <tr>
                                    <td>Date of Birth</td>
                                    <td><input type="string" value="<%= user.getDateOfBirth()%>" name="newdateOfBirth"></td>
                                </tr>               

                            </table></td></tr>

                    <tr><td><table>
                                <h2> Authentication </h2>
                                <tr>
                                    <td>Username:</td>
                                    <td><input type="string" placeholder="Username" name="username"></td>
                                </tr>
                                <tr>
                                    <td>Password:</td>
                                    <td><input type="password" placeholder="Password" name="password"></td>
                                </tr>

                            </table></td></tr>
                </div>
                <tr><td><input type="submit" class="btn" value="Save"></td></tr>
            </form>
            <div class="errorpage">
                <%
                    String errors = (String) session.getAttribute("editAccountError");
                    String success = (String) session.getAttribute("editAccountSuccess");

                    if (errors != null) {
                        if (!errors.equals("")) {
                            out.print(errors);

                        }
                    } else if (success != null) {
                        if (!success.equals("")) {
                            out.print(success);

                        }
                    }
                    session.setAttribute("editAccountError", null);
                    session.setAttribute("editAccountSuccess", null);
                %>
            </div>
        </table>

    </body>
</html>
