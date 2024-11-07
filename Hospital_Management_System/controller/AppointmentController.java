package controller;
import java.io.IOException;
import java.util.List;

import entity.Appointment;
import boundary.AppointmentUI;
import utils.TextFileReader;

public class AppointmentController {
    private List<Appointment> appointmentList;
    private AppointmentUI appointmentUI;

    public AppointmentController(String fileName) throws IOException {
        appointmentList = TextFileReader.loadAppointments(fileName);
        this.appointmentUI = new AppointmentUI();
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
