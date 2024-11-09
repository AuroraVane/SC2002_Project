package controller;
import boundary.AdminAppointmentUI;
import entity.Appointment;
import java.io.IOException;
import java.util.List;
import utils.TextFileReader;

public class AdminAppointmentController{
    private List<Appointment> appointmentList;
    private AdminAppointmentUI appointmentUI;

    public AdminAppointmentController(String fileName) throws IOException {
        appointmentList = TextFileReader.loadAppointments(fileName);
        this.appointmentUI = new AdminAppointmentUI();
    }

    public void MenuController() {
        appointmentUI.printMainMenu();
        appointmentUI.printAllAppointments(appointmentList);
    }

    public void viewAppointmentOutcomeRecord() {
        List<AppointmentOutcome> appointmentOutcomes = AppointmentOutcome.getAllAppointmentOutcomes();
        for (AppointmentOutcome appointmentOutcome : appointmentOutcomes){
            appointmentOutcome.printAppointmentOutcome();
        }
    }

}
