import java.util.Scanner;

public class PatientUI implements UserUI {
    private Patient patient;
    private MedicalRecord medicalRecord;
    private AppointmentController appointmentController;

    public PatientUI(Patient patient) {
        this.patient = patient;
        this.medicalRecord = patient.getMedicalRecord();
        this.appointmentController = patient.getAppointmentController();
    }

    public void printMenu() {
        System.out.println("1. View Medical Record");
        System.out.println("2. Update Personal Information");
        System.out.println("3. View Available Appointment Slots");
        System.out.println("4. Schedule an Appointment");
        System.out.println("5. Reschedule an Appointment");
        System.out.println("6. Cancel an Appointment");
        System.out.println("7. View Scheduled Appointments");
        System.out.println("8. View Past Appointment Outcome Records");
        System.out.println("9. View Appointment Details");
        System.out.println("10. Log Out");
    }

    public void navigateMenu(int option) {
        Scanner sc = new Scanner(System.in);
        switch (option) {
            case 1:
                medicalRecord.viewMedicalRecord();
                break;
            case 2:
                System.out.println("Enter new contact information:");
                String newContactInfo = sc.nextLine();
                patient.setContactInfo(newContactInfo);
                break;
            case 3:
                appointmentController.viewAvailableSlots(new Doctor()); // Placeholder for doctor instantiation
                break;
            case 4:
                System.out.println("Enter doctor, date, and time for the appointment:");
                String doctorName = sc.nextLine(); // Placeholder for doctor
                String date = sc.nextLine();
                String time = sc.nextLine();
                Doctor doctor = new Doctor(); // probs need a find doc method
                appointmentController.scheduleAppointment(patient, doctor, date, time);
                break;
            case 5:
                System.out.println("Enter doctor's name, date, and time of the appointment to reschedule:");
                Doctor rescheduleDoctor = new Doctor(); // Placeholder logic to instantiate Doctor
                String rescheduleDate = sc.nextLine();
                String rescheduleTime = sc.nextLine();
                Appointment appointmentToReschedule = appointmentController.findAppointment(patient, rescheduleDoctor, rescheduleDate, rescheduleTime);
                if (appointmentToReschedule != null) {
                    System.out.println("Enter new date:");
                    String newDate = sc.nextLine();
                    System.out.println("Enter new time:");
                    String newTime = sc.nextLine();
                    appointmentController.rescheduleAppointment(appointmentToReschedule, newDate, newTime);
                } else {
                    System.out.println("Appointment not found.");
                }
                break;
            case 6:
                System.out.println("Enter doctor's name, date, and time of the appointment to cancel:");
                Doctor cancelDoctor = new Doctor(); // Placeholder logic to instantiate Doctor
                String cancelDate = sc.nextLine();
                String cancelTime = sc.nextLine();
                Appointment appointmentToCancel = appointmentController.findAppointment(patient, cancelDoctor, cancelDate, cancelTime);
                if (appointmentToCancel != null) {
                    appointmentController.cancelAppointment(appointmentToCancel);
                } else {
                    System.out.println("Appointment not found.");
                }
                break;
            case 7:
                appointmentController.viewScheduledAppointments(patient);
                break;
            case 8:
                appointmentController.viewPastAppointments(patient);
                break;
            case 9:
                System.out.println("Enter appointment to view details (doctor, date, and time):");
                Appointment detailAppointment = new Appointment(patient, new Doctor(), "specificDate", "specificTime");
                appointmentController.viewAppointmentDetails(detailAppointment);
                break;
            case 10:
                System.out.println("Logging out...");
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }
}
