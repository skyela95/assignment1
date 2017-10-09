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

    @WebMethod(operationName = "createBooking")
    public Booking createBooking(Student student, Tutor tutor) {
        BookingApplication bookingApp = getBookingApp();
        Bookings bookings = getBookingApp().getBookingsObject();
        ArrayList<Booking> list = bookings.getBookings();

        int id = list.size() == 0 ? 0 : list.get(list.size() - 1).getBookingID();
        Booking booking = new Booking(id + 1,
                tutor.getEmail(), tutor.getName(),
                tutor.getSpeciality(), student.getEmail(),
                student.getName(), StatusType.ACTIVE);

        tutor.setAvaliable(false);
        bookingApp.saveTutors();

        bookings.addBooking(booking);
        bookingApp.saveBookings();

        return booking;
    }

    @WebMethod(operationName = "createBookingString")
    public Booking createBooking(String studentUser, String tutorUser) {
        BookingApplication bookingApp = getBookingApp();
        Student student;
        Tutor tutor;
        if ((student = bookingApp.getStudentsObject().getStudentByEmail(studentUser)) != null) {
            System.out.println("Student Found");
            if ((tutor = bookingApp.getTutorsObject().getTutorByEmail(tutorUser)) != null) {
                System.out.println("Tutor Found");
                if (tutor.isAvaliable()) {
                    System.out.println("Tutor is avaliable");
                    return createBooking(student, tutor);
                }
            }
        }
        return null;
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
    public ArrayList<Booking> getBookingsBySubject(String subject) {
        return getBookingApp().getBookingsObject().getBySubject(subject);
    }

    @WebMethod
    public ArrayList<Booking> getBookingsByStatus(String status) {
        return getBookingApp().getBookingsObject().getByStatus(status);
    }

    @WebMethod(operationName = "cancelBookingObject")
    public void cancelBooking(Booking booking) {
        BookingApplication bookingApp = getBookingApp();

        if (booking != null) {
            Tutor tutor = bookingApp.getTutorsObject().getTutorByEmail(booking.getTutorEmail());
            tutor.setAvaliable(true);
            booking.cancelBooking();
        }
    }

    @WebMethod(operationName = "cancelBookingID")
    public void cancelBooking(int ID) {
        BookingApplication bookingApp = getBookingApp();
        Bookings bookings = getBookingApp().getBookingsObject();
        Booking booking = bookings.getByID(ID);

        cancelBooking(booking);
    }

    @WebMethod(operationName = "completeBookingObject")
    public void completeBooking(Booking booking) {
        BookingApplication bookingApp = getBookingApp();

        if (booking != null) {
            Tutor tutor = bookingApp.getTutorsObject().getTutorByEmail(booking.getTutorEmail());
            tutor.setAvaliable(true);
            booking.completeBooking();
        }
    }

    @WebMethod(operationName = "completeBookingID")
    public void completeBooking(int bookingID) {
        BookingApplication bookingApp = getBookingApp();
        Bookings bookings = getBookingApp().getBookingsObject();
        Booking booking = bookings.getByID(bookingID);

        completeBooking(booking);
    }

    // BONUS
    public void cancelUserAccount(User user) {

    }

    public void updateTutorAccount() {

    }

    public void updateStudentAccount() {

    }
}
