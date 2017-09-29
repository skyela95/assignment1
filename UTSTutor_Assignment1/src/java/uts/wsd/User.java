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


public class User {

    
    public enum UserType {
        STUDENT,
        TUTOR
    }
    
    protected String name;
    protected String email;
    protected String password;
    protected String dateOfBirth;
    protected UserType userType;

    public User(String name, String email, String password, String dateOfBirth, UserType userType) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.userType = userType;
    }
    
        /**
     * @return The users full name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the users full name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The users email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the users email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the user's password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the user's date of birth
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth the user's date of birth to set
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return the user's type
     */
    public UserType getUserType() {
        return userType;
    }

    /**
     * @param userType the user's type to set
     */
    public void setUserType(UserType userType) {
        this.userType = userType;
    }
    
}
