public class Appointment {
    private String patientID;
    private String staffID;
    private String status;
    private String date;
    private String time;


    public Appointment(String patientID, String staffID, String status, String date, String time) {
        //TODO Auto-generated constructor stub
        this.patientID = patientID;
        this.staffID = staffID;
        this.status = status;
        this.date = date;
        this.time = time;
    }
    public String getPatientID() {
        return patientID;
    }
    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }
    public String getStaffID() {
        return staffID;
    }
    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

}
