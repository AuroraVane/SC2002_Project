import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Scanner;
public class Appointment {
    private String patientID;
    private String staffID;
    private String status;
    private String date;
    private String time;
    private int appointmentID;
    private static final String APPOINTMENT_FILE_PATH = "./TextFiles/Appointment_List.txt";


    public int getAppointmentID() {
        return appointmentID;
    }
    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }
    public Appointment(int appointmentID, String patientID, String staffID, String status, String date, String time) {
        //TODO Auto-generated constructor stub
        this.patientID = patientID;
        this.staffID = staffID;
        this.status = status;
        this.date = date;
        this.time = time;
        this.appointmentID=appointmentID;
        //generates random appointment unique ID based on current time stamps
        
    }
    public Appointment(String staffID, String status, String date, String time) {
        this.patientID = "NA";
        this.staffID = staffID;
        this.status = status;
        this.date = date;
        this.time = time;
        this.appointmentID=generateID();
        //generates random appointment unique ID based on current time stamps
        
    }
    public static int generateID() {
        return (int) (Instant.now().toEpochMilli() % Integer.MAX_VALUE);
    }
    public String getPatientID() {
        return patientID;
    }
    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }
    public String getStaffID() {
        return staffID;
    }
    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public static void scheduleAppointment(String patientID, int appointmentID) {
        List<Appointment> appointments;
        try {
            appointments = TextFileReader.loadAppointments(APPOINTMENT_FILE_PATH);
        } catch (IOException e) {
            System.out.println("Error loading appointments: " + e.getMessage());
            return;
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
            appointmentToBook.setPatientID(patientID);
            appointmentToBook.setStatus("PENDING");

            // Update the appointment in the file
            TextFileWriter.updateAppointment(appointmentToBook);
            System.out.println("Appointment scheduled successfully for patient " + patientID);
        } else {
            System.out.println("Appointment not available or already booked.");
        }
    }

    public static void viewAvailableAppointmentSlots(){
        List<Appointment> appointments;
        try {
            appointments = TextFileReader.loadAppointments(APPOINTMENT_FILE_PATH);
            Patient.showAvailableAppointments(appointments);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void rescheduleAppointment(String patientID) {
        try {
            List<Appointment> appointments = TextFileReader.loadAppointments(APPOINTMENT_FILE_PATH);
            boolean foundAppointments = false;

            System.out.println("Your Pending and Confirmed Appointments:");
            System.out.println("Appointment ID | Doctor ID | Date       | Time");

            // Display the patient's PENDING and CONFIRMED appointments
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
                return;
            }

            // Prompt the user to select an appointment to reschedule
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the Appointment ID you wish to reschedule: ");
            int appointmentIDToReschedule = Integer.parseInt(scanner.nextLine());

            // Check if the entered appointment ID is valid
            Appointment appointmentToReschedule = null;
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
            scheduleAppointment(patientID, newAppointmentID);

            System.out.println("Appointment rescheduled successfully for patient " + patientID);

        } catch (IOException e) {
            System.out.println("Error processing file: " + e.getMessage());
        }
    }

    public static void cancelAppointment(String patientID) {
        try {
            List<Appointment> appointments = TextFileReader.loadAppointments(APPOINTMENT_FILE_PATH);
            boolean foundAppointments = false;

            System.out.println("Your Pending and Confirmed Appointments:");
            System.out.println("Appointment ID | Doctor ID | Date       | Time");

            // Display the patient's PENDING and CONFIRMED appointments
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
                return;
            }

            // Prompt the user to select an appointment to cancel
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the Appointment ID you wish to cancel: ");
            int appointmentIDToCancel = Integer.parseInt(scanner.nextLine());

            // Check if the entered appointment ID is valid
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

}