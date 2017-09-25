<%-- 
    Document   : Register
    Created on : 25/09/2017, 2:44:37 PM
    Author     : Skye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <h1>Register</h1>
        <table> 
            <form action="main.jsp" method="post">
                <tr>
                    <td>Full name</td>
                    <td>
                        <input type="text" name="name">
                    </td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td>
                        <input type="text" name="email">                    
                    </td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" name="password"></td>
                </tr>
                <tr>
                    <td>DoB (yyyy-mm-dd)</td>
                    <td><input type="date" name="dob" max="2017-01-01" min="1900-12-01"></td>
                </tr>
                <tr>
                    <td>Student/Tutor</td>
                    <td>
                        <select name="SoT">
                            <option value="student">Student</option>
                            <option value="tutor">Tutor</option>
                        </select>
                    </td>

                </tr>
                <tr>
                    <td>
                        <a href="main.jsp">
                            <input name="" value="Register"type="submit">
                        </a>
                    </td>
                </tr>

        </table>

    </body>
</html>
