/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.wsd;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * The base user class that all users are derived from. It holds the general
 * information a user may have, and all getter/setter methods
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "user")
public class User {

    // <editor-fold defaultstate="collapsed" desc=" ${Enums} ">
    // Enum for type of user, with methods to retrieve string values for
    // comparison and XML parsing
    @XmlType(name = "userTypeEnum")
    @XmlEnum
    public enum UserType {
        STUDENT,
        TUTOR;

        public String value() {
            return name();
        }

        public static UserType fromValue(String v) {
            return valueOf(v);
        }
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" ${Variables} ">
    @XmlElement(name = "name")
    protected String name;
    @XmlElement(name = "email")
    protected String email;
    @XmlElement(name = "password")
    protected String password;
    @XmlElement(name = "dateOfBirth")
    protected String dateOfBirth;
    @XmlElement(name = "userType")
    protected UserType userType;
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" ${Constructors} ">
    /**
     * Constructor with basic parameters for a base User
     *
     * @param name Users real name
     * @param email Users email, also their username
     * @param password Users password
     * @param dateOfBirth Users date of birth
     * @param userType The type of User - Student or Tutor
     */
    public User(String name, String email, String password, String dateOfBirth, UserType userType) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.userType = userType;
    }

    /**
     * Empty constructor for XML factory creation
     */
    public User() {
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" ${Getters} ">
    /**
     * @return The users full name
     */
    public String getName() {
        return name;
    }

    /**
     * @return The users email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return the user's date of birth
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @return the user's type
     */
    public UserType getUserType() {
        return userType;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" ${Setters} ">
    /**
     * @param name the users full name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param email the users email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @param password the user's password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @param dateOfBirth the user's date of birth to set
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @param userType the user's type to set
     */
    public void setUserType(UserType userType) {
        this.userType = userType;
    }
    // </editor-fold>
}
