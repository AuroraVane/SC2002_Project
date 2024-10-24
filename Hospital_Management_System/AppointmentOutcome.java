public class AppointmentOutcome {   
    private int appointmentId; 
    private String dateOfAppointment;
    private String service;
    private String medicine;
    private boolean status;
    private String consultationNotes;

    public AppointmentOutcome(int appointmentId, String dateOfAppointment, String service, String medicine, String consultationNotes){
        this.appointmentId = appointmentId;
        this.dateOfAppointment = dateOfAppointment;
        this.service = service;
        this.medicine = medicine;
        this.status = false;
        this.consultationNotes = consultationNotes;
    }

    public AppointmentOutcome(int appointmentId, String dateOfAppointment, String service, String medicine, boolean status, String consultationNotes){
        this.appointmentId = appointmentId;
        this.dateOfAppointment = dateOfAppointment;
        this.service = service;
        this.medicine = medicine;
        this.status = status;
        this.consultationNotes = consultationNotes;
    }
    
    public void outcomeRecord(){
        System.out.println(appointmentId);
        System.out.println(dateOfAppointment);
        System.out.println(service);
        System.out.println(medicine);
        System.out.println((status == false) ? "Pending": "Dispensed");
        System.out.println(consultationNotes);
        System.out.println();
    }
    
}   