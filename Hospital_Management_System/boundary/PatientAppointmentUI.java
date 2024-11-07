package boundary;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.AppointmentOutcome;
import controller.PatientAppointmentController;
import entity.Appointment;
import entity.Patient;
import utils.TextFileReader;
import utils.TextFileWriter;

public class PatientAppointmentUI{
    private PatientAppointmentController appointmentcontroller;
    private static final String APPOINTMENT_FILE_PATH = "./TextFiles/Appointment_List.txt";
    public PatientAppointmentUI() {
        this.appointmentcontroller = new PatientAppointmentController(); 
    }

    public void scheduleAppointment(Patient patient) {
        System.out.println("Enter the appointment ID you would like to schedule your appointment:");
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        int appointmentId = sc.nextInt();
        appointmentcontroller.scheduleAppointment(patient.getId(), appointmentId);
    }
    
    public void rescheduleAppointment(String patientID) {
        try {
            if (!viewScheduledAppointments(patientID)) {
                return;
            }

            // Prompt the user to select an appointment to reschedule
            @SuppressWarnings("resource")
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the Appointment ID you wish to reschedule: ");
            int appointmentIDToReschedule = Integer.parseInt(scanner.nextLine());

            // Check if the entered appointment ID is valid
            Appointment appointmentToReschedule = null;
            List<Appointment> appointments = TextFileReader.loadAppointments(APPOINTMENT_FILE_PATH);
            for (Appointment appointment : appointments) {
                if (appointment.getAppointmentID() == appointmentIDToReschedule &&
                    appointment.getPatientID().equals(patientID) &&
                    (appointment.getStatus().equals("PENDING") || appointment.getStatus().equals("CONFIRMED"))) {
                    appointmentToReschedule = appointment;
                    break;
                }
            }

            if (appointmentToReschedule == null) {
                System.out.println("Invalid Appointment ID or appointment does not belong to patient " + patientID);
                return;
            }

            // Display available slots
            viewAvailableAppointmentSlots();

            // Prompt the user to select a new appointment slot
            System.out.print("Enter the new Appointment ID for rescheduling: ");
            int newAppointmentID = Integer.parseInt(scanner.nextLine());

            // Update the existing appointment to mark it as "RESCHEDULED" (optional, if you want to track this)
            appointmentToReschedule.setPatientID("NA");
            appointmentToReschedule.setStatus("EMPTY");
            TextFileWriter.updateAppointment(appointmentToReschedule);

            // Schedule the new appointment with "PENDING" status for the patient
            appointmentcontroller.scheduleAppointment(patientID, newAppointmentID);

            System.out.println("Appointment rescheduled successfully for patient " + patientID);

        } catch (IOException e) {
            System.out.println("Error processing file: " + e.getMessage());
        }
    }

    public void cancelAppointment(String patientID) {
        try {
            if (!viewScheduledAppointments(patientID)) {
                return;
            }

            // Prompt the user to select an appointment to cancel
            @SuppressWarnings("resource")
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the Appointment ID you wish to cancel: ");
            int appointmentIDToCancel = Integer.parseInt(scanner.nextLine());

            // Check if the entered appointment ID is valid
            List<Appointment> appointments = TextFileReader.loadAppointments(APPOINTMENT_FILE_PATH);
            Appointment appointmentToCancel = null;
            for (Appointment appointment : appointments) {
                if (appointment.getAppointmentID() == appointmentIDToCancel &&
                    appointment.getPatientID().equals(patientID) &&
                    (appointment.getStatus().equals("PENDING") || appointment.getStatus().equals("CONFIRMED"))) {
                    appointmentToCancel = appointment;
                    break;
                }
            }

            if (appointmentToCancel == null) {
                System.out.println("Invalid Appointment ID or appointment does not belong to patient " + patientID);
                return;
            }

            // Update the appointment details to cancel it
            appointmentToCancel.setPatientID("NA");
            appointmentToCancel.setStatus("EMPTY");
            TextFileWriter.updateAppointment(appointmentToCancel);

            System.out.println("Appointment canceled successfully for patient " + patientID);

        } catch (IOException e) {
            System.out.println("Error processing file: " + e.getMessage());
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

    

    public boolean viewScheduledAppointments(String patientID){
        List<Appointment> appointments;
        boolean foundAppointments = false;
        try {
            appointments = TextFileReader.loadAppointments(APPOINTMENT_FILE_PATH);
    
            System.out.println("Your Pending and Confirmed Appointments:");
            System.out.println("Appointment ID | Doctor ID | Date       | Time");
    
            for (Appointment appointment : appointments) {
                if (appointment.getPatientID().equals(patientID) &&
                    (appointment.getStatus().equals("PENDING") || appointment.getStatus().equals("CONFIRMED"))) {
                    
                    System.out.printf("%-14s | %-9s | %s | %s%n",
                                      appointment.getAppointmentID(),
                                      appointment.getStaffID(),
                                      appointment.getDate(),
                                      appointment.getTime());
                    foundAppointments = true;
                }
            }
    
            if (!foundAppointments) {
                System.out.println("No PENDING or CONFIRMED appointments found for patient " + patientID);
            }
    
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return foundAppointments;
    }
    private List<Appointment> filterCompletedAppointments(List<Appointment> appointments, String patientID) {
        List<Appointment> completedAppointments = new ArrayList<>();

        for (Appointment appointment : appointments) {
            if (appointment.getPatientID().equals(patientID) && appointment.getStatus().equals("COMPLETED")) {
                completedAppointments.add(appointment);
            }
        }

        return completedAppointments;
    }
    
    public void viewAppointmentOutcomeRecords(String patientID) {
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
}
