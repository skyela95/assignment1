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
 * Extended class of User, for Student users
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "student")
public class Student extends User implements Serializable {

    // <editor-fold defaultstate="collapsed" desc=" ${Constructors} ">
    /**
     * Empty constructor for XML factory
     */
    public Student() {
    }

    /**
     * Base Student constructor, with basic parameters for base User class, and
     * an automatically set UserType of Student
     *
     * @param name Students name
     * @param email Students email/username
     * @param password Students password
     * @param dateOfBirth Students date of birth
     */
    public Student(String name, String email, String password, String dateOfBirth) {
        super(name, email, password, dateOfBirth, User.UserType.STUDENT);
    }

// </editor-fold>
}
