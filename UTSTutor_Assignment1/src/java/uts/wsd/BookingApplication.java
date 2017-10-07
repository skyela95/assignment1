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
import javax.servlet.ServletContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Adam
 */
public class BookingApplication {

    private final String PATH_SUFFIX = "WEB-INF/%1$s.xml";
    
    private String usersFilePath;
    private String tutorsFilePath;
    private String bookingsFilePath;
    
    //private Students students;
    private Tutors tutors;
    private Bookings bookings;
    
    public BookingApplication(ServletContext sc) {
        try {
        setPathsAndObjects(sc);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
    }
    
    private void setPathsAndObjects(ServletContext sc) throws JAXBException, IOException {
        //students = setPathAndObect(sc, studentsFilePath, Students.class);
        tutors = setPathAndObect(sc, tutorsFilePath, Tutors.class);
        bookings = setPathAndObect(sc, bookingsFilePath, Bookings.class);
        
    }
    
    private <T> T setPathAndObect(ServletContext sc, String filePath, Class<T> c) throws JAXBException, IOException {
        JAXBContext jc = JAXBContext.newInstance(c);
        Unmarshaller u = jc.createUnmarshaller();
        filePath = sc.getRealPath(String.format(PATH_SUFFIX, c.getSimpleName().toLowerCase()));
        FileInputStream fin = new FileInputStream(filePath);
        T t = (T)u.unmarshal(fin);                 
        fin.close();
        
        return t;
    }
    
    /*
    public Students getStudents() {
        return students;
    }
    
    public void setStudents(Students students) {
        this.students = students;
    }
    */
    
    public ArrayList<Tutor> getTutors() {
        return (ArrayList<Tutor>)(ArrayList<?>)tutors.getList();
    }
    
    /*
    public void setTutors(Tutors tutors) {
        this.tutors = tutors;
    }
    */

    public ArrayList<Booking> getBookings() {
        return bookings.getBookings();
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
