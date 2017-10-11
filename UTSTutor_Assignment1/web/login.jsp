<%-- 
    The main login page - User and pass to authenticate user
--%>

<%@page import="uts.wsd.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="CSS\style.css">
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>    
    <body>
        <div class="login">
         <form method="post" action="loginAction.jsp">
            <table>
                <tr><td><h1>-Login-</h1></td></tr>
                <tr><td><input type="text" placeholder="Username" name="name"></td></tr>
                <tr><td><input type="password" placeholder ="Password" name="password"></td></tr>
                <tr><td><input type="submit" class="btn" value="Login"></td></tr>
                <%
                    
                if (session.getAttribute("loginFailed") != null && session.getAttribute("loginFailed").equals(true)) {
                        %>
                <tr><td><div class="error"><b>Error: User details invalid</b></div></td></tr>
                <%
                    }
                %>
                <tr><td><div class="subscript">No login? Register <a href="register.jsp">HERE</a></div></td></tr>
            </table>            
        </form>
        </div>
    </body>
</html>
