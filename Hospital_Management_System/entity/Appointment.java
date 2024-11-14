package entity;

import java.time.Instant;


/**
 * An entity class to represent an appointment that holds the patient ID, staff ID, status, date and time
 */
public class Appointment {

    public enum Status{
        CONFIRMED,
        PENDING,
        EMPTY,
        COMPLETED,
        CANCELLED

    }
    private String patientID;
    private String staffID;
    private Status status;
    private String date;
    private String time;
    private int appointmentID;


    /**
     * getter for appointment ID
     * @return
     */
    public int getAppointmentID() {
        return appointmentID;
    }
    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    /**
     * Appointment constructor for loading existing apointments
     * @param appointmentID
     * @param patientID
     * @param staffID
     * @param status
     * @param date
     * @param time
     */
    public Appointment(int appointmentID, String patientID, String staffID, Status status, String date, String time) {
        this.patientID = patientID;
        this.staffID = staffID;
        this.status = status;
        this.date = date;
        this.time = time;
        this.appointmentID=appointmentID;
        //generates random appointment unique ID based on current time stamps
        
    }

    /**
     * Appointment constructor for new appointments
     * @param staffID
     * @param status
     * @param date
     * @param time
     */
    public Appointment(String staffID, Status status, String date, String time) {
        this.patientID = "NA";
        this.staffID = staffID;
        this.status = status;
        this.date = date;
        this.time = time;
        this.appointmentID=generateID();
        //generates random appointment unique ID based on current time stamps
        
    }

    /**
     * generates new unique IDs
     * @return
     */
    public static int generateID() {
        return (int) (Instant.now().toEpochMilli() % Integer.MAX_VALUE);
    }

    /**
     * getter for patient ID
     * @return
     */
    public String getPatientID() {
        return patientID;
    }

    /**
     * setter for patient ID
     * @param patientID
     */
    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    /**
     * getter for staff ID
     * @return
     */
    public String getStaffID() {
        return staffID;
    }

    /**
     * setter for staff ID
     * @param staffID
     */
    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    /**
     * getter for appointment status
     * @return
     */
    public Status getStatus() {
        return status;
    }

    /**
     * setter for appointment status
     * @param status
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * getter for appointment date
     * @return
     */
    public String getDate() {
        return date;
    }

    /**
     * setter for appointment date
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * getter for appointment time
     * @return
     */
    public String getTime() {
        return time;
    }

    /**
     * setter for appointment time
     * @param time
     */
    public void setTime(String time) {
        this.time = time;
    }

    

}