import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {
    private String appointmentId;
    private String patientId;
    private String doctorId;
    private LocalDate date;
    private LocalTime time;
    private String status;

    public Appointment(String appointmentId, String patientId, String doctorId, 
                       LocalDate date, LocalTime time) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
        this.time = time;
        this.status = "Pending";
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Appointment ID: " + appointmentId + ", Patient: " + patientId + 
               ", Doctor: " + doctorId + ", Date: " + date + ", Time: " + time + 
               ", Status: " + status;
    }
}
