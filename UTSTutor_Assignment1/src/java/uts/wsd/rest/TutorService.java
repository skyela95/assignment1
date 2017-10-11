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
 * Provides a service to access Tutor related searches using REST
 */
@Path("/tutors")
public class TutorService {

    @Context
    private ServletContext application;

    /**
     * Returns the BookingApplication using the REST service, for tutor related
     * searches
     *
     * @return
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
     * Returns all tutors in XML format
     *
     * @return
     * @throws JAXBException
     * @throws IOException
     * @throws Exception
     */
    @Path("tutor")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public ArrayList<Tutor> getAllTutors() throws JAXBException, IOException, Exception {
        return getBookingApp().getTutorsObject().getAll();
    }

    /**
     * Returns a Tutor in XML format based on the given email
     * @param email
     * @return
     * @throws JAXBException
     * @throws IOException
     * @throws Exception 
     */
    // http://localhost:8080/UTSTutor_Assignment1/rest/tutors/tutor/tutEmail?email=steve.jobs@apple.com
    @Path("tutor/tutEmail")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Tutor getTutorByEmail(@QueryParam("email") String email) throws JAXBException, IOException, Exception {
        return getBookingApp().getTutorsObject().getTutorByEmail(email);
    }

    /**
     * Returns a Tutor in XML based on the given status
     * @param status
     * @return
     * @throws JAXBException
     * @throws IOException
     * @throws Exception 
     */
    //http://localhost:8080/UTSTutor_Assignment1/rest/tutors/tutor/status?status=true
    @Path("tutor/status")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public ArrayList<Tutor> getTutorsByAvailability(@QueryParam("status") Boolean status) throws JAXBException, IOException, Exception {
        return getBookingApp().getTutorsObject().getTutorsByStatus(status);
    }
}
