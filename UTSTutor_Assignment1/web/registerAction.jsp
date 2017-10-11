<%-- 
    Document   : registerAction
    Created on : 11/10/2017, 5:41:21 PM
    Author     : Madeleine
--%>

<%@page import="uts.wsd.RegexHelper"%>
<%@page import="uts.wsd.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registering..</title>
    </head>
        <% String filePath = application.getRealPath("\\");%>
        <jsp:useBean id ="bookingApp" class="uts.wsd.BookingApplication" scope="application">
        <jsp:setProperty name="bookingApp" property="filePath" value="<%=filePath%>"/>
        </jsp:useBean>
        
        <%
            String errors ="";
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String dob = request.getParameter("dob");
            String userType = request.getParameter("userType");
            User.UserType userTypeT = null;
            if(userType == "STUDENT"){userTypeT = User.UserType.STUDENT;}
            else if(userType == "TUTOR"){userTypeT = User.UserType.TUTOR;}
            
            if(name!=null && email!=null && password !=null && dob!=null && userType!=null){
                
                if(userTypeT == User.UserType.STUDENT){
                    if(name == bookingApp.getStudentsObject().getStudentByName(name).getName()){
                        errors += "User already exists: name matches existing user";
                    }
                    else if(email == bookingApp.getTutorsObject().getTutorByEmail(email).getEmail()){
                        errors += "User already exists: email matches existing user";
                    }
                }
                
                //test regex:
                if (!RegexHelper.TestDOB(dob)) {
                    errors += "Date of Birth is not in a valid format [01/01/1990]\n";
                }
                if(!RegexHelper.TestEmail(email)){
                    errors+= "Email is not in a valid format";
                }
                if(!RegexHelper.TestName(name)){
                    errors += "Name is not in a valid format [John Smith]\n";
                }
                
                if(errors == ""){
                    User user = null;
                    //go ahead with registering user.
                    if(userTypeT == User.UserType.STUDENT){
                        user = new User(name, email, password, dob, userTypeT);
                        bookingApp.getStudentsObject().addUser(user);
                        bookingApp.saveStudents();
                    }
                    else if(userTypeT == User.UserType.TUTOR){
                        user = new User(name, email, password, dob, userTypeT);
                        bookingApp.getTutorsObject().addUser(user);
                        bookingApp.saveTutors();
                    }
                    if (user !=null){
                        session.setAttribute("user", user);
                        response.sendRedirect("main.jsp");
                    }
                }
                
            }
            else{
                errors += "User did not enter all fields.";
                response.sendRedirect("register.jsp");
            }
        %>
    <body>
        <p></p>
    </body>
</html>
