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
        <%
            User user = (User)session.getAttribute("user");
            if(user.getUserType() == UserType.STUDENT){  
                
                String searchStatus = request.getParameter("tutorStatus");
                
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
                        <select name="tutorName">
                            <option value="Any">Any</option>
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
                 System.out.println("status " + searchStatus);
                  System.out.println("name " + searchName);
                   System.out.println("subject " + searchSubject);
                Tutors tutorsObject = bookingApp.getTutorsObject();
                ArrayList<Tutor> tutorSearch = tutorsObject.getTutors(searchName, searchSubject, searchStatus);
                System.out.println("tutorsearch: " + tutorSearch.size());
                //ArrayList<Tutor> tutorsXML = service.getAllTutors();
                //ArrayList<Tutor> tutorSearch = tutorsObject.getTutorsByAvailability(true);
                //if search is not returning null.
                //if returns null, read error message.
        %>
            
            <form method="post" action="booking.jsp">
                <table>
                    <thead>
                        <tr>
                            <td>Select</td><td>Name</td><td>Email</td><td>Subject</td><td>Status</td>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (Tutor tutor : tutorSearch){
                                String useName = tutor.getName();
                                String useEmail = tutor.getEmail();
                                String useSub = tutor.getSpecialty().toString();
                                String useStat = null;
                                if(tutor.isAvaliable() == true){useStat = "available";}
                                else if(tutor.isAvaliable() == false){useStat = "unavailable";}
                                session.setAttribute("tut", tutor);
                            %>
                            <tr>
                                
                                <%if(tutor.isAvaliable()){%>
                                <td><input type="hidden" value="<%=useName%>" name="mainSelectName"/></td>
                                <td><input type="submit" value="create booking" name="mainSelect"/></td>
                                <%}%>
                                <td><%=useName%></td>
                                <td><%=useEmail%></td>
                                <td><%=useSub%></td>
                                <td><%=useStat%></td>
                            </tr>   
                            <%}%>
                    </tbody>
                </table>
            </form>
            <%}%> 
        <%} else if(user.getUserType() == UserType.TUTOR){ %>
        <p> DELETE THIS:
        TUTORS don't get access to search functionality.</p>
        <%}%>     
    </body>
</html>
