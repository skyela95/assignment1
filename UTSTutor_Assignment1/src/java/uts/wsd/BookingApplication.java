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
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import uts.wsd.Booking.StatusType;
import uts.wsd.User.UserType;

/**
 * The main BookingApplication which handles the data to and from the XML files.
 * The class is build to handle many different files in the same context
 * directory, so extra types of users can be easily handled by the system.
 *
 * The class is focused on doing all the operations required by UTSTutor, away
 * from the services, so data access and handling is hidden
 */
public class BookingApplication {

    // <editor-fold defaultstate="collapsed" desc=" ${Variables} ">
    // Format for accessing the XML files
    private final String PATH_SUFFIX = "%1$sWEB-INF/%2$s.xml";
    // Context/Application file path
    private String filePath;

    // StringBuilder's for storing indivudual XML filepaths
    private StringBuilder studentsFilePath = new StringBuilder("");
    private StringBuilder tutorsFilePath = new StringBuilder("");
    private StringBuilder bookingsFilePath = new StringBuilder("");

    // Objects to store information and manipulate XML data
    private Students students;
    private Tutors tutors;
    private Bookings bookings;

    // Current logged in user
    private User loggedUser = null;

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" ${Initialization} ">
    /**
     * Sets the context/application filepath and calls a method to initialize
     * all the storage objects using that real path
     *
     * @param path real path of the context/application passed by a service
     */
    public void setFilePath(String path) {
        filePath = path;
        try {
            setPathsAndObjects();
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Calls the setPathAndObject method on all storage/list objects in the
     * class, so they have a real path, and parse XML into their lists
     *
     * @throws JAXBException
     * @throws IOException
     */
    private void setPathsAndObjects() throws JAXBException, IOException {
        students = setPathAndObect(studentsFilePath, Students.class);
        tutors = setPathAndObect(tutorsFilePath, Tutors.class);
        bookings = setPathAndObect(bookingsFilePath, Bookings.class);
    }

    /**
     * Main class to initialize a storage/list object such as Students etc. It
     * is a generic method, so it will work with any type of class that is in a
     * similar form to the other objects contain herein
     *
     * @param <T> The type of class e.g. Students, Tutors, Bookings, etc.
     * @param realPath The real path of the class, using application path
     * @param c The class of the passed object, e.g. Tutors.class
     * @return Returns a newly initialized object containing XML parsed elements
     * @throws JAXBException
     * @throws IOException
     */
    private <T> T setPathAndObect(StringBuilder realPath, Class<T> c) throws JAXBException, IOException {
        JAXBContext jc = JAXBContext.newInstance(c);
        Unmarshaller u = jc.createUnmarshaller();
        realPath.append(String.format(PATH_SUFFIX, filePath, c.getSimpleName().toLowerCase()));
        FileInputStream fin = new FileInputStream(realPath.toString());
        T t = (T) u.unmarshal(fin);
        fin.close();
        return t;
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" ${Saving} ">
    /**
     * Takes any storage object (e.g. Students) and saves it to its relevant XML
     * file. It is a generic method designed to take in any object to save into
     * its XML counter part, the reverse of the initializing method at the
     * beginning
     *
     * @param <T> The type of class to work with
     * @param obj The list/storage object to parse into XML
     * @param c The class of the storage object e.g. Students
     * @param path The real filepath of the object
     */
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

    /**
     * Checks to see the logged in users type, and calls the relevant save
     * method
     */
    private void saveUser() {
        if (loggedUser.userType.value().equals(UserType.STUDENT.value())) {
            saveStudents();
        } else {
            saveTutors();
        }
    }

    /**
     * Saves the current Students object into the students.xml file
     */
    public void saveStudents() {
        saveBookingObject(students, Students.class, studentsFilePath);
    }

    /**
     * Saves the current Tutors object into the tutors.xml file
     */
    public void saveTutors() {
        saveBookingObject(tutors, Tutors.class, tutorsFilePath);
    }

    /**
     * Saves the current Booking object into the bookings.xml file
     */
    public void saveBookings() {
        saveBookingObject(bookings, Bookings.class, bookingsFilePath);
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" ${Get Methods} ">
    /**
     * Logs the current user out of the application
     */
    /**
     * Retrieves the currently logged in User
     *
     * @return Returns the logged in User, or null
     */
    public User getLoggedUser() {
        return loggedUser;
    }

    /**
     * Retrieves the list of Booking found within the Bookings object
     *
     * @return ArrayList of Booking
     */
    public ArrayList<Booking> getBookingList() {
        return bookings.getBookings();
    }

    /**
     * Retrieves a list of Users from Tutors, casting it as a list of Tutor
     *
     * @return ArrayList of Tutor
     */
    public ArrayList<Tutor> getTutorList() {
        return (ArrayList<Tutor>) (ArrayList<?>) tutors.getList();
    }

    /**
     * Retrieves a list of Student found within the Students object
     *
     * @return ArrayList of Student - All
     */
    public ArrayList<Student> getStudentList() {
        return (ArrayList<Student>) (ArrayList<?>) students.getList();
    }

    /**
     * Retrieves the Tutors storage/handle object
     *
     * @return The instance of Tutors
     */
    public Tutors getTutorsObject() {
        return tutors;
    }

    /**
     * Retrieves the Booking storage/handle object
     *
     * @return The instance of Bookings
     */
    public Bookings getBookingsObject() {
        return bookings;
    }

    /**
     * Retrieves the Students storage/handle object
     *
     * @return The instance of Students
     */
    public Students getStudentsObject() {
        return students;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" ${Login Methods} ">
    public void logout() {
        loggedUser = null;
    }

    /**
     * Logs the user in by checking their username and password. If correct, the
     * user is returned, and also set in this class
     *
     * @param username The username
     * @param password The password
     * @return Returns the logged in user, either a User or null
     */
    public User login(String username, String password) {
        loggedUser = tutors.login(username, password);
        if (loggedUser != null) {
            return loggedUser;
        } else {
            loggedUser = students.login(username, password);
            return loggedUser;
        }
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" ${Authorize Methods} ">
    /**
     * Attempts to authorize the current user, checking the provided username
     * and password against those of the currently logged in user
     *
     * @param user The username to check with
     * @param pass The password to check with
     * @return
     */
    public boolean authorizeUser(String user, String pass) {
        return (loggedUser.getEmail().equals(user)
                && loggedUser.getPassword().equals(pass));
    }

    /**
     * Checks whether the current logged in user is the owner/part of the
     * provided Booking
     *
     * @param booking The booking to check against
     * @return Returns true/false depending if user is owner
     */
    public boolean isBookingOwner(Booking booking) {
        return (loggedUser.getEmail().equals(booking.getTutorEmail())
                || loggedUser.getEmail().equals(booking.getStudentEmail()));
    }

    /**
     * Finds booking by ID, and calls isBookingOwner with the booking object
     *
     * @param id The booking to check against
     * @return Returns true/false depending if user is owner
     */
    public boolean isBookingOwnerByID(int id) {
        return isBookingOwner(bookings.getByID(id));
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" ${Booking Methods} ">
    /**
     * Creates a new booking with the provided Student and Tutor objects, makes
     * the tutor unavailable and saves Tutors and Bookings.
     *
     * @param student The student object
     * @param tutor The tutor object
     * @return A new booking, or null if not created
     */
    public Booking createBooking(Student student, Tutor tutor) {
        if (tutor.isAvaliable()) {
            ArrayList<Booking> list = bookings.getBookings();
            // If list is empty, id = 0, otherwise it takes the last element id
            // and adds +1 to it
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
        return null;
    }

    /**
     * Creates a new booking with the provided student email and tutor email. It
     * finds the appropriate student and tutor objects, and calls the other
     * createBooking class
     *
     * @param studentEmail
     * @param tutorEmail
     * @return A new booking, or null if not created
     */
    public Booking createBookingByEmail(String studentEmail, String tutorEmail) {
        Student student;
        Tutor tutor;
        if ((student = students.getStudentByEmail(studentEmail)) != null) {
            if ((tutor = tutors.getTutorByEmail(tutorEmail)) != null) {
                return createBooking(student, tutor);
            }
        }
        return null;
    }

    /**
     * Cancels a given booking, resetting a tutor to avaliable and saves
     * relevant lists to XML
     *
     * @param booking The booking to cancel
     * @return Returns true/false if operation was successful
     */
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

    /**
     * Finds the id of the booking, and calls cancelBooking with the Booking
     * object
     *
     * @param bookingId the id of the booking to locate
     * @return Returns true/false if operation was successful
     */
    public boolean cancelBookingByID(int bookingId) {
        return cancelBooking(bookings.getByID(bookingId));
    }

    /**
     * Completes a given booking, resetting tutor to avaliable and saves
     * relevant lists to XML
     *
     * @param booking The booking to complete
     * @return Returns true/false if operation was successful
     */
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

    /**
     * Finds the booking by ID and calls the completeBooking method with the
     * Booking object
     *
     * @param bookingId The id of the booking to find
     * @return Returns true/false depending on operation success
     */
    public boolean completeBookingByID(int bookingId) {
        return completeBooking(bookings.getByID(bookingId));
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" ${Account Methods} ">
    /**
     * Calls to cancel account, checking the users UserType to see which method
     * to run
     */
    public void cancelAccount() {
        if (loggedUser.userType.value().equals(UserType.STUDENT.value())) {
            cancelStudentAccount();
        } else {
            cancelTutorAccount();
        }
    }

    /**
     * Cancels/delete the account if the user is a Student. It sets all bookings
     * to CANCELLED, and sets all relevant tutors to Avaliable. All relevant XML
     * files are updated. Logs the user out at the end
     */
    private void cancelStudentAccount() {
        for (Booking booking : bookings.getbyStudentEmail(loggedUser.getEmail())) {
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

    /**
     * Cancels/delete the account if the user is a Tutor. It sets all bookings
     * to CANCELLED, and updates the XML files with the changes. Logs user out
     * at the end.
     */
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

    /**
     * Edits the current users name to the String parameter
     *
     * @param name The new name
     */
    public void editName(String name) {
        loggedUser.setName(name);
        saveUser();
    }

    /**
     * Edits the current users date of birth to the String parameter
     *
     * @param dob the new date of birth
     */
    public void editDOB(String dob) {
        loggedUser.setDateOfBirth(dob);
        saveUser();
    }

    /**
     * Edits the current users password to the String parameter
     *
     * @param password the new password
     */
    public void editPassword(String password) {
        loggedUser.setPassword(password);
        saveUser();
    }
    
    public void editEmail(String email) {
        loggedUser.setEmail(email);
        saveUser();
    }
}
// </editor-fold>}
