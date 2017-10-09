/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.wsd.soap;

import java.util.ArrayList;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.servlet.ServletContext;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import uts.wsd.Booking;
import uts.wsd.Booking.StatusType;
import uts.wsd.BookingApplication;
import uts.wsd.Bookings;
import uts.wsd.Student;
import uts.wsd.Tutor;
import uts.wsd.Tutors;
import uts.wsd.User;

/**
 *
 * @author Adam
 */
@WebService(serviceName = "bookingApp")
public class BookingSOAP {

    @Resource
    private WebServiceContext context;

    private BookingApplication getBookingApp() {
        ServletContext application = (ServletContext) context.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
        BookingApplication bookingApp = (BookingApplication) application.getAttribute("bookingApp");
        if (bookingApp == null) {
            bookingApp = new BookingApplication();
            bookingApp.setFilePath(application.getRealPath("\\"));
        }
        application.setAttribute("bookingApp", bookingApp);

        return bookingApp;
    }

    @WebMethod
    public ArrayList<Tutor> getTutors() {
        return getBookingApp().getTutorList();
    }

    @WebMethod
    public User login(String email, String password) {
        return getBookingApp().login(email, password);
    }

    @WebMethod
    public void logout() {
        getBookingApp().logout();
    }
    
    @WebMethod
    public User getLoggedUser() {
        return getBookingApp().getLoggedUser();
    }
    
    @WebMethod
    public boolean authorizeUser(String user, String pass) {
        return getBookingApp().authorizeUser(user, pass);
    }

    @WebMethod(operationName = "createBooking")
    public Booking createBooking(Student student, Tutor tutor) {
        return getBookingApp().createBooking(student, tutor);
    }

    @WebMethod(operationName = "createBookingString")
    public Booking createBooking(String studentEmail, String tutorEmail) {
        return getBookingApp().createBookingByEmail(studentEmail, tutorEmail);
    }
    
    @WebMethod
    public ArrayList<Booking> getBookings() {
        return getBookingApp().getBookingsObject().getBookings();
    }

    @WebMethod
    public Booking getBookingByID(int ID) {
        return getBookingApp().getBookingsObject().getByID(ID);
    }

    @WebMethod
    public ArrayList<Booking> getBookingsByStudentEmail(String email) {
        return getBookingApp().getBookingsObject().getbyStudentEmail(email);
    }
    
    @WebMethod
    public ArrayList<Booking> getBookingsByStudentName(String name) {
        return getBookingApp().getBookingsObject().getByName(name);
    }

    @WebMethod
    public ArrayList<Booking> getBookingsBySubject(String subject) {
        return getBookingApp().getBookingsObject().getBySubject(subject);
    }

    @WebMethod
    public ArrayList<Booking> getBookingsByStatus(String status) {
        return getBookingApp().getBookingsObject().getByStatus(status);
    }

    @WebMethod(operationName = "cancelBookingObject")
    public boolean cancelBooking(Booking booking) {
        return getBookingApp().completeBooking(booking);
    }

    @WebMethod(operationName = "cancelBookingID")
    public boolean cancelBookingByID(int bookingId) {
        return getBookingApp().completeBookingByID(bookingId);
    }

    @WebMethod(operationName = "completeBookingObject")
    public boolean completeBooking(Booking booking) {
        return getBookingApp().completeBooking(booking);
    }

    @WebMethod(operationName = "completeBookingID")
    public boolean completeBookingByID(int bookingID) {
        return getBookingApp().completeBookingByID(bookingID);
    }
    
    @WebMethod
    public boolean isBookingOwner(Booking booking) {
        return getBookingApp().isBookingOwner(booking);
    }
    
    @WebMethod
    public boolean isBookingOwnerByID(int id) {
        return getBookingApp().isBookingOwnerByID(id);
    }

    // BONUS
    @WebMethod
    public void cancelAccount() {
        getBookingApp().cancelAccount();
    }
    
    @WebMethod
    public void editName(String name) {
        getBookingApp().editName(name);
    }
    
    @WebMethod
    public void editDOB(String dob) {
        getBookingApp().editDOB(dob);
    }

    @WebMethod
    public void editPassword(String password) {
        getBookingApp().editPassword(password);
    }    
}
