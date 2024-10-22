public class DoctorUI implements UserUI {
    private Doctor doctor;

    public DoctorUI(Doctor doctor) {
        this.doctor = doctor;
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
        switch (option) {
            case 1:
                skeletonPatientList(); // viewPatientList();
                break;
            case 2:
                skeletonPatientList();// updatePatientMedicalRecords();
                break;
            case 3:
                skeletonAppointment();// viewPersonalSchedule();
                break;
            case 4:
                skeletonAppointment();// setAvailabilityForAppointments();
                break;
            case 5:
                skeletonAppointment();// acceptOrDeclineAppointment();
                break;
            case 6:
                skeletonAppointment();// viewUpcomingAppointments();
                break;
            case 7:
                skeletonAppointment();// recordAppointmentOutcome();
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
