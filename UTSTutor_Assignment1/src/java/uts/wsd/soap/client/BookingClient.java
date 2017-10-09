/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.wsd.soap.client;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import uts.wsd.User.UserType;

/**
 * The booking client used by a user to use the SOAP Service running on the
 * server. Students and Tutors have different permissions, just as it is on
 * the normal web-site.
 */
public class BookingClient {

    // <editor-fold defaultstate="collapsed" desc=" ${Constants} ">
    /**
     * Patterns use for RegEx matching
     */
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "([0-9a-zA-Z]([-.\\w]*[0-9a-zA-Z])+@([0-9a-zA-Z]"
            + "[-\\w]*[0-9a-zA-Z]\\.)+[a-zA-Z])");

    private static final Pattern DOB_PATTERN = Pattern.compile(
            "[0-9]{2}/[0-9]{2}/[0-9]{4}");

    private static final Pattern NAME_PATTERN = Pattern.compile(
            "(([A-Z][a-z]+)\\s*)*([A-Z][a-z]*)");

    /**
     * Constants for console actions
     */
    // General
    private static final String INPUT_QUIT = "quit";
    private static final String INPUT_USERNAME = "Username: ";
    private static final String INPUT_PASSWORD = "Password: ";
    private static final String INPUT_SELECTION = "Enter Selection: ";
    private static final String INPUT_TUTOR_EMAIL = "Tutor Email: ";
    private static final String INPUT_BOOKING_ID = "Booking ID: ";
    private static final String INPUT_STUDENT_EMAIL = "Student Email: ";
    private static final String INPUT_STUDENT_NAME = "Student Name: ";
    private static final String INPUT_STATUS = "Enter Status: ";
    private static final String INPUT_NEW_NAME = "Enter New Name: ";
    private static final String INPUT_NEW_DOB = " Enter New Date of Birth: ";
    private static final String INPUT_NEW_PASSWORD = "Enter New Password: ";

    private static final String ERROR_INVALID_ID = "ERROR: Invalid ID";

    // Authorization
    private static final String AUTHORIZE_REQUIRE = "-Need Authorisation-";

    private static final String ERROR_NO_AUTHORIZE = "ERROR: Could not authorize user";

    // Login
    private static final String ERROR_LOGIN_INVALID = "Invalid login details! Try again.";

    // General Bookings
    private static final String BOOKING_FORMAT = "\nBooking [ID: %1$d]\n"
            + "Student: %2$s (%3$s) || Tutor: %4$s (%5$s) [%6$s] \n"
            + "Status: %7$s\n";

    // Booking Creation    
    private static final String BOOKING_CREATED_FORMAT = "Booking Created: ID:"
            + " %1$d \nFOR Student: %2$s \nAND Tutor: %3$s";

    private static final String ERROR_BOOKING_CREATE = "ERROR: Could not create booking";

    // Booking Search
    private static final String HELPER_STATUSES = "Statuses: ACTIVE | CANCELLED | COMPLETE";
    private static final String SEARCH_BOOKING_NOT_FOUND = "No bookings found";
    private static final String ERROR_BOOKING_SEARCH_ID = "No booking with ID %1$d found";

    private static final String ERROR_BOOKING_SEARCH_STRING = "No bookings under [%1$s] were found";

    // Booking Cancel
    private static final String BOOKING_CANCEL_CONFIRMATION = "CONFIRM";
    private static final String BOOKING_CANCEL_CONFIRMATION_INSTRUCTION = "Type %1$s exactly to cancel account: ";
    private static final String BOOKING_CANCELLED_SUCCESS = "Booking [ID: %1$d] cancelled!";

    private static final String ERROR_BOOKING_CANCELLING = "ERROR Cancelling Booking [ID: %1$d]";
    private static final String ERROR_CANCEL_NOT_CONFIRMED = "Account cancel not confirmed!";
    private static final String ERROR_BOOKING_OWNER = "ERROR: You are not the owner of this booking";

    // Booking Completion
    private static final String BOOKING_COMPLETION_SUCCESS = "Booking [ID: %1$d] completed!";

    private static final String ERROR_COMPLETING_BOOKING = "ERROR completing Booking [ID: %1$d]";

    // Account Edit
    private static final String ACCOUNT_EDIT_NAME_SUCCESS = "Successfully changed name to %1$s";
    private static final String ACCOUNT_EDIT_DOB_SUCCESS = "Successfully changed your Date of Birth to %1$s";
    private static final String ACCOUNT_EDIT_PASSWORD_SUCCESS = "Successfully changed your passsword";

    private static final String ERROR_ACCOUNT_EDIT_INVALID_NAME = "ERROR: not a valid name";
    private static final String ERROR_ACCOUNT_EDIT_INVALID_DOB = "ERROR: not a valid date of birth";

    /**
     * Menu Titles
     */
    private static final String MENU_TITLE_BOOKING_DETAIL = "- Booking details - '%1$s' to exit";
    private static final String MENU_TITLE_BOOKING_BY_ID = "- Print by booking ID - '%1$s' to exit";
    private static final String MENU_TITLE_BOOKING_BY_STUDENT_EMAIL = "- Print by booking by Student Email - '%1$s' to exit";
    private static final String MENU_TITLE_BOOKING_BY_STUDENT_NAME = "- Print by booking by Student Name - '%1$s' to exit";
    private static final String MENU_TITLE_BOOKING_BY_STATUS = "- Print by booking status - '%1$s' to exit";
    private static final String MENU_TITLE_CANCEL_BOOKING = "- Cancel Booking - '%1$s' to exit";
    private static final String MENU_TITLE_EDIT_NAME = "- Edit Name - '%1$s' to exit";
    private static final String MENU_TITLE_EDIT_DOB = "- Edit Date Of Birth - '%1$s' to exit";
    private static final String MENU_TITLE_EDIT_PASSWORD = "- Edit Password - '%1$s' to exit";
    private static final String MENU_TITLE_COMPLETE_BOOKING = "- Complete Booking - '%1$s' to exit";
    private static final String MENU_TITLE_CANCEL_ACCOUNT = "- CANCEL ACCOUNT - '%1$s' to exit";

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" ${Variables} ">
    // User Variables
    private static String name = "";
    private static String type;

    // Booking Application Reference
    private static BookingSOAP booking;

    // Bool to end menu loop
    static boolean sessionEnd = false;

    // Reader for user input
    static Scanner reader = new Scanner(System.in);

    // Main java method that runs menu in a loop
    public static void main(String[] args) {
        BookingApp locator = new BookingApp();
        booking = locator.getBookingSOAPPort();

        while (!sessionEnd) {
            attemptLogin("");
            printMainMenu("");
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" ${Utilities} ">
    
    /**
     * Clears the console artificially by printing X lines
     * @param lines X amount of lines to print
     */
    private static void clearConsole(int lines) {
        for (int i = 0; i < lines; i++) {
            System.out.print("\n");
        }
        System.out.flush();
    }

    /**
     * Prints the top of the user menu, with user name and results
     * @param result Prints any result from other menu actions e.g. success
     */
    private static void printMenuTop(String result) {
        clearConsole(20);
        System.out.println("---------------------------------");
        System.out.println("      Welcome to UTS Tutor       ");
        if (!name.equals("")) {
            System.out.println("      Logged in as: " + name);
        }
        System.out.println("---------------------------------");
        System.out.println("                                 ");
        if (!result.equals("")) {
            printResult(result);
        }
    }

    /**
     * Used to print the result underneath the top menu item
     * @param result Prints the passed result e.g. Success
     */
    private static void printResult(String result) {
        System.out.println(result);
        System.out.println("---------------------------------");
        System.out.println("                                 ");
    }

    /**
     * Prints the given booking object using a formatted string
     * @param booking The booking object to print
     * @return The booking object in a neat string format
     */
    private static String printBooking(Booking booking) {
        String result = String.format(BOOKING_FORMAT, booking.getBookingID(),
                booking.getStudentName(), booking.getStudentEmail(),
                booking.getTutorName(), booking.getTutorEmail(),
                booking.getSubjectName().value(),
                booking.getStatusType().value());
        return result;
    }

    /**
     * Attempts to authorize the user details through the SOAP Service
     * @return True/False if the details were correct
     */
    private static boolean authorizeUser() {
        System.out.println(AUTHORIZE_REQUIRE);
        System.out.print(INPUT_USERNAME);
        String username = reader.next().trim();
        if (username.equals(INPUT_QUIT)) {
            return false;
        }
        System.out.print(INPUT_PASSWORD);
        String password = reader.next().trim();
        return booking.authorizeUser(username, password);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" ${Login} ">
    
    /**
     * Attempts to log in the user with provided details through the SOAP service
     * @param result The result message to print below the menu
     */
    private static void attemptLogin(String result) {
        printMenuTop(result);
        System.out.print(INPUT_USERNAME);
        String email = reader.next().trim();
        System.out.print(INPUT_PASSWORD);
        String password = reader.next().trim();

        User user = booking.login(email, password);
        if (user == null) {
            attemptLogin(ERROR_LOGIN_INVALID);
        } else {
            name = user.getName();
            type = user.getUserType().value();
        }
    }

    /**
     * Attempts to log the user out of the system through SOAP
     */
    private static void attemptLogout() {
        name = "";
        type = null;
        booking.logout();
        printMenuTop("");
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" ${Main Menus} ">
    
    /**
     * Decides what menu to display depending on the user's type
     * @param result The result to display on the selected menu
     */
    private static void printMainMenu(String result) {
        if (type.equals(UserType.STUDENT.value())) {
            printStudentMenu(result);
        } else {
            printTutorMenu(result);
        }
    }

    /**
     * Prints the STUDENT menu with their options
     * @param result The result to display underneath the menu
     */
    private static void printStudentMenu(String result) {
        printMenuTop(result);
        System.out.println("[1] : Create Booking");
        System.out.println("[2] : View Bookings");
        System.out.println("[3] : Cancel Booking");
        System.out.println("[4] : Account");
        System.out.println("                    ");
        System.out.println("[0] : Logout");

        System.out.print(INPUT_SELECTION);
        String selection = reader.next().trim();
        switch (selection) {
            case "1":
                printCreateBooking("");
                break;
            case "2":
                printViewBookings("");
                break;
            case "3":
                printCancelBooking("");
                break;
            case "4":
                printAccountMenu("");
            case "0":
                attemptLogout();
                break;
            default:
                printStudentMenu("");
                break;
        }
    }

    /**
     * Prints the TUTOR menu with their options
     * @param result The result to display underneath the menu
     */
    private static void printTutorMenu(String result) {
        printMenuTop(result);
        System.out.println("[1] : View Bookings");
        System.out.println("[2] : Cancel Booking");
        System.out.println("[3] : Complete Booking");
        System.out.println("[4] : Account");
        System.out.println("                    ");
        System.out.println("[0] : Logout");

        System.out.print(INPUT_SELECTION);
        String selection = reader.next().trim();
        switch (selection) {
            case "1":
                printViewBookings("");
                break;
            case "2":
                printCancelBooking("");
                break;
            case "3":
                printCompleteBooking("");
                break;
            case "4":
                printAccountMenu("");
            case "0":
                attemptLogout();
                break;
            default:
                printStudentMenu("");
                break;
        }
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" ${Booking Search} ">
    
    /**
     * Prints the Booking View menu with possible view parameters
     * @param result The result to display underneath the menu
     */
    private static void printViewBookings(String result) {
        printMenuTop(result);
        System.out.println("- View Bookings - ");
        System.out.println("[1] : View ALL Bookings");
        System.out.println("[2] : View Bookings by ID");
        System.out.println("[3] : View Booking by Student Email");
        System.out.println("[4] : View Bookings by Student Name");
        System.out.println("[5] : View Bookings by Status");
        System.out.println("                             ");
        System.out.println("[0] : Back");
        System.out.print(INPUT_SELECTION);
        String selection = reader.next().trim();
        switch (selection) {
            case "1":
                printAllBookings();
                break;
            case "2":
                printBookingByID("");
                break;
            case "3":
                printBookingByStudentEmail("");
                break;
            case "4":
                printBookingByStudentName("");
                break;
            case "5":
                printBookingByStatus("");
                break;
            case "0":
                printMainMenu("");
                break;
            default:
                printViewBookings("");
                break;
        }
    }

    /**
     * Retrieves bookings through the SOAP service, and prints them neatly
     */
    private static void printAllBookings() {
        String result = "";
        List<Booking> bookings = booking.getBookings();

        for (Booking booking : bookings) {
            result += printBooking(booking);
        }
        if (result.equals("")) {
            printViewBookings(SEARCH_BOOKING_NOT_FOUND);
        } else {
            printViewBookings(result);
        }
    }

    /**
     * Retrieves a specific booking through ID using SOAP and prints it
     * @param result The result to display underneath the menu
     */
    private static void printBookingByID(String result) {
        printMenuTop(result);
        System.out.println(String.format(MENU_TITLE_BOOKING_BY_ID, INPUT_QUIT));
        System.out.print(INPUT_BOOKING_ID);
        String input = reader.next().trim();
        int id = -1;

        if (input.equals(INPUT_QUIT)) {
            printViewBookings("");
            return;
        } else {
            try {
                id = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                printCancelBooking(ERROR_INVALID_ID);
                return;
            }

            Booking book = booking.getBookingByID(id);
            if (book == null) {
                printBookingByID(String.format(ERROR_BOOKING_SEARCH_ID, id));
            } else {
                printViewBookings(printBooking(book));
            }
        }
    }

    /** 
     * Retrieves bookings based on the provided student email, and prints them
     * if successful
     * @param result The result to display underneath the menu 
     */
    private static void printBookingByStudentEmail(String result) {
        printMenuTop(result);
        System.out.println(String.format(MENU_TITLE_BOOKING_BY_STUDENT_EMAIL, INPUT_QUIT));
        System.out.print(INPUT_STUDENT_EMAIL);
        String input = reader.next().trim();;
        if (input.equals(INPUT_QUIT)) {
            printViewBookings("");
            return;
        } else {
            List<Booking> bookings = booking.getBookingsByStudentEmail(input);
            String s = "";
            for (Booking booking : bookings) {
                s += printBooking(booking);
            }
            if (s.equals("")) {
                printBookingByStudentEmail(String
                        .format(ERROR_BOOKING_SEARCH_STRING, input));
            } else {
                printViewBookings(s);
            }
        }
    }

    /**
     * Retrieves bookings based on the provided student name, and prints them 
     * if successful
     * @param result The result to display underneath the menu
     */
    private static void printBookingByStudentName(String result) {
        printMenuTop(result);
        System.out.println(String.format(MENU_TITLE_BOOKING_BY_STUDENT_NAME, INPUT_QUIT));
        System.out.print(INPUT_STUDENT_NAME);
        String input = reader.next().trim();
        if (input.equals(INPUT_QUIT)) {
            printViewBookings("");
            return;
        } else {
            List<Booking> bookings = booking.getBookingsByStudentName(input);
            String s = "";
            for (Booking booking : bookings) {
                s += printBooking(booking);
            }
            if (s.equals("")) {
                printBookingByStudentName(String
                        .format(ERROR_BOOKING_SEARCH_STRING, input));
            } else {
                printViewBookings(s);
            }
        }
    }

    /**
     * Retrieves bookings based on the provided status, and prints them
     * if successful
     * @param result The result to display underneath the menu
     */
    private static void printBookingByStatus(String result) {
        printMenuTop(result);
        System.out.println(String.format(MENU_TITLE_BOOKING_BY_STATUS, INPUT_QUIT));
        System.out.println(HELPER_STATUSES);
        System.out.print(INPUT_STATUS);
        String input = reader.next().trim();
        if (input.equals(INPUT_QUIT)) {
            printViewBookings("");
            return;
        } else {
            List<Booking> bookings = booking.getBookingsByStatus(input);
            String s = "";
            for (Booking booking : bookings) {
                s += printBooking(booking);
            }
            if (s.equals("")) {
                printBookingByStatus(String
                        .format(ERROR_BOOKING_SEARCH_STRING, input));
            } else {
                printViewBookings(s);
            }
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" ${Booking Creation} ">
    
    /**
     * Prints the Create Booking menu, and allows the user to create a new
     * booking by providing a tutors email. The user is required to 
     * enter their details to authorize themselves before a booking is created
     * @param result The result to display underneath the menu
     */
    private static void printCreateBooking(String result) {
        printMenuTop(result);
        System.out.println(String.format(MENU_TITLE_BOOKING_DETAIL, INPUT_QUIT));
        System.out.print(INPUT_TUTOR_EMAIL);
        String input = reader.next().trim();

        if (input.equals(INPUT_QUIT)) {
            printMainMenu("");
            return;
        } else {
            if (authorizeUser()) {
                Booking book = booking.createBookingString(
                        booking.getLoggedUser().getEmail(), input);
                if (book == null) {
                    printStudentMenu(ERROR_BOOKING_CREATE);
                } else {
                    printMainMenu(String.format(BOOKING_CREATED_FORMAT,
                            book.getBookingID(), book.getStudentName(),
                            book.getTutorName()));
                }
            } else {
                printStudentMenu(ERROR_NO_AUTHORIZE);
            }
        }
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" ${Booking Completion} ">
    
    /**
     * Prints the Booking Completion menu, which allows a TUTOR to complete
     * a booking. They are required to enter their details to authorize themselves
     * before a booking is completed
     * @param result 
     */
    private static void printCompleteBooking(String result) {
        printMenuTop(result);
        System.out.println(String.format(MENU_TITLE_COMPLETE_BOOKING, INPUT_QUIT));
        System.out.print(INPUT_BOOKING_ID);
        String input = reader.next().trim();
        int id = -1;
        if (input.equals(INPUT_QUIT)) {
            printMainMenu("");
            return;
        } else {
            try {
                id = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                printCancelBooking(ERROR_INVALID_ID);
                return;
            }

            if (authorizeUser()) {
                if (booking.completeBookingID(id)) {
                    printMainMenu(String.format(BOOKING_COMPLETION_SUCCESS, id));
                } else {
                    printMainMenu(String.format(ERROR_COMPLETING_BOOKING, id));
                }
            } else {
                printCancelBooking(ERROR_NO_AUTHORIZE);
            }
        }
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" ${Booking Cancel} ">
    
    /**
     * Prints the Cancel Booking menu, which allows both a STUDENT and TUTOR to
     * cancel a booking. They are required to authorize themselves, as they can
     * only cancel a booking they own/are a part of.
     * @param result The result to dispaly underneath the menu
     */
    private static void printCancelBooking(String result) {
        printMenuTop(result);
        System.out.println(String.format(MENU_TITLE_CANCEL_BOOKING, INPUT_QUIT));
        System.out.print(INPUT_BOOKING_ID);
        String input = reader.next().trim();
        int id = -1;
        if (input.equals(INPUT_QUIT)) {
            printMainMenu("");
            return;
        } else {
            try {
                id = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                printCancelBooking(ERROR_INVALID_ID);
                return;
            }

            if (authorizeUser()) {
                if (booking.isBookingOwnerByID(id)) {
                    if (booking.cancelBookingID(id)) {
                        printMainMenu(String.format(BOOKING_CANCELLED_SUCCESS, id));
                    } else {
                        printMainMenu(String.format(ERROR_BOOKING_CANCELLING, id));
                    }
                } else {
                    printCancelBooking(ERROR_BOOKING_OWNER);
                }
            } else {
                printCancelBooking(ERROR_NO_AUTHORIZE);
            }
        }

    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" ${Account Edit} ">
    
    /**
     * Displays the Account Settings menu, with the user's given options
     * @param result The result to display underneath the menu
     */
    private static void printAccountMenu(String result) {
        printMenuTop(result);
        System.out.println("- Account Settings - ");
        System.out.println("[1] : Edit Name");
        System.out.println("[2] : Edit Date of Birth");
        System.out.println("[3] : Edit Password");
        System.out.println("[4] : Cancel Account - WARNING");
        System.out.println("                             ");
        System.out.println("[0] : Back");
        System.out.print(INPUT_SELECTION);
        String selection = reader.next().trim();

        switch (selection) {
            case "1":
                printEditName("");
                break;
            case "2":
                printEditDOB("");
                break;
            case "3":
                printEditPassword("");
                break;
            case "4":
                printCancelAccount("");
                break;
            case "0":
                printMainMenu("");
                break;
            default:
                printAccountMenu("");
                break;
        }
    }

    /**
     * Allows the user to edit their name, provided it meets the 
     * naming convention
     * @param result The result to display underneath the menu
     */
    private static void printEditName(String result) {
        printMenuTop(result);
        System.out.println(String.format(MENU_TITLE_EDIT_NAME, INPUT_QUIT));
        System.out.print(INPUT_NEW_NAME);
        String input = reader.nextLine().trim();
        if (input.equals(INPUT_QUIT)) {
            printAccountMenu("");
        } else {
            Matcher m = NAME_PATTERN.matcher(input);
            if (m.matches()) {
                booking.editName(input);
                name = booking.getLoggedUser().getName();
                printAccountMenu(String.format(ACCOUNT_EDIT_NAME_SUCCESS, input));
            } else {
                printEditName(ERROR_ACCOUNT_EDIT_INVALID_NAME);
            }
        }
    }

     /**
     * Allows the user to edit their date of birth, provided it meets the 
     * date of birth convention
     * @param result The result to display underneath the menu
     */
    private static void printEditDOB(String result) {
        printMenuTop(result);
        System.out.println(String.format(MENU_TITLE_EDIT_DOB, INPUT_QUIT));
        System.out.print(INPUT_NEW_DOB);
        String input = reader.next().trim();
        if (input.equals(INPUT_QUIT)) {
            printAccountMenu("");
        } else {
            Matcher m = DOB_PATTERN.matcher(input);
            if (m.matches()) {
                booking.editDOB(input);
                printAccountMenu(String.format(ACCOUNT_EDIT_DOB_SUCCESS, input));
            } else {
                printEditDOB(ERROR_ACCOUNT_EDIT_INVALID_DOB);
            }
        }
    }

     /**
     * Allows the user to edit their password, provided they are able to 
     * authorize their account with the details they provide
     * @param result The result to display underneath the menu
     */
    private static void printEditPassword(String result) {
        printMenuTop(result);
        System.out.println(String.format(MENU_TITLE_EDIT_PASSWORD, INPUT_QUIT));
        System.out.print(INPUT_NEW_PASSWORD);
        String input = reader.nextLine().trim();
        if (input.equals(INPUT_QUIT)) {
            printAccountMenu("");
        } else {
            if (authorizeUser()) {
                booking.editPassword(input);
                printAccountMenu(ACCOUNT_EDIT_PASSWORD_SUCCESS);
            } else {
                printEditPassword(ERROR_NO_AUTHORIZE);
            }
        }
    }

     /**
     * Allows the user to cancel their account (Both TUTOR and STUDENT), and 
     * requires them to not only confirm the action, but authorize their account
     * a final time
     * @param result The result to display underneath the menu
     */
    private static void printCancelAccount(String result) {
        printMenuTop(result);
        System.out.println(String.format(MENU_TITLE_CANCEL_ACCOUNT, INPUT_QUIT));
        System.out.print(String.format(BOOKING_CANCEL_CONFIRMATION_INSTRUCTION,
                BOOKING_CANCEL_CONFIRMATION));
        String input = reader.next().trim();
        if (input.equals(INPUT_QUIT)) {
            printAccountMenu("");
            return;
        }
        if (input.equals(BOOKING_CANCEL_CONFIRMATION)) {
            if (authorizeUser()) {
                booking.cancelAccount();
                attemptLogout();
            } else {
                printCancelAccount(ERROR_NO_AUTHORIZE);
            }
        } else {
            printCancelAccount(ERROR_CANCEL_NOT_CONFIRMED);
        }

    }
    // </editor-fold>

}
