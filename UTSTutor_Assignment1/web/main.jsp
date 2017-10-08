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
            <c:import url="/Users/Madeleine/Desktop/20172/WSD/Assi1/UTSTutor_Assignment1/build/web/WEB-INF/tutors.xml"
                      var="inputDoc" />
            <c:import url="/Users/Madeleine/Desktop/20172/WSD/Assi1/UTSTutor_Assignment1/src/java/stylesheets/tutors.xsl"
                      var="stylesheet" />
        <%
            User user = (User) session.getAttribute("user");
            if(user.getUserType() == UserType.STUDENT){  
                String searchStatus = request.getParameter("searchStatus");
                Boolean stat;
                if (searchStatus == "true"){
                    stat = true;
                }
                else if (searchStatus == "false"){
                    stat = false;
                }
                else{
                    stat = null;
                }
                String searchName = request.getParameter("searchName");
                String searchSubject = request.getParameter("searchSubject");
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
                        <select name = "tutor name">
                            <option value="filler">name 1</option>
                            <option value="filler">name 2 </option>
                        </select>
                    </td>
                    <td>
                        <select name="tutor subject">
                            <option value ="filler">subject 1</option>
                            <option value="filler">subject 2</option>
                        </select>
                    </td>
                    <td>
                        <select name="tutor status">
                            <option value="filler">available</option>
                            <option value="filler">unavailable</option>
                        </select>
                    </td>
                </tr>
                <tr><td></td><td><input type="submit" value="Search"></td></tr>
            </table>            
        </form>
        <%
            ArrayList<Tutor> tutors = new ArrayList<Tutor>();
            //tutors = Tutors.getTutors(searchName, searchSubject, stat);
            if (searchStatus!=null || searchName !=null || searchSubject!=null){
                //do the search and display xsl --?
                //need to marshal a list to xml, then use that, not the generic xml file.
        %>
        <x:transform xml  = "${inputDoc}" xslt = "${stylesheet}"/>
        <%}%>
        <%} else if(user.getUserType() == UserType.TUTOR){ %>
        <p> DELETE THIS:
        TUTORS don't get access to search functionality.</p>
        <%}%>     
    </body>
</html>
