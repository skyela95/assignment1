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
import uts.wsd.Booking.StatusType;

/**
 *
 * @author Madeleine
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "bookings")
public class Bookings implements Serializable {
    
    @XmlElement(name = "booking", type = Booking.class)        
    protected ArrayList<Booking> list = new ArrayList<Booking>();


    public ArrayList<Booking> getBookings(){
        return list;
    }

    public void addBooking(Booking booking){
        list.add(booking);
    }

    public void removeBooking(Booking booking){
        list.remove(booking);
    }

    public ArrayList<Booking> getByStatus(String statType){
        ArrayList<Booking> bookings = new ArrayList<Booking>();
        for (Booking booking : list){
            if (booking.getStatusType().value().toLowerCase()
                    .equals(statType.toLowerCase())){
                bookings.add(booking);
            }
        }
        //return bookings.isEmpty() ? null : bookings;
        return bookings;
    }

    public Booking getByID(int ID){
        Booking book;
        for (Booking booking : list){
            if(booking.getBookingID() == ID){
                book = booking;
                return book;
            }
        }
        return null;
    }
    
    public ArrayList<Booking> getByName(String name) {
        ArrayList<Booking> bookings = new ArrayList<Booking>();
        for (Booking booking : list) {
            if (booking.getStudentName().contains(name)) {
                bookings.add(booking);
            }
        }
        return bookings;
    }

    
    public ArrayList<Booking> getbyStudentEmail(String email) {
        ArrayList<Booking> bookings = new ArrayList<Booking>();
        for(Booking booking : list) {
            if (booking.getStudentEmail().equals(email)) {
                bookings.add(booking);
            }
        }
        return bookings;
    }
    
    public ArrayList<Booking> getBySubject(String specialty) {
        ArrayList<Booking> bookings = new ArrayList<Booking>();
        for (Booking booking : list) {            
            if (booking.getSubjectName().value().equals(specialty)) {
                bookings.add(booking);
            }
        }
        return bookings;
    }

}