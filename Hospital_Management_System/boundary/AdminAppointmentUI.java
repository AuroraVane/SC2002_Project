package boundary;

import java.util.List;

import entity.Appointment;
import utils.TextFileReader;

public class AdminAppointmentUI extends AppointmentUI{

    public AdminAppointmentUI() {
    }

    public void printMainMenu() {
        System.out.println("View Appointments");
    }
    @Override
    public void printAllAppointments(List<Appointment> appointmentList){
        for (Appointment appointment : appointmentList) {
            System.out.printf("%-14s | %-9s | %s | %s%n",
                            appointment.getAppointmentID(),
                            appointment.getPatientID(),
                            appointment.getStaffID(),
                            appointment.getStatus(),
                            appointment.getDate(),
                            appointment.getTime());
        }
    }
}
