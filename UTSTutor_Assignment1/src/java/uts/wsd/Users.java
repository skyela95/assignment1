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

import javax.xml.bind.annotation.XmlElements;

import javax.xml.bind.annotation.XmlRootElement;



/**

 * Base class for storage and handle object for the User objects in the application

 */

@XmlAccessorType(XmlAccessType.FIELD)

@XmlRootElement(name = "users")

public class Users implements Serializable {



    // <editor-fold defaultstate="collapsed" desc=" ${Variables} ">

    // List of User objects of type User - XML parses as different classes

    // depending on sub-class

    @XmlElements({

        @XmlElement(name = "student", type = Student.class)

        ,

        @XmlElement(name = "tutor", type = Tutor.class)

    })

    protected ArrayList<User> list = new ArrayList<User>();



// </editor-fold>

    

    // <editor-fold defaultstate="collapsed" desc=" ${Getters} ">

    /**

     * @return the list of User

     */

    public ArrayList<User> getList() {

        return list;

    }



// </editor-fold>

    

    // <editor-fold defaultstate="collapsed" desc=" ${List Methods} ">

    /**

     * Adds the provided user to the list

     *

     * @param user

     */

    public void addUser(User user) {

        list.add(user);

    }



    /**

     * Removes the provided user from the list

     *

     * @param user

     */

    public void removeUser(User user) {

        list.remove(user);

    }

// </editor-fold>



    // <editor-fold defaultstate="collapsed" desc=" ${Login Methods} ">

    /**

     * Checks the provided details against those of the users in the list if

     * both matched, the matched User is returned

     *

     * @param email The users username

     * @param password The users password

     * @return User with matching details, or null

     */

    public User login(String email, String password) {

        for (User user : list) {

            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {

                return user;

            }

        }

        return null;

    }



// </editor-fold>

}