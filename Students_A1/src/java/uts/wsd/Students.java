/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.wsd;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Skye
 */
public class Students implements Serializable {

    private ArrayList<Student> list = new ArrayList<Student>();

    public ArrayList<Student> getList() {
        return list;
    }

    public void addStudent(Student student) {
        list.add(student);
    }

    public void removeStudent(Student student) {
        list.remove(student);
    }

    public Student login(String email, String password) {
        for (Student student : list) {
            if (student.getEmail().equals(email) && student.getPassword().equals(password)) {
                return student;
            }
        }
        return null;
    }
}
