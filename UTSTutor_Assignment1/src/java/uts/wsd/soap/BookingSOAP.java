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
import uts.wsd.BookingApplication;
import uts.wsd.Student;
import uts.wsd.Tutor;
import uts.wsd.User;

/**
 *
 * The main SOAP Web-service class providing the client, and web-site with
 * access to the SOAP service for data access, handling and validation
 */
@WebService(serviceName = "bookingApp")
public class BookingSOAP {

    // The context of the web-service
    @Resource
    private WebServiceContext context;

    /**
     * The main method of the SOAP application, proving the BookingApplication
     * object with the services context. It provides the single instantiated
     * class at an application level
     *
     * @return Returns a BookingApplication object (Single - Application scope)
     */
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

    // <editor-fold defaultstate="collapsed" desc=" ${Get Methods} ">
    /**
     * Retrieves the list of tutors in the stored list, populated by XML
     *
     * @return ArrayList of all Tutor objects, or an empty list (null)
     */
    @WebMethod
    public ArrayList<Tutor> getTutors() {
        return getBookingApp().getTutorList();
    }

    /**
     * Retrieves the list of bookings in the stored list, populated by XML
     *
     * @return ArrayList of all Booking objects, or an empty list (null)
     */
    @WebMethod
    public ArrayList<Booking> getBookings() {
        return getBookingApp().getBookingsObject().getBookings();
    }

    /**
     * Retrieves a Booking object by using the provided ID
     *
     * @param ID id of the booking object
     * @return Booking object, or null
     */
    @WebMethod
    public Booking getBookingByID(int ID) {
        return getBookingApp().getBookingsObject().getByID(ID);
    }

    /**
     * Retrieves a list of bookings associated with the provided email
     *
     * @param email The parameter to filter bookings by
     * @return ArrayList of Booking objects, or an empty list (null)
     */
    @WebMethod
    public ArrayList<Booking> getBookingsByStudentEmail(String email) {
        return getBookingApp().getBookingsObject().getbyStudentEmail(email);
    }

    /**
     * Retrieves a list of bookings associated with the provided name
     *
     * @param name The parameter to filer bookings by
     * @return ArrayList of Booking objects, or an empty list (null)
     */
    @WebMethod
    public ArrayList<Booking> getBookingsByStudentName(String name) {
        return getBookingApp().getBookingsObject().getByName(name);
    }

    /**
     * Retrieves a list of bookings based on the provided subject/tutor
     * specialty .
     *
     * @param subject Subject/Tutor specialty e.g. MAD, SEP, AppProg, etc.
     * @return ArrayList of Booking objects, or an empty list (null)
     */
    @WebMethod
    public ArrayList<Booking> getBookingsBySubject(String subject) {
        return getBookingApp().getBookingsObject().getBySubject(subject);
    }

    /**
     * Retrieves a list of bookings based on the provided status
     *
     * @param status Booking status i.e. ACTIVE, CANCELLED, COMPLETED
     * @return ArrayList of Booking objects, or an empty list (null)
     */
    @WebMethod
    public ArrayList<Booking> getBookingsByStatus(String status) {
        return getBookingApp().getBookingsObject().getByStatus(status);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" ${Login Methods} ">
    /**
     * Retrieves a User object, if details are validated
     *
     * @param email The username
     * @param password The password
     * @return A User object if valid, otherwise null
     */
    @WebMethod
    public User login(String email, String password) {
        return getBookingApp().login(email, password);
    }

    /**
     * Logs the user out of the system -> Removes reference in BookingApp
     */
    @WebMethod
    public void logout() {
        getBookingApp().logout();
    }

    /**
     * Retrieves the current logged in user
     *
     * @return Returns the user object, if one is logged-in, otherwise null
     */
    @WebMethod
    public User getLoggedUser() {
        return getBookingApp().getLoggedUser();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" ${Authorize Methods} ">
    /**
     * Authorizes the user based on provided details
     *
     * @param user Username
     * @param pass Password
     * @return Returns a true/false if details match the logged in user
     */
    @WebMethod
    public boolean authorizeUser(String user, String pass) {
        return getBookingApp().authorizeUser(user, pass);
    }

    /**
     * Checks whether the logged in user is the owner of the booking
     *
     * @param booking The booking to check the owner against
     * @return True/False depending if user is owner
     */
    @WebMethod
    public boolean isBookingOwner(Booking booking) {
        return getBookingApp().isBookingOwner(booking);
    }

    /**
     * Checks whether the logged in user is owner of the booking, provided by ID
     *
     * @param id the id of the booking to check the owner against
     * @return True/False depending if user is owner
     */
    @WebMethod
    public boolean isBookingOwnerByID(int id) {
        return getBookingApp().isBookingOwnerByID(id);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" ${Booking Methods} ">
    /**
     * Retrieves a new Booking with the provided users
     *
     * @param student Student user
     * @param tutor Tutor user
     * @return A new Booking, or null
     */
    @WebMethod(operationName = "createBooking")
    public Booking createBooking(Student student, Tutor tutor) {
        return getBookingApp().createBooking(student, tutor);
    }

    /**
     * Returns a new Booking with the provided user E-mails - Mainly for client
     *
     * @param studentEmail Student username
     * @param tutorEmail Tutor username
     * @return
     */
    @WebMethod(operationName = "createBookingString")
    public Booking createBooking(String studentEmail, String tutorEmail) {
        return getBookingApp().createBookingByEmail(studentEmail, tutorEmail);
    }

    /**
     * Cancels the provided Booking
     *
     * @param booking The Booking to cancel
     * @return Returns if operation was successful
     */
    @WebMethod(operationName = "cancelBookingObject")
    public boolean cancelBooking(Booking booking) {
        return getBookingApp().completeBooking(booking);
    }

    /**
     * Cancels the booking with the provided ID
     *
     * @param bookingId The id of a Booking to cancel
     * @return Returns if operation was successful
     */
    @WebMethod(operationName = "cancelBookingID")
    public boolean cancelBookingByID(int bookingId) {
        return getBookingApp().completeBookingByID(bookingId);
    }

    /**
     * Completes the provided Booking
     *
     * @param booking The Booking to complete
     * @return Returns if operation was successful
     */
    @WebMethod(operationName = "completeBookingObject")
    public boolean completeBooking(Booking booking) {
        return getBookingApp().completeBooking(booking);
    }

    /**
     * Completes the booking with the provided ID
     *
     * @param bookingID The id of the Booking to cancel
     * @return Returns if operation was successful
     */
    @WebMethod(operationName = "completeBookingID")
    public boolean completeBookingByID(int bookingID) {
        return getBookingApp().completeBookingByID(bookingID);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" ${Account Methods} ">
    /**
     * Cancels the account of the currently logged in user
     */
    @WebMethod
    public void cancelAccount() {
        getBookingApp().cancelAccount();
    }

    /**
     * Edits the current users name to the provided name This method is invoked
     * through the client after authorization
     *
     * @param name New name for the user
     */
    @WebMethod
    public void editName(String name) {
        getBookingApp().editName(name);
    }

    /**
     * Edits the current users date of birth to the provided date This method is
     * invoked through the client after authorization
     *
     * @param dob
     */
    @WebMethod
    public void editDOB(String dob) {
        getBookingApp().editDOB(dob);
    }

    /**
     * Edits the current users password to the provided password This method is
     * invoked through the client after authorization
     *
     * @param password
     */
    @WebMethod
    public void editPassword(String password) {
        getBookingApp().editPassword(password);
    }
}    
    // </editor-fold>
