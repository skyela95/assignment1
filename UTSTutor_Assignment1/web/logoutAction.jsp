<%-- 
    Handles logging out the user and clearing attributes
--%>

<%@page import="uts.wsd.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logging out...</title>
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
        } else {
            bookingApp.logout();
            session.setAttribute("user", null);
            session.setAttribute("editAccountError", null);
            session.setAttribute("editAccountSuccess", null);
            session.setAttribute("cancelAccountError", null);
            response.sendRedirect("login.jsp");
        }
    %>
    <body>       
    </body>
</html>
