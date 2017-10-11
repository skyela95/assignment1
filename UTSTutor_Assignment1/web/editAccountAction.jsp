<%-- 
    Document   : editAccountAction
    Created on : 10/10/2017, 5:17:09 PM
    Author     : Adam
--%>

<%@page import="uts.wsd.RegexHelper"%>
<%@page import="uts.wsd.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        if (user == null || bookingApp.getLoggedUser() == null) {
            response.sendRedirect("login.jsp");
            bookingApp.logout();
            return;
        }
        String errors = "";
        String submit = request.getParameter("submitted");

        if (submit == null || !submit.equals("submitEdit")) {
            errors += "Error processing request";
            session.setAttribute("editAccountError", errors);
            response.sendRedirect("account.jsp");
            return;
        }

        String newName = request.getParameter("newname").trim();
        String newPassword = request.getParameter("newpassword").trim();
        String newDOB = request.getParameter("newdateOfBirth").trim();

        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();

        if (newName.equals(user.getName())
                && newPassword.equals(user.getPassword())
                && newDOB.equals(user.getDateOfBirth())) {
            errors += "Details Unchanged\n";
        } else {

            if (!bookingApp.authorizeUser(username, password)) {
                errors += "Unable to authorize user";
            } else {
                if (!RegexHelper.TestDOB(newDOB)) {
                    errors += "Date of Birth is not in a valid format [01/01/1990]\n";
                }
                System.out.println("Before test: " + newName);
                if (!RegexHelper.TestName(newName)) {
                    errors += "Name is not in a valid format [John Smith]\n";
                }

            }
        }

        if (errors.equals("")) {
            bookingApp.editName(newName);
            bookingApp.editDOB(newDOB);
            bookingApp.editPassword(newPassword);
            //session.setAttribute("user", bookingApp.getLoggedUser());
            session.setAttribute("editAccountSuccess", "Details changed successfully");
        } else {
            session.setAttribute("editAccountError", errors);
        }
        response.sendRedirect("accountEdit.jsp");
    %>
    <body>

    </body>
</html>
