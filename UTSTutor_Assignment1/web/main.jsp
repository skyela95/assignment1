<%-- 
    Document   : Main
    Created on : 02/10/2017, 7:01:44 PM
    Author     : Madeleine
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@page import="uts.wsd.Tutor"%>
<%@page import="java.util.ArrayList"%>
<%@page import="uts.wsd.Tutors"%>
<%@page import="uts.wsd.User.UserType"%>
<%@page import="uts.wsd.User" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Main</title>
    </head>
    <body>
        <h1>Main Page</h1>
        <% String filePath = application.getRealPath("\\");%>
        <jsp:useBean id ="bookingApp" class="uts.wsd.BookingApplication" scope="application">
            <jsp:setProperty name="bookingApp" property="filePath" value="<%=filePath%>"/>
        </jsp:useBean>
        <%
            User user = (User) session.getAttribute("user");
            if(user.getUserType() == UserType.STUDENT){  
                //chnange to be tutorstat type.
                String searchStatus = request.getParameter("searchStatus");
                Boolean stat;
                if (searchStatus == "available"){
                    stat = true;
                }
                else if (searchStatus == "unavailable"){
                    stat = false;
                }
                else{
                    stat = null;
                }
                String searchName = request.getParameter("tutorName");
                //change to be tutorspecialty type match.
                String searchSubject = request.getParameter("tutorSubject");
                //get list of tutors, use as drop down data fill.
        %>
        <p> DELETE THIS: search form(STUDENT), for tutors using: subj, name, status.
        results displayed (name, email, sub, stat) - selectable.
        when student selects tutor, takes to booking page.</p>
        <p>marshall xml to java, then create list, use list for dropdowns.</p>
        <form method="get" action="main.jsp">
            <table>
                <tr>
                    <td>
                        <select name = "tutorName">
                            <option value="Steve Jobs">Steve Jobs</option>
                            <option value="Bill Gates">Bill Gates</option>
                        </select>
                    </td>
                    <td>
                        <select name="tutorSubject">
                            <option value ="WSD">WSD</option>
                            <option value="USP">USP</option>
                            <option value="SEP">SEP</option>
                            <option value="AppProg">AppProg</option>
                            <option value="MobileApp">MobileApp</option>
                        </select>
                    </td>
                    <td>
                        <select name="tutorStatus">
                            <option value="available">available</option>
                            <option value="unavailable">unavailable</option>
                        </select>
                    </td>
                </tr>
                <tr><td></td><td><input type="submit" value="Search"></td></tr>
            </table>            
        </form>
        <%
            //tutors = Tutors.getTutors(searchName, searchSubject, stat);
            if (searchStatus!=null || searchName !=null || searchSubject!=null){
                //do search, return list of tutors
                //marshall the list to xml, pass to inputDoc.
                Tutors tutors = bookingApp.getTutorsObject();
                //ArrayList<Tutor> tutorsList = tutors.getTutors(searchName, searchSubject, searchStatus);
                ArrayList<Tutor> tutorsListTest = tutors.getAll();
                //pass through REST to get XML.
                //pass xml into inputDoc spot.
        %>
        <c:import url="/Users/Madeleine/Desktop/20172/WSD/Assi1/UTSTutor_Assignment1/build/web/WEB-INF/tutors.xml"
                      var="inputDoc" />
        <c:import url="/Users/Madeleine/Desktop/20172/WSD/Assi1/UTSTutor_Assignment1/src/java/stylesheets/tutors.xsl"
                      var="stylesheet" />
        <x:transform xml  = "${inputDoc}" xslt = "${stylesheet}"/>
        <%}%>
        <%} else if(user.getUserType() == UserType.TUTOR){ %>
        <p> DELETE THIS:
        TUTORS don't get access to search functionality.</p>
        <%}%>     
    </body>
</html>
