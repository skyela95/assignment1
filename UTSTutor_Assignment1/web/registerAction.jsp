<%-- 
    Handles all the validation required for registering a user
--%>

<%@page import="uts.wsd.Tutor"%>
<%@page import="uts.wsd.Tutor.TutorSpecialty"%>
<%@page import="uts.wsd.RegexHelper"%>
<%@page import="uts.wsd.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <% String filePath = application.getRealPath("\\");%>
    <jsp:useBean id ="bookingApp" class="uts.wsd.BookingApplication" scope="application">
        <jsp:setProperty name="bookingApp" property="filePath" value="<%=filePath%>"/>
    </jsp:useBean>

    <%
        String errors = "";
        String name = request.getParameter("name").trim();
        String email = request.getParameter("email").trim();
        String password = request.getParameter("password").trim();
        String dob = request.getParameter("dob").trim();
        String userType = request.getParameter("userType");
        String subject = request.getParameter("subjectType");
        TutorSpecialty specialty;

        if (subject == "WSD") {
            specialty = Tutor.TutorSpecialty.WSD;
        } else if (subject == "USP") {
            specialty = Tutor.TutorSpecialty.USP;
        } else if (subject == "SEP") {
            specialty = Tutor.TutorSpecialty.SEP;
        } else if (subject == "AppProg") {
            specialty = Tutor.TutorSpecialty.AppProg;
        } else if (subject == "MobileApp") {
            specialty = Tutor.TutorSpecialty.MobileApp;
        } else {
            specialty = null;
        }

        User.UserType userTypeT = null;
        if (userType.equals("Student")) {
            userTypeT = User.UserType.STUDENT;
        } else if (userType.equals("Tutor")) {
            userTypeT = User.UserType.TUTOR;
        }

        if ((name != null && !name.equals("")) &&
                (email != null && !email.equals("")) 
                && (password != null && !password.equals(""))
                && (dob != null && !dob.equals("")) 
                && (userType != null && !userType.equals(""))) {

            if (userTypeT == User.UserType.STUDENT) {
                if (bookingApp.getStudentsObject().getStudentByEmail(email) != null) {
                    errors += "User already exists: email matches existing user\n";
                } else if (bookingApp.getTutorsObject().getTutorByEmail(email) != null) {
                    errors += "User already exists: email matches existing user\n";
                }
            }

            //test regex:
            if (!RegexHelper.TestDOB(dob)) {
                errors += "Date of Birth is not in a valid format [01/01/1990]\n";
            }
            if (!RegexHelper.TestEmail(email)) {
                errors += "Email is not in a valid format\n";
            }
            if (!RegexHelper.TestName(name)) {
                errors += "Name is not in a valid format [John Smith]\n";
            }

        } else {
            errors += "User did not enter all fields\n";
        }

        if (errors == "") {
                       User user = null;
            //go ahead with registering user.
            if (userTypeT == User.UserType.STUDENT) {
                user = bookingApp.createStudent(name, email, password, dob);
            } else if (userTypeT == User.UserType.TUTOR) {
                user = bookingApp.createTutor(name, email, password, dob, specialty);
            }
            if (user != null) {
                session.setAttribute("user", user);
                bookingApp.login(user.getEmail(), user.getPassword());
                response.sendRedirect("main.jsp");
                return;
            } else {
                errors += "Error creating account. Try again\n";
                session.setAttribute("accountCreateError", errors);
            response.sendRedirect("register.jsp");

            }

            response.sendRedirect("register.jsp");
            return;
        } else {
            session.setAttribute("accountCreateError", errors);
            response.sendRedirect("register.jsp");
        }
    %>
    <body>
    </body>
</html>
