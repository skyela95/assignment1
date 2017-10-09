/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.wsd.rest;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBException;
import uts.wsd.*;

/**
 *
 * @author Skye
 */
@Path("/tutors")
public class TutorService {
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
        
         //Since the BookingApplication has a getTutorsObject() I'll just use
         //that one
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
    
    @Path("tutor")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public ArrayList<Tutor> getAllTutors() throws JAXBException, IOException, Exception {
        return getBookingApp().getTutorsObject().getTutors();
}

    // http://localhost:8080/UTSTutor_Assignment1/rest/tutors/tutor/tutEmail?email=steve.jobs@apple.com
    @Path("tutor/tutEmail")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Tutor getTutorByEmail(@QueryParam("email") String email) throws JAXBException, IOException, Exception{
        return getBookingApp().getTutorsObject().getTutorByEmail(email);
    }    
    
     //http://localhost:8080/UTSTutor_Assignment1/rest/tutors/tutor/status?status=true
    @Path("tutor/status")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public ArrayList<Tutor> getTutorsByAvailability(@QueryParam("status") Boolean status) throws JAXBException, IOException, Exception{
        return getBookingApp().getTutorsObject().getTutorsByAvailability(status);
    }
}    