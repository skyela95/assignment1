/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

package uts.wsd.soap.client;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Adam
 
public class BookingClient {
    private static String name = "";

    private static BookingSOAP booking;
    
    static boolean sessionEnd = false;
    
    static Scanner reader = new Scanner(System.in);
    
    public static void main(String[] args) {
        BookingApp locator = new BookingApp();
        booking = locator.getBookingSOAPPort(); 


        clearConsole(20);
        attemptLogin();
        while (!sessionEnd) {
            clearConsole(20);
            printMenuTop();
            System.out.println("[0] : Exit");
            int i = reader.nextInt();
            switch(i) {
                case 0:
                    sessionEnd = true;
            }
        }
    }

    private static void clearConsole(int lines) {
        for (int i = 0; i < lines; i++) {
            System.out.print("\n");
        }
        System.out.flush();
    }

    private static void printMenuTop() {
        System.out.println("---------------------------------");
        System.out.println("      Welcome to UTS Tutor       ");
        if (!name.equals("")) {
            System.out.println("      Logged in as: " + name);
        }
        System.out.println("---------------------------------");
        System.out.println("                                 ");
    }
    
    private static void attemptLogin() {        
        printMenuTop();
        System.out.print("Enter email: ");
        String email = reader.next();
        System.out.print("Enter password: ");
        String password = reader.next();
        
        User user = booking.login(email, password);
        if (user == null) {
            System.out.println("Invalid login details! Try again.");
            attemptLogin();
        } else {
            name = user.getName();
        }        
    }

}
 */