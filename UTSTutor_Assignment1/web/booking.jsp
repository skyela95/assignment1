<%-- 
    Document   : booking
    Created on : 08/10/2017, 7:14:46 PM
    Author     : Madeleine
--%>
<%@page import="uts.wsd.Student"%>
<%@page import="uts.wsd.Bookings"%>
<%@page import="uts.wsd.Tutor"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@page import="uts.wsd.rest.BookingService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="uts.wsd.Booking"%>
<%@page import="uts.wsd.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bookings</title>
    </head>
    <body>
        <h1>This is the bookings page</h1>
        <% String filePath = application.getRealPath("\\");%>
        <jsp:useBean id ="bookingApp" class="uts.wsd.BookingApplication" scope="application">
            <jsp:setProperty name="bookingApp" property="filePath" value="<%=filePath%>"/>
        </jsp:useBean>
        
        <% 
            User user = (User) session.getAttribute("user");
            
            //HANDLE ALL FUNCTIONS HERE AFTER GETTING THE USER.
            //create a booking, if create was clicked:
            if(request.getParameter("createName")!=null){
                String createName = request.getParameter("createName");
                Tutor createTutor = bookingApp.getTutorsObject().getTutorByName(createName);
                bookingApp.createBooking((Student)user, createTutor);
            }
            
            if(request.getParameter("cancelID")!=null){
                int cancelID = Integer.parseInt(request.getParameter("cancelID"));
                bookingApp.cancelBookingByID(cancelID);
            }
            
            if(request.getParameter("completeID")!=null){
                int completeID = Integer.parseInt(request.getParameter("completeID"));
                bookingApp.completeBookingByID(completeID);
            }
            
            boolean allBookings = true;
            if(request.getParameter("viewAll") != null){ allBookings = true; }
            else if(request.getParameter("viewActive") != null){allBookings = false;}
            else{allBookings = true;}
            
            //FOR STUDENT ONLY
            if(user.getUserType() == User.UserType.STUDENT){
            
            //Tutor tutor = (Tutor)session.getAttribute("tut");
            if(request.getParameter("mainSelectName")!=null){
            //}
            Tutor tutor = bookingApp.getTutorsObject().getTutorByName(request.getParameter("mainSelectName"));
            String availability = null;
            if(tutor!=null){
                if(tutor.isAvaliable() == true){availability = "available";}
                else if (tutor.isAvaliable()==false){availability = "unavailable";}
                if(tutor.isAvaliable()){
                %>
                <p>Selected Tutor: </p>
                <form method="post" action="booking.jsp">
                <table>
                    <tr><td>Name: </td><td><%=tutor.getName()%></td></tr>
                    <tr><td>Subject: </td><td><%=tutor.getSpecialty()%></td></tr>
                    <tr><td>Status: </td><td><%=availability%></td></tr>
                    <tr><td><input type="hidden" value="<%=tutor.getName()%>" name="createName"/></td></tr>
                    <tr><td><input type="submit" value="create booking" name="createBooking"/></td></tr>
                </table>
                </form>
            <%}else{%>
            <p>The tutor is not available. Please go back to main.</p>
            <%} } } }%>
            
            <%
                //if session has a selected booking, show that booking and give options.
                Booking selectedBooking = null;
                String selectID = request.getParameter("selectedID");
                if(request.getParameter("selectedID") != null){
                    selectedBooking = bookingApp.getBookingsObject().getByID(Integer.parseInt(selectID));
                }
                //display selected booking.
                if (selectedBooking!=null){ 
                    int selectedID = selectedBooking.getBookingID();
                    String selectedSub = selectedBooking.getSubjectName().toString();
                    String selectedTut = selectedBooking.getTutorName();
                    String selectedStat = selectedBooking.getStatusType().toString();
                %>
                <form method="post" action="booking.jsp">
                <table>
                    <tr><td>Selected Booking</td></tr>
                    <td>ID: <%=selectedID%></td>
                    <td>Subject: <%=selectedSub%></td>
                    <td>Tutor: <%=selectedTut%></td>
                    <td>Status: <%=selectedStat%></td>
                    <tr><td><input type="hidden" value="<%=selectedID%>" name="cancelID"/></td></tr>
                    <tr><td><input type="submit" value="cancel booking" name="cancelBooking"/></td></tr>
                    <%if(user.getUserType() == User.UserType.TUTOR){%>
                    <tr><td><input type="hidden" value="<%=selectedID%>" name="completeID"/></td></tr>
                    <tr><td><input type="submit" value="complete booking" name="completeBooking"/></td></tr>
                    <%}%>
                </table>
                </form>
                <%}%>
            
            <!--<form method="post" action="booking.jsp"> 
            Can change to get if implement loggedin check. -->
            <form method="post" action="booking.jsp">
                <input type="submit" value="View All Bookings" name="viewAll">
            </form>
            <form method="post" action="booking.jsp">
                 <input type="submit" value="View Active Bookings" name="viewActive">
            </form>

            <%
                if(allBookings == true){%>
                <p>All bookings returned</p> 
                <%
                    ArrayList<Booking> bookings2 = bookingApp.getBookingsObject().getbyStudentEmail(user.getEmail());
                %>
                <table>
                    <tr><td>Bookings</td></tr>
                      <%
                            for(Booking bookings : bookings2){
                                int bookID = bookings.getBookingID();
                                String subname = bookings.getSubjectName().toString();
                                String tutName = bookings.getTutorName();
                                String stat = bookings.getStatusType().toString();
                        %>
                    <tr>
                            <td>ID: <%=bookID%></td>
                            <td>Subject: <%=subname%></td>
                            <td>Tutor: <%=tutName%></td>
                            <td>Status: <%=stat%></td>
                            <td><form method="post" action="booking.jsp">
                            <input type="submit" value="select booking" name="selectBooking"/>
                            <input type="hidden" value="<%=bookID%>" name="selectedID"/>
                            </form></td>
                          <%}%>
                    </tr>
                    
                </table>
                
               <% }
                else if(allBookings == false){%>
                <p>active bookings returned.</p> 
                <table>
                <%
                    ArrayList<Booking> actBook = bookingApp.getBookingsObject().getbyStudentEmail(user.getEmail());
                    for (Booking books : actBook){
                        if(books.getStatusType() == Booking.StatusType.ACTIVE){
                        int ID = books.getBookingID();
                        String subName = books.getSubjectName().toString();
                        String tutName = books.getTutorName();
                        String stat = books.getStatusType().toString(); 
                //DONE: table now starts before the for loop, not after.. %>
                            <tr><td>Bookings</td></tr>
                            <tr><td>ID: <%=ID%></td>
                                <td>Subject: <%=subName%></td>
                                <td>Tutor: <%=tutName%></td>
                                <td>Status: <%=stat%></td></tr>
                                <td><form method="post" action="booking.jsp">
                                <input type="submit" value="select booking" name="selectBooking"/>
                                <input type="hidden" value="<%=ID%>" name="selectedID"/>
                                </form></td>
                <%}else{%><p>No Active Bookings.</p> <%}%>
                  </table>
               <% }}else{%><p>Please press a button above.</p><% }%>
    </body>
</html>