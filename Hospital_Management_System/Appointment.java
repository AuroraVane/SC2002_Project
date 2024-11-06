import java.io.IOException;
import java.time.Instant;
import java.util.List;
public class Appointment {
    private String patientID;
    private String staffID;
    private String status;
    private String date;
    private String time;
    private int appointmentID;
    private static final String APPOINTMENT_FILE_PATH = "./TextFiles/Appointment_List.txt";


    public int getAppointmentID() {
        return appointmentID;
    }
    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }
    public Appointment(int appointmentID, String patientID, String staffID, String status, String date, String time) {
        //TODO Auto-generated constructor stub
        this.patientID = patientID;
        this.staffID = staffID;
        this.status = status;
        this.date = date;
        this.time = time;
        this.appointmentID=appointmentID;
        //generates random appointment unique ID based on current time stamps
        
    }
    public Appointment(String staffID, String status, String date, String time) {
        this.patientID = "NA";
        this.staffID = staffID;
        this.status = status;
        this.date = date;
        this.time = time;
        this.appointmentID=generateID();
        //generates random appointment unique ID based on current time stamps
        
    }
    public static int generateID() {
        return (int) (Instant.now().toEpochMilli() % Integer.MAX_VALUE);
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

    public static void scheduleAppointment(String patientID, int appointmentID) {
        List<Appointment> appointments;
        try {
            appointments = TextFileReader.loadAppointments(APPOINTMENT_FILE_PATH);
        } catch (IOException e) {
            System.out.println("Error loading appointments: " + e.getMessage());
            return;
        }

        // Find the appointment with the given appointmentID
        Appointment appointmentToBook = null;
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentID() == appointmentID && appointment.getPatientID().equals("NA")) {
                appointmentToBook = appointment;
                break;
            }
        }

        if (appointmentToBook != null) {
            // Update the appointment details with patientID and set status to "PENDING"
            appointmentToBook.setPatientID(patientID);
            appointmentToBook.setStatus("PENDING");

            // Update the appointment in the file
            TextFileWriter.updateAppointment(appointmentToBook);
            System.out.println("Appointment scheduled successfully for patient " + patientID);
        } else {
            System.out.println("Appointment not available or already booked.");
        }
    }

}