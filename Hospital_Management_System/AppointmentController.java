import java.util.List;
import java.io.IOException;

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

}
