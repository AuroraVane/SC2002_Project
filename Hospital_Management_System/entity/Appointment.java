package entity;

import java.time.Instant;

/**
 *
 */
public class Appointment {
    private String patientID;
    private String staffID;
    private String status;
    private String date;
    private String time;
    private int appointmentID;


    /**
     * @return
     */
    public int getAppointmentID() {
        return appointmentID;
    }
    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    /**
     * @param appointmentID
     * @param patientID
     * @param staffID
     * @param status
     * @param date
     * @param time
     */
    public Appointment(int appointmentID, String patientID, String staffID, String status, String date, String time) {
        this.patientID = patientID;
        this.staffID = staffID;
        this.status = status;
        this.date = date;
        this.time = time;
        this.appointmentID=appointmentID;
        //generates random appointment unique ID based on current time stamps
        
    }

    /**
     * @param staffID
     * @param status
     * @param date
     * @param time
     */
    public Appointment(String staffID, String status, String date, String time) {
        this.patientID = "NA";
        this.staffID = staffID;
        this.status = status;
        this.date = date;
        this.time = time;
        this.appointmentID=generateID();
        //generates random appointment unique ID based on current time stamps
        
    }

    /**
     * @return
     */
    public static int generateID() {
        return (int) (Instant.now().toEpochMilli() % Integer.MAX_VALUE);
    }

    /**
     * @return
     */
    public String getPatientID() {
        return patientID;
    }

    /**
     * @param patientID
     */
    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    /**
     * @return
     */
    public String getStaffID() {
        return staffID;
    }

    /**
     * @param staffID
     */
    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    /**
     * @return
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time
     */
    public void setTime(String time) {
        this.time = time;
    }

    

}