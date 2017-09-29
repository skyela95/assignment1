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
public class Tutors extends Users {
    
    //private ArrayList<Tutor> list = new ArrayList<Tutor>();
    
    public Tutor getTutorByName(String name) {
        for (User user : list) {
            Tutor tutor = (Tutor)user;
            if (tutor.getName().equals(name)) {
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
