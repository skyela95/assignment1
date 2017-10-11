<%-- 
    Document   : Main
    Created on : 02/10/2017, 7:01:44 PM
    Author     : Madeleine
--%>
<%@page import="uts.wsd.rest.TutorService"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@page import="uts.wsd.Tutor"%>
<%@page import="java.util.ArrayList"%>
<%@page import="uts.wsd.Tutors"%>
<%@page import="uts.wsd.User.UserType"%>
<%@page import="uts.wsd.User" contentType="text/html" pageEncoding="UTF-8"%>

<link rel="stylesheet" type="text/css" href="CSS\style.css">
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Main</title>
    </head>
    <body>
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
        %>
        <div class ="title">
            <h1>UTS Tutor</h1>
        </div>
        <div class="navigationBar">
            <ul>
                <li><a class="active" href="main.jsp">Main</a></li>
                <li><a href="account.jsp">Account</a></li>
                <li><a href="booking.jsp">Booking</a></li>
                <li style="float:right"><a href="logoutAction.jsp">Logout</a></li>
                <li style="float:right"><div class="user"> Logged in as: <%=user.getName()%> [<%=user.getUserType().value()%>]</div></li>
            </ul>
        </div>
        <%

            if (user.getUserType() == UserType.STUDENT) {

                String searchStatus = request.getParameter("tutorStatus");

                String searchName = request.getParameter("tutorName");

                String searchSubject = request.getParameter("tutorSubject");
                Tutor.TutorSpecialty searchSubjectT;

                if (searchSubject == "WSD") {
                    searchSubjectT = Tutor.TutorSpecialty.WSD;
                } else if (searchSubject == "USP") {
                    searchSubjectT = Tutor.TutorSpecialty.USP;
                } else if (searchSubject == "SEP") {
                    searchSubjectT = Tutor.TutorSpecialty.SEP;
                } else if (searchSubject == "AppProg") {
                    searchSubjectT = Tutor.TutorSpecialty.AppProg;
                } else if (searchSubject == "MobileApp") {
                    searchSubjectT = Tutor.TutorSpecialty.MobileApp;
                } else {
                    searchSubjectT = null;
                }

                ArrayList<Tutor> tutors = bookingApp.getTutorList();
        %>

        <table align="center">
            <div class="detailsTitle">
                <h1>Search for a Tutor:</h1>
            </div>
            <form method="get" action="main.jsp">  
                <div class="detailsContent">
                    <tr><td><table>
                                <tr>
                                    <td>
                                        <select name="tutorName">
                                            <option value="Any">Any</option>
                                            <%
                                                ArrayList<String> tutorNames = new ArrayList<String>();
                                                for (Tutor tutor : tutors) {
                                                    tutorNames.add(tutor.getName());
                                                }
                                                for (String name : tutorNames) {
                                                    String using = name;%>
                                            <option value="<%=using%>"><%=using%></option>
                                            <% } %>
                                        </select>
                                    </td>
                                    <td>
                                        <select name="tutorSubject">
                                            <option value ="Any">Any</option>
                                            <option value ="WSD">WSD</option>
                                            <option value="USP">USP</option>
                                            <option value="SEP">SEP</option>
                                            <option value="AppProg">AppProg</option>
                                            <option value="MobileApp">MobileApp</option>
                                        </select>
                                    </td>
                                    <td>
                                        <select name="tutorStatus">
                                            <option value="Any">Any</option>
                                            <option value="Available">Available</option>
                                            <option value="Unavailable">Unavailable</option>
                                        </select>
                                    </td>
                                </tr>
                            </table></td></tr>  
                    <tr><td><input type="submit" class="btn" value="Search"></td></tr>
                </div>
            </form>
        </table>
        <%

            Tutors tutorsObj = bookingApp.getTutorsObject();
            ArrayList<Tutor> tutorsListTest = tutorsObj.getAll();
            TutorService service = new TutorService();

            if (searchStatus != null || searchName != null || searchSubject != null) {
                System.out.println("status " + searchStatus);
                System.out.println("name " + searchName);
                System.out.println("subject " + searchSubject);
                Tutors tutorsObject = bookingApp.getTutorsObject();
                ArrayList<Tutor> tutorSearch = tutorsObject.getTutors(searchName, searchSubject, searchStatus);
                System.out.println("tutorsearch: " + tutorSearch.size());
        %>

        <table align="center">
            <div class="detailsTitle">
                <h1>Avaliable Tutors:</h1>
            </div>
            <form method="post" action="booking.jsp">
                <div class="detailsContent">
                    <tr><td><table cellspacing="1">
                                <thead align="center">
                                    <tr>
                                        <td>Select</td><td>Name</td><td>Email</td><td>Subject</td><td>Status</td>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        for (Tutor tutor : tutorSearch) {
                                            String useName = tutor.getName();
                                            String useEmail = tutor.getEmail();
                                            String useSub = tutor.getSpecialty().toString();
                                            String useStat = null;
                                            if (tutor.isAvaliable() == true) {
                                                useStat = "Available";
                                            } else if (tutor.isAvaliable() == false) {
                                                useStat = "Unavailable";
                                            }
                                            session.setAttribute("tut", tutor);
                                    %>
                                    <tr class="tutorrow" align="center">

                                        <%if (tutor.isAvaliable()) {%>
                                <input type="hidden" value="<%=useName%>" name="mainSelectName">
                                <td><input type="submit" class="btn" value="Create Booking" name="mainSelect"></td>
                                    <%} else {%>
                                <td></td>
                                <% } %>

                                <td><%=useName%></td>
                                <td><%=useEmail%></td>
                                <td><%=useSub%></td>
                                <td><%=useStat%></td>
                    </tr>   
                    <%}%>
                    </tbody>
                    </table></td></tr>
                </div>
            </form>
        </table>
        <%}%> 
        <%} else if (user.getUserType() == UserType.TUTOR) {%>
        <table align ="center">
        <div class="detailsTitle">
            <tr><td><h1> Welcome <%=user.getName()%>!</h1></td></tr>
        </div>
        </table>
        <%}%>     
    </body>
</html>
