/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.wsd.soap.client;

/**
 *
 * @author Adam
 */
public class BookingClient {
    private static BookingSOAP booking;
    
    public void main(String[] args) {
        BookingApp locator = new BookingApp();
        BookingSOAP booking = locator.getBookingSOAPPort();  
    }
   
    
    public static User login(String email, String password) {        
        return booking.login(email, password);
    }
}
