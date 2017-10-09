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
 *
 * @author Skye
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "students")
public class Students extends Users implements Serializable {
    
        public Student getStudentByEmail(String email) {
        for (User user : list) {
            Student student = (Student)user;
            if (student.getEmail().equals(email)) {
                return student;
            }
        }
        return null;
    }
    
    
    public Student getStudentByName(String name) {
        for (User user : list) {
            Student student = (Student)user;
            if (student.getName().equals(name)) {
                return student;
            }
        }
        return null;
    }

}