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
                //for(Tutor tutor : tutors){
                  //  tutorNames.add(tutor.getName());
                //}
        %>
        <form method="get" action="main.jsp">
            <table>
                <tr>
                    <td>
                        <select>
                            <%
                                ArrayList<String> tutorNames = new ArrayList<String>();
                                for(Tutor tutor : tutors){
                                    tutorNames.add(tutor.getName());
                                }
                                for(String name : tutorNames){
                                    String using = name;%>
                                <option value="<%=using%>"><%=using%></option>
                               <% } %>
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
            //if (stat!=null || searchName !=null || searchSubjectT!=null){
                //do search, return list of tutors
                //marshall the list to xml, pass to inputDoc.
                Tutors tutorsObj = bookingApp.getTutorsObject();
                //change to have proper.
                //ArrayList<Tutor> tutorsList = tutors.getTutors(searchName, searchSubject, searchStatus);
                ArrayList<Tutor> tutorsListTest = tutorsObj.getAll();
                //pass through REST to get XML.
                //pass xml into inputDoc spot.
                TutorService service = new TutorService();
                //service.getAllTutors();
        %>
      
        <%//}%>
        <%
            if(searchStatus!=null || searchName !=null || searchSubject !=null){
                Tutors tutorsObject = bookingApp.getTutorsObject();
                ArrayList<Tutor> tutorSearch = tutorsObject.getTutors("Steve Jobs", Tutor.TutorSpecialty.WSD, true);
                ArrayList<Tutor> tutorsXML = service.getAllTutors();
        %>
            <p>Hi the if statement works!</p>  
                <c:import url="/WEB-INF/tutors.xml"
                      var="inputDoc" />
                <c:import url="/WEB-INF/tutors.xsl"
                      var="stylesheet" />
                <x:transform xml  = "<%=tutorsXML%>" xslt = "${stylesheet}"/>
            <%}
        %> 
        <%} else if(user.getUserType() == UserType.TUTOR){ %>
        <p> DELETE THIS:
        TUTORS don't get access to search functionality.</p>
        <%}%>     
    </body>
</html>
