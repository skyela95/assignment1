<%-- 
    Document   : booking
    Created on : 08/10/2017, 7:14:46 PM
    Author     : Madeleine
--%>
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
            ArrayList<Booking> bookings = user.getBookings().getByStatus(Booking.StatusType.ACTIVE);
            BookingService service = new BookingService();            
          %>
          <c:import url="http://localhost:8080/UTSTutor_Assignment1/rest/bookings/ID?query=12345"
                      var="inputDoc" />
          <c:import url="/Users/Madeleine/Desktop/20172/WSD/Assi1/UTSTutor_Assignment1/src/java/stylesheets/bookings.xsl"
                      var="stylesheet" />
        <x:transform xml  = "${inputDoc}" xslt = "${stylesheet}"/>
    </body>
</html>
