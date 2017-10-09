/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.wsd;

import java.io.Serializable;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Base storage and handle class for Booking objects. Handles searching, and
 * list manipulation
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "bookings")
public class Bookings implements Serializable {

    // <editor-fold defaultstate="collapsed" desc=" ${Variables} ">
    // The list holding all Booking objects parsed from XML
    @XmlElement(name = "booking", type = Booking.class)
    protected ArrayList<Booking> list = new ArrayList<Booking>();

// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" ${Getters} ">
    /**
     * Retrieves a list of all Booking objects in the list
     *
     * @return ArrayList of Booking
     */
    public ArrayList<Booking> getBookings() {
        return list;
    }

// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" ${List Methods} ">
    /**
     * Adds the provided Booking to the list
     *
     * @param booking new Booking to add
     */
    public void addBooking(Booking booking) {
        list.add(booking);
    }

    /**
     * Removes the provided Booking from the list
     *
     * @param booking the Booking to remove
     */
    public void removeBooking(Booking booking) {
        list.remove(booking);
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" ${Search Methods} ">
    /**
     * Retrieves all Booking objects with the provided status
     *
     * @param statType The status to search for
     * @return ArrayList of Booking, or a empty/null list
     */
    public ArrayList<Booking> getByStatus(String statType) {
        ArrayList<Booking> bookings = new ArrayList<Booking>();
        for (Booking booking : list) {
            if (booking.getStatusType().value().toLowerCase()
                    .equals(statType.toLowerCase())) {
                bookings.add(booking);
            }
        }
        return bookings;
    }

    /**
     * Retrieves a Booking with the specified ID
     *
     * @param ID the ID to search for
     * @return A Booking with the ID, or null
     */
    public Booking getByID(int ID) {
        Booking book;
        for (Booking booking : list) {
            if (booking.getBookingID() == ID) {
                book = booking;
                return book;
            }
        }
        return null;
    }

    /**
     * Retrieves a list of Booking with the provided name
     *
     * @param name the name to search for
     * @return ArrayList of Booking, or an empty/null list
     */
    public ArrayList<Booking> getByName(String name) {
        ArrayList<Booking> bookings = new ArrayList<Booking>();
        for (Booking booking : list) {
            if (booking.getStudentName().contains(name)) {
                bookings.add(booking);
            }
        }
        return bookings;
    }

    /**
     * Retrieves a list of Booking with the provided student email/username
     *
     * @param email the email/username to search for
     * @return ArrayList of Booking, or empty/null list
     */
    public ArrayList<Booking> getbyStudentEmail(String email) {
        ArrayList<Booking> bookings = new ArrayList<Booking>();
        for (Booking booking : list) {
            if (booking.getStudentEmail().equals(email)) {
                bookings.add(booking);
            }
        }
        return bookings;
    }

    /**
     * Retrieves a list of Booking with the provided subject/tutor specialty
     *
     * @param specialty the subject/specialty to search for
     * @return ArrayList of Booking, or empty/null list
     */
    public ArrayList<Booking> getBySubject(String specialty) {
        ArrayList<Booking> bookings = new ArrayList<Booking>();
        for (Booking booking : list) {
            if (booking.getSubjectName().value().equals(specialty)) {
                bookings.add(booking);
            }
        }
        return bookings;
    }
// </editor-fold>

}
