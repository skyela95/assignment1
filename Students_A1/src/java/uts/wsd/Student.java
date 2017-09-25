/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.wsd;

import java.io.Serializable;

public class Student implements Serializable {
    private String email;
    private String name;
    private String password;
    private String dob;
    private String sot;

    public Student() {
    }

    public Student(String email, String name, String password, String dob, String sot) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.dob = dob;
        this.sot = sot;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
    
    public String getSot() {
        return sot;
    }
    
    public void setSot() {
        this.sot = sot;
    }
}
