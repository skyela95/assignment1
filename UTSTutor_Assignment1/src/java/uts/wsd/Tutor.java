/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.wsd;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Tutor derives from User, and is able to be parsed to and from XML It has
 * special variables, pointing to the tutors Specialty and their availability
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "tutor")
public class Tutor extends User implements Serializable {

    // <editor-fold defaultstate="collapsed" desc=" ${Enums} ">
    @XmlType(name = "tutorSpecialtyEnum")
    @XmlEnum
    public enum TutorSpecialty {
        WSD,
        USP,
        SEP,
        AppProg,
        MobileApp;

        public String value() {
            return name();
        }

        public static TutorSpecialty fromValue(String v) {
            return valueOf(v);
        }
    }

// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" ${Variables} ">
    @XmlElement(name = "tutorSpecialty", required = true)
    private TutorSpecialty specialty;
    @XmlElement(name = "avaliable")
    private boolean avaliable = true;
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" ${Constructors} ">
    /**
     * Base constructor for a Tutor, with base parameters sent to the base User
     * class. UserType is automatically set to Tutor
     *
     * @param name The tutors name
     * @param email The tutors email, also their username
     * @param password The tutors password
     * @param dateOfBirth The tutors date of birth
     * @param specialty The tutors subject specialty
     */
    public Tutor(String name, String email, String password, String dateOfBirth, TutorSpecialty specialty) {
        super(name, email, password, dateOfBirth, User.UserType.TUTOR);

        this.specialty = specialty;
    }

    /**
     * Empty constructor for XML factory
     */
    public Tutor() {
    }

    /**
     * @return the tutor's specialty
     */
    public TutorSpecialty getSpeciality() {
        return specialty;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" ${Getters} ">
    /**
     * @return returns the tutors specialty
     */
    public TutorSpecialty getSpecialty() {
        return specialty;
    }

    /**
     * @return the tutor's availability
     */
    public boolean isAvaliable() {
        return avaliable;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" ${Setters} ">
    /**
     * @param speciality the tutor's specialty to set
     */
    public void setSpeciality(TutorSpecialty specialty) {
        this.specialty = specialty;
    }

    /**
     * @param avaliable the tutor's availability to set
     */
    public void setAvaliable(boolean avaliable) {
        this.avaliable = avaliable;
    }
// </editor-fold>
}
