package controller;
import boundary.AdminAppointmentUI;
import entity.Appointment;
import java.io.IOException;
import java.util.List;
import utils.TextFileReader;

/**
 * Controller class for AdminAppointmentUI 
 */
public class AdminAppointmentController{
    private List<Appointment> appointmentList;
    private AdminAppointmentUI appointmentUI;

    /**
     * Admin Appointment Controller constructor
     * @param fileName
     * @throws IOException
     */
    public AdminAppointmentController(String fileName) throws IOException {
        appointmentList = TextFileReader.loadAppointments(fileName);
        this.appointmentUI = new AdminAppointmentUI();
    }

    /**
     * Controller for the menu
     */
    public void MenuController() {
        appointmentUI.printMainMenu();
        appointmentUI.printAllAppointments(appointmentList);
    }

    

}
