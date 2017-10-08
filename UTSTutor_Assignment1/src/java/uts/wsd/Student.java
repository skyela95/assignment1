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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Adam
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "student")
public class Student extends User implements Serializable {
    
    //private ArrayList<Booking> bookings = new ArrayList<Booking>();
    
    public Student() {}
    
    public Student(String name, String email, String password, String dateOfBirth) {
        super(name, email, password, dateOfBirth, User.UserType.STUDENT);
    }
    
}
