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

@Path("/bookings")
public class BookingService {

    @Context
    private ServletContext application;

    private BookingApplication getBookingApp() throws JAXBException, IOException {
        // The web server can handle requests from different clients in parallel.
        // These are called "threads".
        //
        // We do NOT want other threads to manipulate the application object at the same
        // time that we are manipulating it, otherwise bad things could happen.
        //
        // The "synchronized" keyword is used to lock the application object while
        // we're manpulating it.
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

    //@Path("bookings")
    //@GET
    //@Produces(MediaType.APPLICATION_XML)
    //public ArrayList<Booking> getBookings() throws JAXBException, IOException {
    //    ArrayList<Booking> bookings = getBookingApp().getBookingList();
    //    return bookings;
    //}

    
    
    // http://localhost:8080/UTSTutor_Assignment1/rest/bookings/studEmail?email=lisa.simpson@springfield.com
    @Path("ID")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Booking getBookingID(@QueryParam("ID") int ID) throws JAXBException, IOException, Exception{
        System.out.println("Getting id: " + ID);
        return getBookingApp().getBookingsObject().getByID(ID);
    }    


    
    // http://localhost:8080/UTSTutor_Assignment1/rest/bookings/studEmail?email=lisa.simpson@springfield.com
    @Path("studEmail")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public ArrayList<Booking> getBookingStudEmail(@QueryParam("email") String email) throws JAXBException, IOException, Exception{
        return getBookingApp().getBookingsObject().getbyStudentEmail(email);
    }    
}