<%-- 
    Document   : register
    Created on : 02/10/2017, 7:02:35 PM
    Author     : Madeleine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<link rel="stylesheet" type="text/css" href="CSS\style.css">
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <div class="detailsTitle">
            <h1>Please Register with your details below:</h1>
        </div>
        <table align="center">
            <form method="post" action="registerAction.jsp">
                <div class="detailsContent">
                    <tr><td><table>
                                <tr><td>Full Name:</td><td><input type="text" name="name"></td></tr>
                                <tr><td>Email:</td><td><input type="text" name="email"></td></tr>
                                <tr><td>Password:</td><td><input type="password" name="password"></td></tr>
                                <tr><td>DOB:</td><td><input type = "date" name="dob"></td></tr>
                                <tr><td>User Type:</td><td>
                                        <select onchange="showSubjectChoice(this);" id="type" name="userType">
                                            <option value="Student">Student</option>
                                            <option value="Tutor">Tutor</option>
                                        </select>
                                    </td></tr>

                                <tr><td><div id="subject" class="hidden">Subject Type:</div></td><td>
                                        <div id="subject2" class="hidden" name="subjectType">
                                            <select id="type" name="subjectType">
                                                <option value="WSD">WSD</option>
                                                <option value="USP">USP</option>
                                                <option value="SEP">SEP</option>
                                                <option value="AppProg">AppProg</option>
                                                <option value="MobileApp">MobileApp</option>
                                            </select>
                                        </div></td></tr>
                                <tr><td></td><td><input type="submit" class="btn" value="Register"></td></tr>
                            </table></td></tr> 
                </div>
            </form>
        </table>

        <div class ="errorpage">
            <%

                String errors = (String) session.getAttribute("accountCreateError");

                if (errors != null) {
                    if (!errors.equals("")) {
                        out.print(errors);
                    }
                }
                session.setAttribute("accountCreateError", null);
            %>
        </div>
        <script>
            function showSubjectChoice(e) {
                var strdisplay = e.options[e.selectedIndex].value;
                var e = document.getElementById("subject");
                var e2 = document.getElementById("subject2");
                if (strdisplay == "Student") {
                    e.style.display = "none";
                    e2.style.display = "none";
                } else {
                    e.style.display = "block";
                    e2.style.display = "block";
                }
            }
        </script>

    </body>
</html>



