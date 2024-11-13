package boundary;
import controller.AppointmentOutcomeController;
import controller.PatientAppointmentController;
import entity.Appointment;
import entity.Appointment.Status;
import entity.AppointmentOutcome;
import entity.Patient;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import utils.TextFileReader;
import utils.TextFileWriter;

/**
 * UI class for PatientAppointmentUI used by PatientAppointmentController
 */
public class PatientAppointmentUI extends AppointmentUI{
    private PatientAppointmentController appointmentcontroller;
    private static final String APPOINTMENT_FILE_PATH = "./TextFiles/Appointment_List.txt";

    /**
     * Constructor for PatientAppointmentUI class
     */
    public PatientAppointmentUI() {
        this.appointmentcontroller = new PatientAppointmentController(); 
    }

    /**
     * @param appointment
     */
    public void printAppointment(Appointment appointment){
        
        System.out.printf("%-14s | %-14s | %s | %s | %s%n",
                        appointment.getAppointmentID(),
                        TextFileReader.findUserName(appointment.getStaffID(), "Doctor"),
                        appointment.getDate(),
                        appointment.getTime(),
                        appointment.getStatus().toString());
    }

    /**
     * @param appointmentList
     */
    @Override
    public void printAllAppointments(List<Appointment> appointmentList){
        System.out.println("Appointment ID |     Doctor     | Date     | Time | Status");
        for (Appointment appointment : appointmentList) {
            printAppointment(appointment);
        }
    }

    /**
     * @param patient
     */
    public void scheduleAppointment(Patient patient) {
        System.out.println("Enter the appointment ID you would like to schedule your appointment:");
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        int appointmentId = sc.nextInt();
        appointmentcontroller.scheduleAppointment(patient.getId(), appointmentId);
    }

    /**
     * @param patientID
     */
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
                    (appointment.getStatus().equals(Status.PENDING) || appointment.getStatus().equals(Status.CONFIRMED))) {
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

            // Schedule the new appointment with "PENDING" status for the patient
            if(appointmentcontroller.scheduleAppointment(patientID, newAppointmentID)){
                // Update the existing appointment to mark it as "RESCHEDULED" (optional, if you want to track this)
                appointmentToReschedule.setPatientID("NA");
                appointmentToReschedule.setStatus(Status.EMPTY);
                TextFileWriter.updateAppointment(appointmentToReschedule);
            }


        } catch (IOException e) {
            System.out.println("Error processing file: " + e.getMessage());
        }
    }

    /**
     * @param patientID
     */
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
                    (appointment.getStatus().equals(Status.PENDING) || appointment.getStatus().equals(Status.CONFIRMED))) {
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
            appointmentToCancel.setStatus(Status.EMPTY);
            TextFileWriter.updateAppointment(appointmentToCancel);

            System.out.println("Appointment canceled successfully for patient " + patientID);

        } catch (IOException e) {
            System.out.println("Error processing file: " + e.getMessage());
        }
    }

    /**
     * View available appointment slots for scheduling
     */
    public void viewAvailableAppointmentSlots(){
        List<Appointment> appointments;
        try {
            appointments = TextFileReader.loadAppointments("./TextFiles/Appointment_List.txt");
            System.out.println("Appointment ID |     Doctor     | Date     | Time | Status");
            for (Appointment appointment : appointments) {
                // Check if the appointment has no patient assigned (patientID is "NA")
                if (appointment.getPatientID().equals("NA")) {
                    printAppointment(appointment);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @param patientID
     * @return
     */
    public boolean viewScheduledAppointments(String patientID){
        List<Appointment> appointments;
        boolean foundAppointments = false;
        try {
            appointments = TextFileReader.loadAppointments(APPOINTMENT_FILE_PATH);
    
            System.out.println("Your Pending and Confirmed Appointments:");
            System.out.println("Appointment ID |     Doctor     | Date     | Time | Status");
            for (Appointment appointment : appointments) {
                if (appointment.getPatientID().equals(patientID) &&
                    (appointment.getStatus().equals(Status.PENDING) || appointment.getStatus().equals(Status.CONFIRMED))) {
                    printAppointment(appointment);
                    foundAppointments = true;
                }
            }
    
            if (!foundAppointments) {
                System.out.println("No PENDING or CONFIRMED appointments found for patient " + patientID);
            }
    
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return foundAppointments;
    }

    /**
     * @param appointments
     * @param patientID
     * @return
     */
    private List<Appointment> filterCompletedAppointments(List<Appointment> appointments, String patientID) {
        List<Appointment> completedAppointments = new ArrayList<>();

        for (Appointment appointment : appointments) {
            if (appointment.getPatientID().equals(patientID) && appointment.getStatus().equals(Status.COMPLETED)) {
                completedAppointments.add(appointment);
            }
        }

        return completedAppointments;
    }

    /**
     * @param patientID
     */
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

            List<AppointmentOutcome> outcomes = AppointmentOutcomeController.getAllAppointmentOutcomes();
            System.out.println("Past Appointment Outcomes:");
            System.out.println("Appointment ID | Date       | Type         | Medication       | Status    | Consultation Notes");

            for (Appointment completed : completedAppointments) {
                for (AppointmentOutcome outcome : outcomes) {
                    if (outcome.getAppointmentId() == completed.getAppointmentID()) {
                        outcome.printAppointmentOutcome();
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error processing files: " + e.getMessage());
        }
    }
}
