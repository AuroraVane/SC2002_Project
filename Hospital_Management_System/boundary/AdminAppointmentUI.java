package boundary;

import entity.Appointment;
import java.util.List;

/**
 *
 */
public class AdminAppointmentUI extends AppointmentUI{
    /**
     *
     */
    public AdminAppointmentUI() {
    }

    /**
     *
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
