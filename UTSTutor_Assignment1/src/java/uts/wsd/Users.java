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
public class Users {

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
