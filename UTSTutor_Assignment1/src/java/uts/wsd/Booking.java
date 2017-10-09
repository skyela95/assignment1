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
 * The base Booking object, storing relevant information about the student,
 * tutor, and the status of the booking
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "booking")
public class Booking implements Serializable {

    // <editor-fold defaultstate="collapsed" desc=" ${Enums} ">
    // Enum for the booking status. Has extra methods for easy string comparison,
    // and getting the enum names for XML parsing
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
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" ${Variables} ">
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
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" ${Constructors} ">
    /**
     * Empty constructor for XML factory
     */
    public Booking() {
    }

    /**
     * Base constructor for creating a new booking
     *
     * @param bookingID Unique ID for the booking - integer
     * @param tutorEmail The tutors email/username
     * @param tutorName The tutors real name
     * @param subjectName The tutors subject specialty (TutorSpecialty class)
     * @param studentEmail The students email/username
     * @param studentName The students real name
     * @param statusType The booking status
     */
    public Booking(int bookingID, String tutorEmail, String tutorName,
            Tutor.TutorSpecialty subjectName,
            String studentEmail, String studentName, StatusType statusType) {
        this.bookingID = bookingID;
        this.tutorEmail = tutorEmail;
        this.tutorName = tutorName;
        this.subjectName = subjectName;
        this.studentEmail = studentEmail;
        this.studentName = studentName;
        this.statusType = statusType;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" ${Getters} ">
    /**
     * @return the unique booking id
     */
    public int getBookingID() {
        return bookingID;
    }

    /**
     * @return the tutors email
     */
    public String getTutorEmail() {
        return tutorEmail;
    }

    /**
     * @return the tutors real name
     */
    public String getTutorName() {
        return tutorName;
    }

    /**
     * @return the tutors specialty
     */
    public Tutor.TutorSpecialty getSubjectName() {
        return subjectName;
    }

    /**
     * @return the students email
     */
    public String getStudentEmail() {
        return studentEmail;
    }

    /**
     * @return the students name
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * @return the booking status type
     */
    public StatusType getStatusType() {
        return statusType;
    }

// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" ${Setter} ">
    
    /**
     * @param bookingID the unique integer identifier
     */
    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    /*
     * @param tutorEmail new email for tutor
     */
    public void setTutorEmail(String tutorEmail) {
        this.tutorEmail = tutorEmail;
    }

    /**
     * @param tutorName new real name for tutor
     */
    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    /**
     * @param subjectName new subject/specialty for booking
     */
    public void setSubjectName(Tutor.TutorSpecialty subjectName) {
        this.subjectName = subjectName;
    }

    /**
     * @param studentEmail new student email
     */
    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    /**
     * @param studentName new student name
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    /**
     * @param statusType new status type for booking
     */
    public void setStatusType(StatusType statusType) {
        this.statusType = statusType;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" ${Booking Control} ">
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
// </editor-fold>

}
