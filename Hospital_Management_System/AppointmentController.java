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

    public void viewAppointmentOutcomeRecord() {
        List<AppointmentOutcome> appointmentOutcomes = AppointmentOutcome.getAllAppointmentOutcomes();
        
        for (AppointmentOutcome appointmentOutcome : appointmentOutcomes){
            appointmentOutcome.printAppointmentOutcome();
        }
    }

    public void viewAvailableAppointmentSlots(){
        List<Appointment> appointments;
        try {
            appointments = TextFileReader.loadAppointments("./TextFiles/Appointment_List.txt");
            for (Appointment appointment : appointments) {
                // Check if the appointment has no patient assigned (patientID is "NA")
                if (appointment.getPatientID().equals("NA")) {
                    System.out.printf("%-14s | %-9s | %s | %s%n",
                                    appointment.getAppointmentID(),
                                    appointment.getStaffID(),
                                    appointment.getDate(),
                                    appointment.getTime());
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
