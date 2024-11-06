import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class PatientUI implements UserUI {
    private Patient patient;

    public PatientUI(Patient patient) {
        this.patient = patient;
    }

    public void printMenu() {
        System.out.println("1. View Medical Record");
        System.out.println("2. Update Personal Information");
        System.out.println("3. View Available Appointments Slots");
        System.out.println("4. Schedule an Appointment");
        System.out.println("5. Reschedule an Appointment");
        System.out.println("6. Cancel an Appointment");
        System.out.println("7. View Scheduled Appointments");
        System.out.println("8. View Past Appointment Outcome Records");
        System.out.println("9. Log Out");
    }

    public void navigateMenu(int option) {
        switch (option) {
            case 1:
                MedicalRecord.viewMedicalRecord(patient);
                break;
            case 2:
                updateContactInfo();
                break;
            case 3:
                Appointment.viewAvailableAppointmentSlots();
                break;
            case 4:
                scheduleAppointment();// scheduleAppointment();
                break;
            case 5:
                Appointment.rescheduleAppointment(patient.getId());// rescheduleAppointment();
                break;
            case 6:
                skeletonAppointment();// cancelAppointment();
                break;
            case 7:
                skeletonAppointment();// viewScheduledAppointments();
                break;
            case 8:
                skeletonAppointment();// viewAppointmentOutcomeRecords();
                break;
            case 9:
                System.out.println("Logging out...");
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    public void updateContactInfo(){
        System.out.println("Enter new contact information:");
        Scanner sc = new Scanner(System.in);
        String newContactInfo = sc.nextLine();
        TextFileWriter.updatePatientEmail(patient.getId(), newContactInfo);
        patient.setContactInfo(newContactInfo);
    }

    public void scheduleAppointment() {
        System.out.println("Enter the appointment ID you would like to schedule your appointment:");
        Scanner sc = new Scanner(System.in);
        int appointmentId = sc.nextInt();
        Appointment.scheduleAppointment(patient.getId(), appointmentId);
    }
    
    public void skeletonAppointment() {
        System.out.println("Skeleton for Appointment");
    }
}
