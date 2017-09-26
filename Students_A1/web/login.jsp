<%-- 
    Document   : login
    Created on : 18/08/2017, 8:28:17 PM
    Author     : Skye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login</h1>
         
            <form method="post" action="loginAction.jsp">
                <table>
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
                    <td>
                       
                        <input name="" value="Login"type="submit"></td>
                </tr>
                </table>
            </form>
        
    </body>
</html>
