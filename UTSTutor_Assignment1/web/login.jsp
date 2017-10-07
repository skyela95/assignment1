<%-- 
    Document   : login
    Created on : 02/10/2017, 8:12:06 PM
    Author     : Madeleine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Login with your details below: </h1>
         <form method="post" action="main.jsp">
            <table>
                <tr><td>Username:</td><td><input type="text" name="name"></td></tr>
                <tr><td>Password:</td><td><input type="password" name="password"></td></tr>
                <tr><td></td><td><input type="submit" value="Login"></td></tr>
            </table>            
        </form>
    </body>
</html>
