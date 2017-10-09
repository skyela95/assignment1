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
import javax.jws.WebParam;
import javax.servlet.ServletContext;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import uts.wsd.BookingApplication;
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
        System.out.println("Getting Booking App...");
        ServletContext application = (ServletContext)context.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
        BookingApplication bookingApp = (BookingApplication)application.getAttribute("bookingApp");
        if (bookingApp == null) {
            System.out.println("Instantiating new Booking App...");
            //bookingApp = new BookingApplication(application);
            bookingApp = new BookingApplication();
            bookingApp.setFilePath(application.getRealPath("\\"));
        }
        application.setAttribute("bookingApp", bookingApp);
        
        return bookingApp;
    }
    
    @WebMethod
    public ArrayList<Tutor> fetchTutors() {
        return getBookingApp().getTutorList();
    }
    
    public User login(String email, String password) {
        /*
        User user;
        user = getBookingApp().getTutorsObject().login(email, password);
        if (user != null) {
            return user;
        } else {
            user = getBookingApp().getStudentsObject().login(email, password); 
            return user;
        }
        */
        return getBookingApp().login(email, password);
    }
}
