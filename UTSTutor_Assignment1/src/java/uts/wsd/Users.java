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
 *
 * @author Adam
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "users")
public class Users implements Serializable {

    @XmlElements({
        @XmlElement(name = "student", type = Student.class)
        ,
        @XmlElement(name = "tutor", type = Tutor.class)
    })
    protected ArrayList<User> list = new ArrayList<User>();
    
        /**
     * @return the list
     */
    public ArrayList<User> getList() {
        return list;
    }
    
    public void addUser(User user) {
        list.add(user);
    }
    
    public void removeUser(User user) {
        list.remove(user);
    }
    
    public User login(String email, String password) {
        for (User user : list) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
        
    
}
