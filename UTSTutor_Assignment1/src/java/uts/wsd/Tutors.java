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
    
    //THIS METHOD TAKES A Boolean NOT boolean !! had to change so tha type could be null.
    public ArrayList<Tutor> getTutors(String name, Tutor.TutorSpecialty specialty, Boolean status){
        ArrayList<Tutor> tutors = new ArrayList<Tutor>();

        if (name != null){
           tutors.add(getTutorByName(name));
        }
        if (specialty != null){
            for (Tutor tutor : matchLists(tutors, getTutorsBySpecialty(specialty))){
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
        //int i;
        for(int i=0; i<sorting.size(); i++){
            for (Tutor tutor : main){
                if (sorting.get(i) != tutor){
                    main.add(sorting.get(i));
                    break;
                }
            }
        }
        return main;
    }
}
