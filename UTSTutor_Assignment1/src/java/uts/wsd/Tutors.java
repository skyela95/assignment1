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
 * @author Adam
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "tutors")
public class Tutors extends Users implements Serializable{
    
    //private ArrayList<Tutor> list = new ArrayList<Tutor>();
    
    @XmlElement(name = "tutor", type = Tutor.class)        
    protected ArrayList<Tutor> list = new ArrayList<Tutor>();


    public ArrayList<Tutor> getTutors(){
        return list;
    }
    
    
    public Tutor getTutorByName(String name) {
        for (User user : list) {
            Tutor tutor = (Tutor)user;
            if (tutor.getName().equals(name)) {
                return tutor;
            }
        }
        return null;
    }
    
    public Tutor getTutorByEmail(String email) {
        for (User user : list) {
            Tutor tutor = (Tutor)user;
            if (tutor.getEmail().equals(email)) {
                return tutor;
            }
        }
        return null;
    }
    
    public ArrayList<Tutor> getTutorsBySpecialty(Tutor.TutorSpecialty specialty) {
        ArrayList<Tutor> tutors = new ArrayList<Tutor>();
        
        for (User user : list) {
            Tutor tutor = (Tutor)user;
            if (tutor.getSpeciality().equals(specialty)) {
                tutors.add(tutor);
            }
        }
        return tutors.isEmpty() ? null : tutors;
    }
    
    public ArrayList<Tutor> getTutorsByAvailability(boolean status) {
        ArrayList<Tutor> tutors = new ArrayList<Tutor>();
        
            for (User user : list) {
            Tutor tutor = (Tutor)user;
            if (tutor.isAvaliable() == status) {
                tutors.add(tutor);
            }
        }
        return tutors.isEmpty() ? null : tutors;
    }
}
