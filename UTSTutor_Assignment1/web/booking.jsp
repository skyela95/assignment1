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
            boolean allBookings = false;
            //check if a tutor has been passed in.
            //if yes - display selected tutor.
            //same with booking - only one, not both.
            //if no - skip this.
            //display all bookings active for user in xslt 
            //selected booking - shows options with it.
            //view all booking history for user.
            //only tutor can compelete a booking.
            //Tutor tutor = request.getParameter("tutor");
            User user = (User) session.getAttribute("user");
           //ArrayList<Booking> bookings = user.getBookings().getByStatus("ACTIVE");
            //BookingService service = new BookingService();    
            Bookings bookings2 = bookingApp.getBookingsObject();
            String passedName = request.getParameter("useName");
            
            //FOR STUDENT ONLY
            Tutor tutor = (Tutor)session.getAttribute("tut");
            String availability = null;
            if(tutor!=null){
                if(tutor.isAvaliable() == true){availability = "available";}
                else if (tutor.isAvaliable()==false){availability = "unavailable";}
                if(tutor.isAvaliable()){
                %>
                <p>Selected Tutor: </p>
                <table>
                    <tr><td>Name: </td><td><%=tutor.getName()%></td></tr>
                    <tr><td>Subject: </td><td><%=tutor.getSpecialty()%></td></tr>
                    <tr><td>Status: </td><td><%=availability%></td></tr>
                    <tr><input type="button" value="create booking" 
                               onClick="
                               <%System.out.println("BUTTON CLICKED");
                               bookingApp.createBooking((Student)user, tutor);%>
                               "></tr>
                </table>
            <%}else{%>
            <p>The tutor is not available. Please go back to main.</p>
            <%}}%>
            <%//FOR BOTH
                //if there's a booking selected.
                Booking booking = null;
                booking = (Booking)request.getAttribute("booking");
                BookingService service = new BookingService();
                //service.getBookingID(1);
                String pathName = "http://localhost:8080/UTSTutor_Assignment1/rest/bookings/booking/bookingID?ID=" + booking.getBookingID();
                
            %>
            <c:import url="${pathName}"
                      var="inputDoc" />
            <c:import url="/WEB-INF/bookings.xsl"
                      var="stylesheet" />
            <x:transform xml  = "${inputDoc}" xslt = "${stylesheet}"/>
            <form method="post" action="booking.jsp">
                <input type="submit" value="cancel booking"onclick="
                       <%
                           //request.setAttribute("booking", booking);
                           System.out.println("cancelling booking");
                           bookingApp.cancelBookingByID(booking.getBookingID());                           
                       %>">
            </form>
            
            <!--<form method="post" action="booking.jsp"> -->
            <input type="button" value="View All Bookings" onClick="<%allBookings = true;%>">
            <input type="button" value="View Active Bookings" onClick="<%allBookings = false;%>">

            <%
                if(allBookings == true){%>
                <p>All bookings returned</p> 
               <% }
                else if(allBookings == false){%>
                <p>active bookings returned.</p>  
               <% }
            %>
    </body>
</html>
