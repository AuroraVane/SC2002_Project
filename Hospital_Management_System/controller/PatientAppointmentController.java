package controller;
import java.io.IOException;
import java.util.List;
import entity.Appointment.Status;
import entity.Appointment;
import utils.TextFileReader;
import utils.TextFileWriter;

/**
 * Controller class for PatientAppointmentUI 
 */
public class PatientAppointmentController{
    private static final String APPOINTMENT_FILE_PATH = "./TextFiles/Appointment_List.txt";

    /**
     * Constructor for PatientAppointmentController
     */
    public PatientAppointmentController(){
    }

    /**
     * Schedule Appointment
     * @param patientID
     * @param appointmentID
     * @return
     */
    public boolean scheduleAppointment(String patientID, int appointmentID) {
        List<Appointment> appointments;
        try {
            appointments = TextFileReader.loadAppointments(this.APPOINTMENT_FILE_PATH);
        } catch (IOException e) {
            System.out.println("Error loading appointments: " + e.getMessage());
            return false;
        }

        // Find the appointment with the given appointmentID
        Appointment appointmentToBook = null;
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentID() == appointmentID && appointment.getPatientID().equals("NA")) {
                appointmentToBook = appointment;
                break;
            }
        }

        if (appointmentToBook != null) {
            // Update the appointment details with patientID and set status to "PENDING"
            TextFileWriter.updateOverseeingPatient(appointmentToBook.getStaffID(), patientID);
            appointmentToBook.setPatientID(patientID);
            appointmentToBook.setStatus(Status.PENDING);

            // Update the appointment in the file
            TextFileWriter.updateAppointment(appointmentToBook);
            System.out.println("Appointment scheduled successfully for patient " + patientID);
            return true;
        } else {
            System.out.println("Appointment not available or already booked.");
            return false;
        }
        
    }

    


}
