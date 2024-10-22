import java.util.List;

public class AppointmentUI {
    public void printMainMenu() {
        System.out.println("View Appointments");
    }
    public void printAllAppointments(List<Appointment> appointmentList) {
        System.out.println("Appointments:");
        for (Appointment appointment : appointmentList) {
            System.out.println(appointment.getPatientID() + ", " + appointment.getStaffID() + ", " + appointment.getStatus() + ", " + appointment.getDate() + ", " + appointment.getTime());
        }
    }
}
