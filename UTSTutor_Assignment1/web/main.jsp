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
         <jsp:useBean id="tutorsClass" class="uts.wsd.Tutors" scope="page"/>
        <%
            User user = (User)session.getAttribute("user");
            if(user.getUserType() == UserType.STUDENT){  
                
                String searchStatus = request.getParameter("searchStatus");
                Boolean stat;
                if (searchStatus == "available"){stat = true;}
                else if (searchStatus == "unavailable"){stat = false;}
                else{stat = null;}
                
                String searchName = request.getParameter("tutorName");

                String searchSubject = request.getParameter("tutorSubject");
                Tutor.TutorSpecialty searchSubjectT;
                
                if(searchSubject == "WSD"){searchSubjectT = Tutor.TutorSpecialty.WSD;}
                else if(searchSubject == "USP"){searchSubjectT = Tutor.TutorSpecialty.USP;}
                else if(searchSubject == "SEP"){searchSubjectT = Tutor.TutorSpecialty.SEP;}
                else if(searchSubject == "AppProg"){searchSubjectT = Tutor.TutorSpecialty.AppProg;}
                else if(searchSubject == "MobileApp"){searchSubjectT = Tutor.TutorSpecialty.MobileApp;}
                else{searchSubjectT = null;}
                //get list of tutors, use as drop down data fill.
                ArrayList<Tutor> tutors = bookingApp.getTutorList();
                //ArrayList<String> tutorNames = new ArrayList<String>();
                //for(Tutor tutor : tutors){
                  //  tutorNames.add(tutor.getName());
                //}
        %>
        <form method="get" action="main.jsp">
            <table>
                <tr>
                    <td>
                        <select>
                            <c:forEach var="name" items="${tutorsClass.names}">
                                <option value="${name}">${name}</option>
                             </c:forEach>
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
                Tutors tutorsObj = bookingApp.getTutorsObject();
                //change to have proper.
                //ArrayList<Tutor> tutorsList = tutors.getTutors(searchName, searchSubject, searchStatus);
                ArrayList<Tutor> tutorsListTest = tutorsObj.getAll();
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
