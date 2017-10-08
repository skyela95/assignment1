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
    
    private String studentsFilePath;
    private String tutorsFilePath;
    private String bookingsFilePath;
    
    private Students students;
    private Tutors tutors;
    private Bookings bookings;

    
    
    public void setFilePath(String path) {
        filePath = path;
        try {
        setPathsAndObjects();
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }
    
    
    private void setPathsAndObjects() throws JAXBException, IOException {
        //System.out.println("Initialising Students...");
        //students = setPathAndObect(sc, studentsFilePath, Students.class);
        System.out.println("Initialising Tutors...");
        tutors = setPathAndObect(tutorsFilePath, Tutors.class);
        System.out.println("Initialising Bookings...");
        bookings = setPathAndObect(bookingsFilePath, Bookings.class);       
    }
    
    private <T> T setPathAndObect(String realPath, Class<T> c) throws JAXBException, IOException {
        JAXBContext jc = JAXBContext.newInstance(c);
        Unmarshaller u = jc.createUnmarshaller();
        System.out.println("Test2: " + String.format(PATH_SUFFIX, filePath, c.getSimpleName().toLowerCase()));
        realPath = String.format(PATH_SUFFIX, filePath, c.getSimpleName().toLowerCase());
        FileInputStream fin = new FileInputStream(realPath);
        T t = (T)u.unmarshal(fin);                 
        fin.close();
        return t;
    }

    public User login(String username, String password) {
        User user;
        user = tutors.login(username, password);
        if (user != null) {
            System.out.println("Found user (Tutor)");
            return user;
        } else {
            user = students.login(username, password);
            return user;
        }
    }

    

    /*
    public void setStudents(Students students) {
        this.students = students;
    }
    */
    
    public ArrayList<Tutor> getTutorList() {
        return (ArrayList<Tutor>)(ArrayList<?>)tutors.getList();
    }
    
    /*
    public void setTutors(Tutors tutors) {
        this.tutors = tutors;
    }
    */
    
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
    
    /*
    public void setBookings(Bookings bookings) {
        this.bookings = bookings;
    }
    */
    
    public void saveTutors() throws JAXBException, IOException {
        saveBookingObject(tutors, Tutors.class, tutorsFilePath);
    }
    
    public void saveBookings() throws JAXBException, IOException {
        saveBookingObject(bookings, Bookings.class, bookingsFilePath);
    }
    
    private <T> void saveBookingObject(T obj, Class<T> c, String path) throws JAXBException, IOException {
        JAXBContext jc = JAXBContext.newInstance(c);
        Marshaller m = jc.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        
        FileOutputStream fout = new FileOutputStream(path);
        m.marshal(obj, fout);
        fout.close();
    }
    
    
}