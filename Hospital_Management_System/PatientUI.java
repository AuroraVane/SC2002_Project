public class PatientUI implements UserUI{
    private Patient patient;
    public PatientUI(Patient patient){
        this.patient = patient;
    }
    public void printMenu(){
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
}
