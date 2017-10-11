<%-- 
 The booking page where users can view all bookings, active bookings and book 
tutors if redirected from the main page from search results
--%>
<%@page import="uts.wsd.User.UserType"%>
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

<link rel="stylesheet" type="text/css" href="CSS\style.css">
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bookings</title>
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
            }%>
        <div class ="title">
            <h1>UTS Tutor</h1>
        </div>
        <div class="navigationBar">
            <ul>
                <li><a href="main.jsp">Main</a></li>
                <li><a href="account.jsp">Account</a></li>
                <li><a class="active" href="booking.jsp">Booking</a></li>
                <li style="float:right"><a href="logoutAction.jsp">Logout</a></li>
                <li style="float:right"><div class="user"> Logged in as: <%=user.getName()%> [<%=user.getUserType().value()%>]</div></li>
            </ul>
        </div>
        <%

            //HANDLE ALL FUNCTIONS HERE AFTER GETTING THE USER.
            //create a booking, if create was clicked:
            if (request.getParameter("createName") != null) {
                String createName = request.getParameter("createName");
                Tutor createTutor = bookingApp.getTutorsObject().getTutorByName(createName);
                bookingApp.createBooking((Student) user, createTutor);
            }

            if (request.getParameter("cancelID") != null) {
                int cancelID = Integer.parseInt(request.getParameter("cancelID"));
                bookingApp.cancelBookingByID(cancelID);
            }

            if (request.getParameter("completeID") != null) {
                int completeID = Integer.parseInt(request.getParameter("completeID"));
                bookingApp.completeBookingByID(completeID);
            }

            boolean allBookings = true;
            if (request.getParameter("viewAll") != null) {
                allBookings = true;
            } else if (request.getParameter("viewActive") != null) {
                allBookings = false;
            } else {
                allBookings = true;
            }

            //FOR STUDENT ONLY
            if (user.getUserType() == User.UserType.STUDENT) {

                //Tutor tutor = (Tutor)session.getAttribute("tut");
                if (request.getParameter("mainSelectName") != null) {
                    //}
                    Tutor tutor = bookingApp.getTutorsObject().getTutorByName(request.getParameter("mainSelectName"));
                    String availability = null;
                    if (tutor != null) {
                        if (tutor.isAvaliable() == true) {
                            availability = "Available";
                        } else if (tutor.isAvaliable() == false) {
                            availability = "Unavailable";
                        }
                        if (tutor.isAvaliable()) {
        %>
        <div class="detailsTitle">
            <h1>Selected Tutor: </h1>
        </div>
        <table align="center" cellspacing="1">
            <form method="post" action="booking.jsp">
                <div class="detailsContent">
                    <tr class="tutorrow"><td><table cellspacing="1">
                                <tr><td>Name: </td><td><%=tutor.getName()%></td></tr>
                                <tr><td>Subject: </td><td><%=tutor.getSpecialty()%></td></tr>
                                <tr><td>Status: </td><td><%=availability%></td></tr>
                                <input type="hidden" value="<%=tutor.getName()%>" name="createName"/>                
                            </table></td></tr>
                </div>
                <tr><td><input type="submit" class="btn" value="Book Tutor" name="createBooking"/></td></tr>
            </form>
        </table>     
        <hr>
        <%} else {%>
        <div class="errorpage">
            <h1>The tutor is not available. Please go back to main.</h1>
        </div>
        <hr>
        <%}
                    }
                }
            }%>

        <%
            //if session has a selected booking, show that booking and give options.
            Booking selectedBooking = null;
            String selectID = request.getParameter("selectedID");
            if (request.getParameter("selectedID") != null) {
                selectedBooking = bookingApp.getBookingsObject().getByID(Integer.parseInt(selectID));
            }
            //display selected booking.
            if (selectedBooking != null) {
                int selectedID = selectedBooking.getBookingID();
                String selectedSub = selectedBooking.getSubjectName().toString();
                String selectedTut = selectedBooking.getTutorName();
                String selectedStat = selectedBooking.getStatusType().toString();
        %>
        <div class="detailsTitle">
            <h1>Selected Booking:</h1>
        </div>
        <table align="center">
            <form method="post" action="booking.jsp">
                <div class="detailsContent">
                    <tr><td><table>
                                <thead align="center">
                                    <tr>
                                        <td>ID</td><td>Subject</td><td>Tutor</td><td>Status</td>
                                    </tr>
                                </thead>
                                <tr class="tutorrow">
                                <td>ID: <%=selectedID%></td>
                                <td>Subject: <%=selectedSub%></td>
                                <td>Tutor: <%=selectedTut%></td>
                                <td>Status: <%=selectedStat%></td>
                                <%if (user.getUserType() == UserType.STUDENT) {%>
                                <input type="hidden" value="<%=selectedID%>" name="cancelID">
                                <td><input type="submit" class="btn" value="Cancel Booking" name="cancelBooking"></td>
                                <% } else { %>
                                <input type="hidden" value="<%=selectedID%>" name="completeID">
                                <td><input type="submit" class="btn" value="Complete Booking" name="completeBooking"></td>
                    </tr>
                    <%}%>
                    </table></td></tr>
                </div>
            </form>
        </table>
        <hr>
        <%}%>

        <!--<form method="post" action="booking.jsp"> -->
        <table align="center">
            <div class="detailsContent">
                </tr><td><table>
                        <form method="post" action="booking.jsp">
                            <input type="submit" class="btn" value="View All Bookings" name="viewAll">
                        </form>
                        <form method="post" action="booking.jsp">
                            <input type="submit" class="btn" value="View Active Bookings" name="viewActive">
                        </form>
                    </table></td></tr>
            </div>
        </table>

        <%
            if (allBookings == true) {%>
        <div class="detailsTitle">
            <h1>All Bookings:</h1> 
            <div>
                <%
                    ArrayList<Booking> bookings2;
                    if (user.getUserType() == UserType.STUDENT) {
                    bookings2 = bookingApp.getBookingsObject().getbyStudentEmail(user.getEmail()); 
                    } else {
                        bookings2 = bookingApp.getBookingsObject().getbyTutorEmail(user.getEmail()); 
                    }
                %>
                <table align="center">
                    <div class="detailsContent">
                        <tr><td><table>
                                    <thead align="center">
                                        <tr>
                                            <td>ID</td><td>Subject</td><td>Tutor</td><td>Status</td>
                                        </tr>
                                    </thead>
                                    <%
                                        for (Booking bookings : bookings2) {
                                            int bookID = bookings.getBookingID();
                                            String subname = bookings.getSubjectName().toString();
                                            String tutName = bookings.getTutorName();
                                            String stat = bookings.getStatusType().toString();
                                    %>
                                    <tr class="tutorrow">
                                        <td>ID: <%=bookID%></td>
                                        <td>Subject: <%=subname%></td>
                                        <td>Tutor: <%=tutName%></td>
                                        <td>Status: <%=stat%></td>
                                        <td><form method="post" action="booking.jsp">
                                                <input type="submit" value="Select Booking" name="selectBooking"/>
                                                <input type="hidden" value="<%=bookID%>" name="selectedID"/>
                                            </form></td>
                                            <%}%>
                                    </tr>

                                </table></td></tr>
                </table>
                <% } else if (allBookings == false) {%>
                <div class="detailsTitle">
                    <h1>Active Bookings:</h1> 
                </div>
                <table align="center">
                    <div class="detailsContent">
                        <tr><td><table>
                                    <%
                                        ArrayList<Booking> actBook;
                                        if (user.getUserType() == UserType.STUDENT) {
                                                actBook = bookingApp.getBookingsObject().getbyStudentEmail(user.getEmail());
                                        } else {
                                            actBook = bookingApp.getBookingsObject().getbyTutorEmail(user.getEmail());
                                        }
                                        int counter = 0;
                                        for (Booking books : actBook) {
                                            if (books.getStatusType() == Booking.StatusType.ACTIVE) {
                                                counter++;
                                                int ID = books.getBookingID();
                                                String subName = books.getSubjectName().toString();
                                                String tutName = books.getTutorName();
                                                String studentName = books.getStudentName();
                                                String stat = books.getStatusType().toString();
                                                //DONE: table now starts before the for loop, not after.. %>
                                    <tr class="tutorrow"><td>ID: <%=ID%></td>
                                        <td>Subject: <%=subName%></td>
                                        <% if (user.getUserType() == UserType.TUTOR) { %>
                                           <td>Student: <%=studentName%></td> 
                                        <% } else {%>
                                        <td>Tutor: <%=tutName%></td>
                                        <% } %>
                                        <td>Status: <%=stat%></td>
                                        <td><form method="post" action="booking.jsp">
                                                <input type="submit" value="Select Booking" name="selectBooking"/>
                                                <input type="hidden" value="<%=ID%>" name="selectedID"/>
                                            </form></td></tr>
                    </div>
                </table></td></tr>

            </div>
        </table>
        <% }
            }
            if (counter == 0) {%>
        <div class="errorpage">
            <p>No Active Bookings.</p>
            <%}
                }%>
            </body>
            </html>