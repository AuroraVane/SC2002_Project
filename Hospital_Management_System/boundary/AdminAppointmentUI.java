package boundary;

import entity.Appointment;
import java.util.List;

/**
 * UI class for AdminAppointmentUI used by AdminAppointmentController
 */
public class AdminAppointmentUI extends AppointmentUI{
    /**
     * Default constructor
     */
    public AdminAppointmentUI() {
    }

    /**
     * Print the main menu
     */
    public void printMainMenu() {
        System.out.println("View Appointments");
    }

    /**
     * @param appointmentList
     */
    @Override
    public void printAllAppointments(List<Appointment> appointmentList){
        for (Appointment appointment : appointmentList) {
            System.out.printf("%-14s | %-9s | %-9s | %-9s | %s | %s%n",
                            appointment.getAppointmentID(),
                            appointment.getPatientID(),
                            appointment.getStaffID(),
                            appointment.getStatus(),
                            appointment.getDate(),
                            appointment.getTime());
        }
    }
}
