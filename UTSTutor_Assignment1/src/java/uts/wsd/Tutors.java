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
 * Extended class from Users, storage and handle class for Tutor objects
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "tutors")
public class Tutors extends Users implements Serializable {

    // <editor-fold defaultstate="collapsed" desc=" ${Search Methods} ">
    /**
     * Retrieves ALL tutors from the user list
     *
     * @return ArrayList of ALL Tutors
     */
    public ArrayList<Tutor> getAll() {
        ArrayList<Tutor> tutors = new ArrayList<Tutor>();
        for (User user : list) {
            if (user.getUserType() == User.UserType.TUTOR) {
                tutors.add((Tutor) user);
            }
        }
        return tutors;
    }

    /**
     * Retrieves a list of string containing all tutor names
     *
     * @return ArrayList of names as strings
     */
    public ArrayList<String> getAllNames() {
        ArrayList<String> names = new ArrayList<String>();
        for (User user : list) {
            if (user.getUserType() == User.UserType.TUTOR) {
                names.add(user.getName());
            }
        }
        return names;
    }

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
     * Retrieves a Tutor with a given email
     *
     * @param email The email to search for
     * @return A Tutor object matching the email, or null
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
     * Retrieves a list of Tutors with the provided specialty
     *
     * @param specialty The specialty to search for
     * @return ArrayList of Tutor matching the given specialty
     */
    public ArrayList<Tutor> getTutorsBySpecialty(Tutor.TutorSpecialty specialty) {
        ArrayList<Tutor> tutors = new ArrayList<Tutor>();

        for (User user : list) {
            Tutor tutor = (Tutor) user;
            if (tutor.getSpeciality().equals(specialty)) {
                tutors.add(tutor);
            }
        }
        return tutors;
    }

    /**
     * Retrieves a list of Tutors with the provided specialty in string form
     *
     * @param specialty The specialty to search for
     * @return ArrayList of Tutor matching the given specialty
     */
    public ArrayList<Tutor> getTutorsBySpecialty(String specialty) {
        ArrayList<Tutor> tutors = new ArrayList<Tutor>();
        for (Tutor tutor : (ArrayList<Tutor>) (ArrayList<?>) list) {
            if (tutor.getSpecialty().value().equals(specialty)) {
                tutors.add(tutor);
            }
        }
        return tutors;
    }

    /**
     * Retrieves a list of Tutors matching the given name
     *
     * @param name The name to search for
     * @return ArrayList of Tutors matching the given name
     */
    public ArrayList<Tutor> getTutorsByName(String name) {
        ArrayList<Tutor> tutors = new ArrayList<Tutor>();
        for (User user : list) {
            if (user.getName().equals(name.trim())) {
                tutors.add((Tutor) user);
            }
        }
        return tutors;
    }

    /**
     * Retrieves a list of Tutors matching the given status
     *
     * @param status The status to search for (Avaliable (true) Unavailable
     * (false) )
     * @return A list of Tutor objects matching the status
     */
    public ArrayList<Tutor> getTutorsByStatus(boolean status) {
        ArrayList<Tutor> tutors = new ArrayList<Tutor>();
        for (Tutor tutor : (ArrayList<Tutor>) (ArrayList<?>) list) {
            if (tutor.isAvaliable() == status) {
                tutors.add(tutor);
            }
        }
        return tutors;
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" ${Sorting Methods} ">
    /**
     * Called by the main web-page, retrieves search results for all three
     * parameters and then compares them against each-other
     *
     * @param name The name of the tutor
     * @param specialty The specialty of the tutor
     * @param status The status of the tutor
     * @return ArrayList of Tutor objects matching the given parameters
     */
    public ArrayList<Tutor> getTutors(String name, String specialty, String status) {
        ArrayList<Tutor> tutors = new ArrayList<Tutor>();
        ArrayList<Tutor> tutorName = new ArrayList<Tutor>();
        ArrayList<Tutor> tutorSpecialty = new ArrayList<Tutor>();
        ArrayList<Tutor> tutorStatus = new ArrayList<Tutor>();

        if (name.equals("Any") && specialty.equals("Any")
                && status.equals("Any")) {
            tutors = getAll();
        } else {
            if (name != null && !name.equals("Any")) {
                tutorName = getTutorsByName(name);
            } else {
                tutorName = null;
            }

            if (specialty != null && !specialty.equals("Any")) {
                tutorSpecialty = getTutorsBySpecialty(specialty);
            } else {
                tutorSpecialty = null;
            }

            if (status != null && !status.equals("Any")) {
                boolean stat = status.equals("Available") ? true : false;
                tutorStatus = getTutorsByStatus(stat);
            } else {
                tutorStatus = null;
            }

            tutors = matchLists(tutorName, tutorSpecialty);
            tutors = matchLists(tutors, tutorStatus);
        }
        return tutors;
    }

    /**
     * Compares two lists against each-other. Null lists are discarded, and
     * before comparison begins, the biggest list is selected as the main, with
     * the smaller list being the comparison list
     *
     * @param listOne The first list to compare
     * @param listTwo The second list to compare
     * @return ArrayList of Tutor objects resulting from the comparison
     */
    private ArrayList<Tutor> matchLists(ArrayList<Tutor> listOne, ArrayList<Tutor> listTwo) {
        if (listOne == null) {
            return listTwo;
        } else if (listTwo == null) {
            return listOne;
        }
        ArrayList<Tutor> main = listOne.size() >= listTwo.size() ? listOne : listTwo;
        ArrayList<Tutor> comparison = listTwo.size() <= listOne.size() ? listTwo : listOne;

        ArrayList<Tutor> returning = new ArrayList<Tutor>();
        for (Tutor tutor : main) {
            if (comparison.contains(tutor)) {
                returning.add(tutor);
            }
        }
        return returning;
    }

    // </editor-fold>
}
