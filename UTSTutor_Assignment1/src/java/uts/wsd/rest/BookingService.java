/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.wsd.rest;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.xml.bind.JAXBException;
import uts.wsd.*;

/**
 *  The main REST service for the  Booking Application
 */
@Path("/bookings")
public class BookingService {

    @Context
    private ServletContext application;

    /**
     * Retrieves the BookingApplication through the REST context
     * @return ^^
     * @throws JAXBException
     * @throws IOException 
     */
    private BookingApplication getBookingApp() throws JAXBException, IOException {
        synchronized (application) {
            BookingApplication bookingApp = (BookingApplication) application.getAttribute("bookingApp");
            if (bookingApp == null) {
                bookingApp = new BookingApplication();
                bookingApp.setFilePath(application.getRealPath("\\"));
                application.setAttribute("bookingApp", bookingApp);
            }
            return bookingApp;
        }
    }
  
    /**
     * Returns all bookings in XML format
     * @return ^^
     * @throws JAXBException
     * @throws IOException
     * @throws Exception 
     */
    //http://localhost:8080/UTSTutor_Assignment1/rest/bookings/booking/
    @Path("booking")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public ArrayList<Booking> getAllBookings() throws JAXBException, IOException, Exception{
        return getBookingApp().getBookingsObject().getBookings();
    
    }
    
    /**
     * Returns a specific Booking in XML format
     * @param ID The booking ID to return
     * @return ^^
     * @throws JAXBException
     * @throws IOException
     * @throws Exception 
     */
    // http://localhost:8080/UTSTutor_Assignment1/rest/bookings/booking/bookingID?ID=1
    @Path("booking/bookingID")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Booking getBookingID(@QueryParam("ID") int ID) throws JAXBException, IOException, Exception{
        System.out.println("Getting id: " + ID);
        return getBookingApp().getBookingsObject().getByID(ID);
    }    


    /**
     * Returns a specific Booking with a student Email
     * @param email The email of the booking
     * @return ^^
     * @throws JAXBException
     * @throws IOException
     * @throws Exception 
     */
    // http://localhost:8080/UTSTutor_Assignment1/rest/bookings/booking/studEmail?email=lisa.simpson@springfield.com
    @Path("booking/studEmail")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public ArrayList<Booking> getBookingStudEmail(@QueryParam("email") String email) throws JAXBException, IOException, Exception{
        return getBookingApp().getBookingsObject().getbyStudentEmail(email);
    }    
    
    /**
     * Returns a specific Booking with a subject name
     * @param subjectName The subject name
     * @return ^^
     * @throws JAXBException
     * @throws IOException
     * @throws Exception 
     */
    //http://localhost:8080/UTSTutor_Assignment1/rest/bookings/booking/subjectName?subjectName=AppProg
    @Path("booking/subjectName")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public ArrayList<Booking> getBookingSubject(@QueryParam("subjectName") String subjectName) throws JAXBException, IOException, Exception{
        return getBookingApp().getBookingsObject().getBySubject(subjectName);
    }    
    
    
    /**
     * Returns a specific booking with a status
     * @param status the status
     * @return
     * @throws JAXBException
     * @throws IOException
     * @throws Exception 
     */
     //http://localhost:8080/UTSTutor_Assignment1/rest/bookings/booking/status?status=ACTIVE
    @Path("booking/status")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public ArrayList<Booking> getBookingStatus(@QueryParam("status") String status) throws JAXBException, IOException, Exception{
        return getBookingApp().getBookingsObject().getByStatus(status);
    }
}    
    
