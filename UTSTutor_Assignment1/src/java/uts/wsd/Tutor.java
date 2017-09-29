/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.wsd;

/**
 *
 * @author Adam
 */
public class Tutor extends User {

    public enum TutorSpecialty {
        WSD,
        USP,
        SEP,
        AppProg,
        MobileApp
    }
    
    private TutorSpecialty specialty;
    private boolean avaliable = true;
    //private Booking booking;

    public Tutor(String name, String email, String password, String dateOfBirth, TutorSpecialty specialty) {
        super(name, email, password, dateOfBirth, User.UserType.TUTOR);
        
        this.specialty = specialty;
    }
    
        /**
     * @return the tutor's specialty
     */
    public TutorSpecialty getSpeciality() {
        return specialty;
    }

    /**
     * @param speciality the tutor's specialty to set
     */
    public void setSpeciality(TutorSpecialty specialty) {
        this.specialty = specialty;
    }

    /**
     * @return the tutor's availability
     */
    public boolean isAvaliable() {
        return avaliable;
    }

    /**
     * @param avaliable the tutor's availability to set
     */
    public void setAvaliable(boolean avaliable) {
        this.avaliable = avaliable;
    }

    
}
