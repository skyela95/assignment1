/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.wsd;

import java.util.ArrayList;

/**
 *
 * @author Adam
 */
public class Student extends User {
    
    //private ArrayList<Booking> bookings = new ArrayList<Booking>();
    
    public Student(String name, String email, String password, String dateOfBirth) {
        super(name, email, password, dateOfBirth, User.UserType.STUDENT);
    }
    
}
