import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;


public class DoctorUI implements UserUI {
    private Doctor doctor;
    private Register register;
    MedicalRecordUI medicalRecordUI;
    DoctorAppointmentUI doctorAppointmentUI;
    
    String AppointmentFilePath;

    public DoctorUI(Doctor doctor) throws IOException{
        this.doctor = doctor;
        medicalRecordUI=new MedicalRecordUI("MedicalRecord.txt", "Patient.txt", "OverseeingPatients.txt", doctor.getId());
        doctorAppointmentUI=new DoctorAppointmentUI(doctor);
        
    }

    public void printMenu() {
        System.out.println("1. View Patient List");
        System.out.println("2. Update Patient Medical Records");
        System.out.println("3. View Personal Schedule");
        System.out.println("4. Set Availability for Appointments");
        System.out.println("5. Accept or Decline Appointment");
        System.out.println("6. View Upcoming Appointments");
        System.out.println("7. Record Appointment Outcome");
        System.out.println("8. Register New User"); // New option
        System.out.println("9. Log Out");
    }

    public void navigateMenu(int option) {
        Scanner sc=new Scanner(System.in);
        int index,choice;
        switch (option) {
            case 1:
                medicalRecordUI.ViewOverseeingPatients();
                // viewPatientList();
                break;
            case 2:
                medicalRecordUI.UpdatePatientMedicalRecords();
                // updatePatientMedicalRecords();
                break;
            case 3:
                doctorAppointmentUI.ViewDoctorPersonalSchedule();
                break;
            case 4:
                doctorAppointmentUI.SetAvailability();
                break;
            case 5:
                doctorAppointmentUI.ManagePendingAppointments();
                // acceptOrDeclineAppointment();
                break;
            case 6:
                doctorAppointmentUI.ViewUpcomingAppointments();
                // viewUpcomingAppointments();
                break;
            case 7:
                doctorAppointmentUI.RecordAppointmentOutcome();
                // recordAppointmentOutcome();
                break;
            case 8:
                break;
            case 9:
                System.out.println("Logging out...");
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    public void skeletonPatientList() {
        System.out.println("Skeleton for Patient List");
    }

    public void skeletonAppointment() {
        System.out.println("Skeleton for Appointment");
    }
}
