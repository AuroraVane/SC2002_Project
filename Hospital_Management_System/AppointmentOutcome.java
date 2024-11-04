import java.io.*;
import java.util.List;

public class AppointmentOutcome {   
    private int appointmentId; 
    private String dateOfAppointment;
    private String service;
    private String medicine;
    private boolean status;
    private String consultationNotes;

    public AppointmentOutcome(int appointmentId, String dateOfAppointment, String service, String medicine, String consultationNotes){
        this.appointmentId = appointmentId;
        this.dateOfAppointment = dateOfAppointment;
        this.service = service;
        this.medicine = medicine;
        this.status = false;
        this.consultationNotes = consultationNotes;
    }

    public AppointmentOutcome(int appointmentId, String dateOfAppointment, String service, String medicine, boolean status, String consultationNotes){
        this.appointmentId = appointmentId;
        this.dateOfAppointment = dateOfAppointment;
        this.service = service;
        this.medicine = medicine;
        this.status = status;
        this.consultationNotes = consultationNotes;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public String getMedicine() {
        return medicine;
    }

    public boolean isStatus() {
        return status;
    }

    public static List<AppointmentOutcome> getAllAppointmentOutcomes(){
        String filePath = "./TextFiles/AppointmentOutcome_List.txt";
        List<AppointmentOutcome> appointmentOutcomes;
        try {
            appointmentOutcomes = TextFileReader.loadAppointmentOutcomes(filePath);
            return appointmentOutcomes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void updateAppointmentOutcomeStatus(int appointmentId){
        List<AppointmentOutcome> appointmentOutcomes = getAllAppointmentOutcomes();
        for (AppointmentOutcome appointmentOutcome : appointmentOutcomes){
            if (appointmentOutcome.appointmentId == appointmentId){
                appointmentOutcome.status = true;
            }
        }
        updateAppointmentOutcomeFile(appointmentOutcomes);
    }

    @Override
    public String toString() {
        return appointmentId + "|" + dateOfAppointment + "|" + service + "|" + medicine + "|" + status + "|" + consultationNotes;
    }

    public static void updateAppointmentOutcomeFile(List<AppointmentOutcome> appointmentOutcomes) {
        String filePath = "./TextFiles/AppointmentOutcome_List.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (AppointmentOutcome appointmentOutcome : appointmentOutcomes) {
                writer.write(appointmentOutcome.toString());
                writer.newLine();
            }
            System.out.println("File updated successfully.");
        } catch (IOException e) {
            System.err.println("Error updating the file: " + e.getMessage());
        }
    }

    public void printAppointmentOutcome(){
        System.out.println("AppointmentID: " + appointmentId);
        System.out.println("Date of Appointment: " + dateOfAppointment);
        System.out.println("Service provided: " + service);
        System.out.println("Medicine Required: " + medicine);
        System.out.println((status == false) ? "Status: Pending": "Status: Dispensed");
        System.out.println("Consultation Notes: " + consultationNotes);
        System.out.println();
    }
    
}   