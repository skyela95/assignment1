/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.wsd;

/**
 *
 * @author Madeleine
 */
public class Booking {

    public enum StatusType {
        ACTIVE,
        CANCELLED,
        COMPLETED
    }

    protected String bookingID;
    protected String tutorEmail;
    protected String tutorName;
    protected String subjectName;
    protected String studentEmail;
    protected String studentName;
    protected StatusType statusType;


    public Booking(){}

    public Booking(String bookingID, String tutorEmail, String tutorName, String subjectName,
            String studentEmail, String studentName,StatusType statusType){
        this.bookingID = bookingID;
        this.tutorEmail = tutorEmail;
        this.tutorName = tutorName;
        this.subjectName = subjectName;
        this.studentEmail = studentEmail;
        this.studentName = studentName;
        this.statusType = statusType;
    }



    public String getBookingID() {return bookingID;}

    public void setBookingID(String bookingID) {this.bookingID = bookingID;}

    public String getTutorEmail() {return tutorEmail; }

    public void setTutorEmail(String tutorEmail) {this.tutorEmail = tutorEmail; }

    public String getTutorName() {return tutorName; }

    public void setTutorName(String tutorName) {this.tutorName = tutorName;}

    public String getSubjectName() {return subjectName;}

    public void setSubjectName(String subjectName) {this.subjectName = subjectName;}

    public String getStudentEmail() {return studentEmail;}

    public void setStudentEmail(String studentEmail) {this.studentEmail = studentEmail;}

    public String getStudentName() {return studentName;}

    public void setStudentName(String studentName) { this.studentName = studentName;}

    public StatusType getStatusType() {return statusType; }

    public void setStatusType(StatusType statusType) {this.statusType = statusType;}

}
