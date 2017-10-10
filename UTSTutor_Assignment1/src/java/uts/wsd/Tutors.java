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
 * Extended from Users base class - storage and handling for Tutor objects
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "tutors")
public class Tutors extends Users implements Serializable {

    // <editor-fold defaultstate="collapsed" desc=" ${Search Methods} ">
    /**
     * Retrieves a Tutor with the given name
     *
     * @param name The name of the tutor to search for
     * @return Tutor, or null if not found
     */
    public Tutor getTutorByName(String name) {
        for (User user : list) {
            Tutor tutor = (Tutor) user;
            if (tutor.getName().equals(name)) {
                return tutor;
            }
        }
        return null;
    }

    /**
     * Retrieves a Tutor with the given email/username
     *
     * @param email The email/username of the tutor to search for
     * @return Tutor, or null if not found
     */
    public Tutor getTutorByEmail(String email) {
        for (User user : list) {
            Tutor tutor = (Tutor) user;
            if (tutor.getEmail().equals(email)) {
                return tutor;
            }
        }
        return null;
    }

    /**
     * Retrieves a list of Tutor that have the given specialty
     *
     * @param specialty The specialty to search for
     * @return ArrayList of Tutor, or null
     */
    public ArrayList<Tutor> getTutorsBySpecialty(Tutor.TutorSpecialty specialty) {
        ArrayList<Tutor> tutors = new ArrayList<Tutor>();

        for (User user : list) {
            Tutor tutor = (Tutor) user;
            if (tutor.getSpeciality().equals(specialty)) {
                tutors.add(tutor);
            }
        }
        return tutors.isEmpty() ? null : tutors;
    }

    /**
     * Retrieves a list of Tutor that have the given availability
     *
     * @param status The status to search for
     * @return ArrayList of Tutor, or null
     */
    public ArrayList<Tutor> getTutorsByAvailability(boolean status) {
        ArrayList<Tutor> tutors = new ArrayList<Tutor>();

        for (User user : list) {
            Tutor tutor = (Tutor) user;
            if (tutor.isAvaliable() == status) {
                tutors.add(tutor);
            }
        }
        return tutors.isEmpty() ? null : tutors;
    }
// </editor-fold>  

    public ArrayList<Tutor> getAll(){
       ArrayList<Tutor> tutors = new ArrayList<Tutor>();
       for (User user : list){
           if (user.getUserType() == User.UserType.TUTOR){
               tutors.add((Tutor)user);
}
       }
       return tutors;
    }
    
    public ArrayList<String> getAllNames(){
        ArrayList<String> names = new ArrayList<String>();
        for (User user : list){
            if(user.getUserType() == User.UserType.TUTOR){
                names.add(user.getName());
            }
        }
        return names;
    }
    
    //THIS METHOD TAKES A Boolean NOT boolean !! had to change so tha type could be null.
    public ArrayList<Tutor> getTutors(String name, Tutor.TutorSpecialty specialty, Boolean status){
        ArrayList<Tutor> tutors = new ArrayList<Tutor>();

        if (name != null){
           tutors.add(getTutorByName(name));
        }
        if (specialty != null){
            ArrayList<Tutor> toMatch = matchLists(tutors, getTutorsBySpecialty(specialty));
            for (Tutor tutor : toMatch){
                tutors.add(tutor);
            }
        }
        if (status != null){
            for (Tutor tutor : matchLists(tutors, getTutorsByAvailability(status))){
                tutors.add(tutor);
            }
        }
        return tutors; 
    }
    
    public ArrayList<Tutor> matchLists(ArrayList<Tutor> main, ArrayList<Tutor> sorting){
        ArrayList<Tutor> returning = main;
        for(Tutor tutor : sorting){
            if(!main.contains(tutor)){
                returning.add(tutor);
            }
        }
        return returning;
    }
}
