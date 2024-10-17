public class AdminstratorUI implements UserUI{
    public void printMenu(){
        System.out.println("1. View Patient List"); // View and Update Patient List
        System.out.println("2. View Personal Schedule"); // View, Set Availability for Appointments, Accept or Decline Appointment
        System.out.println("3. Record Appointment Outcome"); // Record Appointment Outcome
    }
}
