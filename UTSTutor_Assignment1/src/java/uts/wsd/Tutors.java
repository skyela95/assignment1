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

    public Tutor getTutorByEmail(String email) {
        for (User user : list) {
            Tutor tutor = (Tutor) user;
            if (tutor.getEmail().equals(email)) {
                return tutor;
            }
        }
        return null;
    }

    public ArrayList<Tutor> getTutorsBySpecialty(Tutor.TutorSpecialty specialty) {
        ArrayList<Tutor> tutors = new ArrayList<Tutor>();

        for (User user : list) {
            Tutor tutor = (Tutor) user;
            if (tutor.getSpeciality().equals(specialty)) {
                tutors.add(tutor);
            }
        }
        return tutors;
        //return tutors.isEmpty() ? null : tutors;
    }

    public ArrayList<Tutor> getTutorsByAvailability(boolean status) {
        ArrayList<Tutor> tutors = new ArrayList<Tutor>();

        for (User user : list) {
            Tutor tutor = (Tutor) user;
            if (tutor.isAvaliable() == status) {
                tutors.add(tutor);
            }
        }
        return tutors;
        //return tutors.isEmpty() ? null : tutors;
    }

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
    
    public ArrayList<Tutor> getTutors(Tutor.TutorSpecialty specialty, Boolean status){
        ArrayList<Tutor> tutors = new ArrayList<Tutor>();
        for (User user : list){
            Tutor tutor = (Tutor)user;
            if(tutor.getSpecialty() == specialty){
                if(tutor.isAvaliable() == status){
                    tutors.add(tutor);
                }
            }
        }
        return tutors;
    }
    
    public ArrayList<Tutor> getTutors(String name, Tutor.TutorSpecialty specialty){
        ArrayList<Tutor> tutors = new ArrayList<Tutor>();
        Tutor tutor = getTutorByName(name);
        if(tutor!=null)
        {
            if(tutor.getSpecialty()==specialty)
            {
            tutors.add(tutor);
            }
        }
        return tutors;
    }
    
    public ArrayList<Tutor> getTutors(String name, Boolean status){
        ArrayList<Tutor> tutors = new ArrayList<Tutor>();
        Tutor tutor = getTutorByName(name);
        if(tutor!=null)
        {
            if(tutor.isAvaliable()==status)
            {
            tutors.add(tutor);
            }
        }
        return tutors;
    }
    
    public ArrayList<Tutor> getTutorsByName(String name) {
        ArrayList<Tutor> tutors = new ArrayList<Tutor>();
        for (User user : list) {
            if (user.getName().equals(name.trim())) {
                tutors.add((Tutor) user);
            }
        }
        return tutors;
    }

    public ArrayList<Tutor> getTutorsBySpecialty(String specialty) {
        ArrayList<Tutor> tutors = new ArrayList<Tutor>();
        for (Tutor tutor : (ArrayList<Tutor>) (ArrayList<?>) list) {
            if (tutor.getSpecialty().value().equals(specialty)) {
                tutors.add(tutor);
            }
        }
        return tutors;
    }

    public ArrayList<Tutor> getTutorsByStatus(boolean status) {
        ArrayList<Tutor> tutors = new ArrayList<Tutor>();
        for (Tutor tutor : (ArrayList<Tutor>) (ArrayList<?>) list) {
            if (tutor.isAvaliable() == status) {
                tutors.add(tutor);
            }
        }
        return tutors;
    }
    
    //THIS METHOD TAKES A Boolean NOT boolean !! had to change so tha type could be null.
    public ArrayList<Tutor> getTutors(String name, String specialty, String status){
        ArrayList<Tutor> tutors = new ArrayList<Tutor>();
        //Tutor tutor = getTutorByName(name);
               System.out.println("GetTutor Name:" + name);
            System.out.println("GetTutor Specialty:" +  specialty);
            System.out.println("GetTutor status:" +  status);
            ArrayList<Tutor> tutorName = new ArrayList<Tutor>();
            ArrayList<Tutor> tutorSpecialty = new ArrayList<Tutor>();
            ArrayList<Tutor> tutorStatus = new ArrayList<Tutor>();
            
            if (name != null && !name.equals("Any")) {
                tutorName = getTutorsByName(name);
            }
            if (specialty != null && !specialty.equals("Any")) {
                tutorSpecialty = getTutorsBySpecialty(specialty);
            }
            if (status != null && status != "Any") {
                boolean stat = status.equals("Available") ? true : false;
                tutorStatus = getTutorsByStatus(stat);
            }
            
            tutors = matchLists(tutorName, tutorSpecialty);
            tutors = matchLists(tutors, tutorStatus);
            
        
        return tutors;
    }
    
    public ArrayList<Tutor> matchLists(ArrayList<Tutor> main, ArrayList<Tutor> sorting){
        if (main.isEmpty()) {
            return sorting;
        } else if (sorting.isEmpty()) {
            return main;
        }
        ArrayList<Tutor> returning = main;
        for(Tutor tutor : sorting){
            if(!main.contains(tutor)){
                returning.add(tutor);
            }
        }
        return returning;
    }
}
