/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.wsd;

import java.util.ArrayList;

/**
 *
 * @author Madeleine
 */
public class Bookings {
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

    public ArrayList<Booking> getByStatus(Booking.StatusType statType){
        ArrayList<Booking> bookings = new ArrayList<Booking>();
        for (Booking booking : list){
            if (booking.getStatusType() == statType){
                bookings.add(booking);
            }
            else{
                return null;
            }
        }
        return bookings.isEmpty() ? null : bookings;
    }

    public Booking getByID(String ID){
        Booking book = new Booking();
        for (Booking booking : list){
            if(booking.getBookingID() == ID){
                book = booking;
            }
            else{
                book = null;
            }
        }
        return book;
    }

    public Booking getbyStuEmail(String stuEmail){
        Booking book = new Booking();
        for(Booking booking : list){
            if(booking.getStudentEmail() == stuEmail){
                book =  booking;
            }
            else{
                book =  null;
            }
        }
        return book;
    }
}
