/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.wsd;


import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Madeleine
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "booking")
public class Booking implements Serializable {
    @XmlType(name = "statusTypeEnum")
    @XmlEnum
    public enum StatusType {
        ACTIVE,
        CANCELLED,
        COMPLETED;
        
        public String value() {
            return name();
        }

        public static StatusType fromValue(String v) {
            return valueOf(v);
        }
    }

    @XmlElement(name = "bookingID")
    protected int bookingID;
    @XmlElement(name = "tutorEmail")
    protected String tutorEmail;
    @XmlElement(name = "tutorName")
    protected String tutorName;
    @XmlElement(name = "subjectName")
    protected Tutor.TutorSpecialty subjectName;
    @XmlElement(name = "studentEmail")
    protected String studentEmail;
    @XmlElement(name = "studentName")
    protected String studentName;
    @XmlElement(name = "statusType")
    protected StatusType statusType;


    public Booking(){}

    public Booking(int bookingID, String tutorEmail, String tutorName, 
            Tutor.TutorSpecialty subjectName,
            String studentEmail, String studentName,StatusType statusType){
        this.bookingID = bookingID;
        this.tutorEmail = tutorEmail;
        this.tutorName = tutorName;
        this.subjectName = subjectName;
        this.studentEmail = studentEmail;
        this.studentName = studentName;
        this.statusType = statusType;
    }



    public int getBookingID() {return bookingID;}

    public void setBookingID(int bookingID) {this.bookingID = bookingID;}

    public String getTutorEmail() {return tutorEmail; }

    public void setTutorEmail(String tutorEmail) {this.tutorEmail = tutorEmail; }

    public String getTutorName() {return tutorName; }

    public void setTutorName(String tutorName) {this.tutorName = tutorName;}

    public Tutor.TutorSpecialty getSubjectName() {return subjectName;}

    public void setSubjectName(Tutor.TutorSpecialty subjectName) {this.subjectName = subjectName;}

    public String getStudentEmail() {return studentEmail;}

    public void setStudentEmail(String studentEmail) {this.studentEmail = studentEmail;}

    public String getStudentName() {return studentName;}

    public void setStudentName(String studentName) { this.studentName = studentName;}

    public StatusType getStatusType() {return statusType; }

    public void setStatusType(StatusType statusType) {this.statusType = statusType;}
    
    public void cancelBooking() {
        if (statusType.equals(StatusType.ACTIVE)) {
            setStatusType(StatusType.CANCELLED);
        }
    }

    public void completeBooking() {
        if (statusType.equals(StatusType.ACTIVE)) {
            setStatusType(StatusType.COMPLETED);
        }
    }

}
