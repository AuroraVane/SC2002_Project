import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;


public class DoctorUI implements UserUI {
    private Doctor doctor;
    private Register register;
    private DoctorAppointmentManager AM;
    private MedicalRecordManager MRM;
    String AppointmentFilePath;

    public DoctorUI(Doctor doctor, String AppointmentFilePath) {
        this.doctor = doctor;
        AM=new DoctorAppointmentManager(AppointmentFilePath, doctor);
        MRM=new MedicalRecordManager();
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
                MRM.viewOverseeingPatients(doctor.getId());// viewPatientList();
                break;
            case 2:
                System.out.println("Select which Patient's Medical Record to view");
                MRM.viewOverseeingPatients(doctor.getId());
                index=sc.nextInt();
                Patient p=MRM.getOverseeingPatient(index);
                System.out.println("Select what you want to edit:");
                System.out.println("1. Diagnosis");
                System.out.println("2. Treatment Plans");
                choice=sc.nextInt();
                switch(choice){
                    case 1:MRM.EditMedicalTxt(p.getId(), "DIAGNOSIS");
                    case 2:MRM.EditMedicalTxt(p.getId(), "TERATMENT_PLANS"); 
                    default:System.out.println("Set filename for consultation notes");
                }

                // updatePatientMedicalRecords();
                break;
            case 3:
                AM.viewDoctorPersonalSchedule();// viewPersonalSchedule();
                break;
            case 4:
                int error=0;
                while (error==0){
                    error=0;
                    System.out.println("Enter date in format YYYY-MM-DD");
                    String dateStr=sc.nextLine();
                    System.out.println("Give starting Time in format HH:MM:SS");
                    String startTimeStr=sc.nextLine();
                    System.out.println("Give ending Time in format HH:MM:SS");
                    String endTimeStr=sc.nextLine();
                    System.out.println("Set filename for consultation notes");
                    String filepath=sc.nextLine();

                    LocalDate date = null;
                    LocalTime startTime = null;
                    LocalTime endTime = null;

                    try {
                        date = LocalDate.parse(dateStr);
                        startTime = LocalTime.parse(startTimeStr);
                        endTime = LocalTime.parse(endTimeStr);
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid date or time format. Please try again.");
                        error=1;
                        continue;
                    }
                    AM.DoctorSet(date, startTime, endTime, doctor.getId(), filepath);
                }
                break;
            case 5:
                System.out.println("Select which Pending Slot you want to review");
                AM.ViewPendingList();
                index=sc.nextInt();
                System.out.println("Enter 1 to accept and 0 to decline");
                choice=sc.nextInt();
                switch(choice){
                    case 1: AM.DoctorAccept(doctor.getId(), index);
                    case 2: AM.DoctorDecline(doctor.getId(), index);
                    default: System.out.println("Error");
                }
                
                // acceptOrDeclineAppointment();
                break;
            case 6:
                AM.viewDoctorUpcoming();// viewUpcomingAppointments();
                break;
            case 7:
                AM.viewConfirmedAppointments();
                System.out.println("Select which Confirmed Appointment you want to conclude");
                index=sc.nextInt();
                System.out.println("Enter treatement type");
                String treatment=sc.nextLine();
                System.out.println("Set FileName for Consultation Notes");
                System.out.println("Write Consultation Notes:");
                System.out.println("Enter 0 to exit from writing");
                AM.FinishAppointment(index, treatment);// recordAppointmentOutcome();
                break;
            case 8:
                register.showRegistrationForm(doctor);
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
