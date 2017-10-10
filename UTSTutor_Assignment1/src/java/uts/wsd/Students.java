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
 * Extended class from Users, storage and handle class for Student objects
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "students")
public class Students extends Users implements Serializable {

    // <editor-fold defaultstate="collapsed" desc=" ${Search Methods} ">
    /**
     * Retrieves a Student with the provided email
     *
     * @param email the email to search for
     * @return Student object, or null
     */
    public Student getStudentByEmail(String email) {
        for (User user : list) {
            Student student = (Student) user;
            if (student.getEmail().equals(email)) {
                return student;
            }
        }
        return null;
    }

    /**
     * Retrieves a Student with the provided name
     *
     * @param name the name to search for
     * @return Student object, or null
     */
    public Student getStudentByName(String name) {
        for (User user : list) {
            Student student = (Student) user;
            if (student.getName().equals(name)) {
                return student;
            }
        }
        return null;
    }

// </editor-fold>
}
