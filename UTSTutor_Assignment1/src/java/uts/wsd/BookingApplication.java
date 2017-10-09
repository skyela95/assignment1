/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.wsd;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

/**
 *
 * @author Adam
 */
public class BookingApplication {

    private final String PATH_SUFFIX = "%1$sWEB-INF/%2$s.xml";
    private String filePath;
    
    private StringBuilder studentsFilePath = new StringBuilder("");
    private StringBuilder tutorsFilePath = new StringBuilder("");
    private StringBuilder bookingsFilePath = new StringBuilder("");
    
    private Students students;
    private Tutors tutors;
    private Bookings bookings;
    
    private User loggedUser;

    
    
    public void setFilePath(String path) {
        filePath = path;
        try {
        setPathsAndObjects();
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }
    
    
    private void setPathsAndObjects() throws JAXBException, IOException {
        students = setPathAndObect(studentsFilePath, Students.class);
        tutors = setPathAndObect(tutorsFilePath, Tutors.class);
        bookings = setPathAndObect(bookingsFilePath, Bookings.class);       
    }
    
    private <T> T setPathAndObect(StringBuilder realPath, Class<T> c) throws JAXBException, IOException {
        JAXBContext jc = JAXBContext.newInstance(c);
        Unmarshaller u = jc.createUnmarshaller();
        //realPath = String.format(PATH_SUFFIX, filePath, c.getSimpleName().toLowerCase());
        realPath.append(String.format(PATH_SUFFIX, filePath, c.getSimpleName().toLowerCase()));
        FileInputStream fin = new FileInputStream(realPath.toString());
        T t = (T)u.unmarshal(fin);                 
        fin.close();
        return t;
    }

    public User login(String username, String password) {
        loggedUser = tutors.login(username, password);
        if (loggedUser != null) {
            return loggedUser;
        } else {
            loggedUser = students.login(username, password);
            return loggedUser;
        }
    }
    
    
    public void logout() {
        loggedUser = null;
    }
    
    public User getLoggedUser() {
        return loggedUser;
    }
   
    public ArrayList<Tutor> getTutorList() {
        return (ArrayList<Tutor>)(ArrayList<?>)tutors.getList();
    }
    
    public Tutors getTutorsObject() {
        return tutors;
    }

    public Bookings getBookingsObject() {
        return bookings;
    }
    
    public Students getStudentsObject() {
        return students;
    }

    public ArrayList<Booking> getBookingList() {
        return bookings.getBookings();
    }    
    
    public ArrayList<Student> getStudentList() {
        return (ArrayList<Student>)(ArrayList<?>)students.getList();
    }

    public void saveStudents() {

        saveBookingObject(students, Students.class, studentsFilePath);

    }

    public void saveTutors() {
        saveBookingObject(tutors, Tutors.class, tutorsFilePath);

    }

    public void saveBookings() {
        saveBookingObject(bookings, Bookings.class, bookingsFilePath);
    }

    private <T> void saveBookingObject(T obj, Class<T> c, StringBuilder path) {
        try {
            JAXBContext jc = JAXBContext.newInstance(c);
            Marshaller m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            FileOutputStream fout = new FileOutputStream(path.toString());
            m.marshal((T) obj, fout);
            fout.close();
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }

}