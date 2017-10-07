<%-- 
    Document   : register
    Created on : 02/10/2017, 7:02:35 PM
    Author     : Madeleine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <h2>Please Register with your details below:</h2>
        <form method="post" action="main.jsp">
            <table>
                <tr><td>Full Name:</td><td><input type="text" name="name"></td></tr>
                <tr><td>Email:</td><td><input type="text" name="email"></td></tr>
                <tr><td>Password:</td><td><input type="password" name="password"></td></tr>
                <tr><td>DOB:</td><td><input type = "date" name="dob"></td></tr>
                <tr><td>User Type:</td><td>
                        <select>
                            <option value="Student">Student</option>
                            <option value="Tutor">Tutor</option>
                        </select>
                    </td></tr>
                <tr><td></td><td><input type="submit" value="Register"></td></tr>
            </table>            
        </form>
    </body>
</html>
