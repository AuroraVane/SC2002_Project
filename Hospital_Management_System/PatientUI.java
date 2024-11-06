import java.io.IOException;

public class PatientUI implements UserUI {
    private Patient patient;

    //private MedicalRecord medicalRecord;
    private PatientNonMedicalRecordUI nonmedicalrecordUI;
    private PatientMedicalRecordUI medicalrecordUI;

    public PatientUI(Patient patient) {
        this.patient = patient;
        try {
            this.medicalrecordUI = new PatientMedicalRecordUI("./TextFiles/MedicalRecord.txt", "./TextFiles/Patient_List.txt", patient.getId()); 
        } catch (IOException e) {
            System.out.println("Error: Unable to load list from file.");
            e.printStackTrace();
        }
        this.nonmedicalrecordUI = new PatientNonMedicalRecordUI(patient); 
    }

    public void printMenu() {
        System.out.println("1. View Medical Record");
        System.out.println("2. Update Personal Information");
        System.out.println("3. View Available Appointments Slots");
        System.out.println("4. Schedule an Appointment");
        System.out.println("5. Reschedule an Appointment");
        System.out.println("6. Cancel an Appointment");
        System.out.println("7. View Scheduled Appointments");
        System.out.println("8. View Past APpointment Outcome Records");
        System.out.println("9. Log Out");
    }

    public void navigateMenu(int option) {
        switch (option) {
            case 1:
                medicalrecordUI.viewMedicalRecord(); // viewMedicalRecord();
                break;
            case 2:
                nonmedicalrecordUI.updateContactInfo(); // updatePersonalInformation();
                break;
            case 3:
                skeletonAppointment();// viewAvailableAppointmentSlots();
                break;
            case 4:
                skeletonAppointment();// scheduleAppointment();
                break;
            case 5:
                skeletonAppointment();// rescheduleAppointment();
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

    public void skeletonAppointment() {
        System.out.println("Skeleton for Appointment");
    }
}
