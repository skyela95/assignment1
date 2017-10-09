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
import uts.wsd.Booking.StatusType;
import uts.wsd.User.UserType;

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
    
    private User loggedUser = null;

    
    
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
    
    public boolean authorizeUser(String user, String pass) {
        return (loggedUser.getEmail().equals(user) 
                && loggedUser.getPassword().equals(pass));
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
    
    public Booking createBooking(Student student, Tutor tutor) {
        ArrayList<Booking> list = bookings.getBookings();
        int id = list.isEmpty() ? 0 : list.get(list.size() - 1).getBookingID();
        Booking booking = new Booking(id + 1,
                tutor.getEmail(), tutor.getName(),
                tutor.getSpeciality(), student.getEmail(),
                student.getName(), Booking.StatusType.ACTIVE);

        tutor.setAvaliable(false);
        saveTutors();

        bookings.addBooking(booking);
        saveBookings();

        return booking;
    }
    
    public Booking createBookingByEmail(String studentEmail, String tutorEmail) {
        Student student;
        Tutor tutor;
        if ((student = students.getStudentByEmail(studentEmail)) != null) {
            if ((tutor = tutors.getTutorByEmail(tutorEmail)) != null) {
                if (tutor.isAvaliable()) {
                    return createBooking(student, tutor);
                }
            }
        }
        return null;
    }

    public boolean cancelBooking(Booking booking) {
        if (booking != null) {
            Tutor tutor = tutors.getTutorByEmail(booking.getTutorEmail());
            tutor.setAvaliable(true);
            saveTutors();
            
            booking.cancelBooking();
            saveBookings();
            return true;
        }
        return false;
    }

    public boolean cancelBookingByID(int bookingId) {
        return cancelBooking(bookings.getByID(bookingId));
    }

    public boolean completeBooking(Booking booking) {
        if (booking != null) {
            Tutor tutor = tutors.getTutorByEmail(booking.getTutorEmail());
            tutor.setAvaliable(true);
            saveTutors();
            
            booking.completeBooking();
            saveBookings();
            return true;
        }
        return false;
    }
    
    public boolean isBookingOwner(Booking booking) {
        return (loggedUser.getEmail().equals(booking.getTutorEmail()) 
                || loggedUser.getEmail().equals(booking.getStudentEmail()));
    }
    
    public boolean isBookingOwnerByID(int id) {
        return isBookingOwner(bookings.getByID(id));
    }
    
    public void cancelAccount() {
        if (loggedUser.userType.value().equals(UserType.STUDENT.value())) {
            cancelStudentAccount();
        } else {
            cancelTutorAccount();
        }
    }
    
    private void cancelStudentAccount() {
        for (Booking booking : bookings.getBookings()) {
            Tutor tutor = tutors.getTutorByEmail(booking.getTutorEmail());
            tutor.setAvaliable(true);
            booking.cancelBooking();
        }        
        saveTutors();
        saveBookings();
        
        students.removeUser(loggedUser);
        logout();
        saveStudents();
    }
    
    private void cancelTutorAccount() {
        for (Booking booking : bookings.getByStatus(StatusType.ACTIVE.value())) {
            if (booking.getTutorEmail().equals(loggedUser.email)) {
                booking.cancelBooking();
            }
        } 
        saveBookings();
        
        tutors.removeUser(loggedUser);
        logout();
        saveTutors();
    }
    
    public boolean completeBookingByID(int bookingId) {
        return completeBooking(bookings.getByID(bookingId));
    }
    
    public ArrayList<Student> getStudentList() {
        return (ArrayList<Student>)(ArrayList<?>)students.getList();
    }
    
    public void editName(String name) {
        loggedUser.setName(name);
    }
    
    public void editDOB(String dob) {
        loggedUser.setDateOfBirth(dob);
    }
    
    public void editPassword(String password) {
        loggedUser.setPassword(password);
    }
    
    private void saveUser() {
        if (loggedUser.userType.value().equals(UserType.STUDENT.value())) {
            saveStudents();
        } else {
            saveTutors();
        }
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