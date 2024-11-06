import java.io.*;
import java.util.ArrayList;
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

    public String getDateOfAppointment() {
        return dateOfAppointment;
    }

    public String getService() {
        return service;
    }

    public String getConsultationNotes() {
        return consultationNotes;
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

    public static void viewAppointmentOutcomeRecords(String patientID) {
        List<Appointment> completedAppointments;
        try {
            // Load appointments and filter only completed ones for the patient
            completedAppointments = TextFileReader.loadAppointments("./TextFiles/Appointment_List.txt");
            completedAppointments = filterCompletedAppointments(completedAppointments, patientID);

            if (completedAppointments.isEmpty()) {
                System.out.println("No completed appointments found for patient " + patientID);
                return;
            }

            List<AppointmentOutcome> outcomes = TextFileReader.loadAppointmentOutcomes("./TextFiles/AppointmentOutcome_List.txt");
            System.out.println("Past Appointment Outcomes:");
            System.out.println("Appointment ID | Date       | Type         | Medication       | Status    | Consultation Notes");

            for (Appointment completed : completedAppointments) {
                for (AppointmentOutcome outcome : outcomes) {
                    if (outcome.getAppointmentId() == completed.getAppointmentID()) {
                        System.out.printf("%-14d | %-10s | %-12s | %-16s | %-9s | %s%n",
                                          outcome.getAppointmentId(),
                                          outcome.getDateOfAppointment(),
                                          outcome.getService(),
                                          outcome.getMedicine(),
                                          outcome.isStatus() ? "Dispensed" : "Pending",
                                          outcome.getConsultationNotes());
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error processing files: " + e.getMessage());
        }
    }

    private static List<Appointment> filterCompletedAppointments(List<Appointment> appointments, String patientID) {
        List<Appointment> completedAppointments = new ArrayList<>();

        for (Appointment appointment : appointments) {
            if (appointment.getPatientID().equals(patientID) && appointment.getStatus().equals("COMPLETED")) {
                completedAppointments.add(appointment);
            }
        }

        return completedAppointments;
    }
    
}   