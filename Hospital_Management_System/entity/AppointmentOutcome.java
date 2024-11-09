package entity;

public class AppointmentOutcome{   
    private int appointmentId; 
    private String dateOfAppointment;
    private String service;
    private String medicine;
    private boolean pstatus;
    private String consultationNotes;

    public AppointmentOutcome(int appointmentId, String dateOfAppointment, String service, String medicine, String consultationNotes){
        this.appointmentId = appointmentId;
        this.dateOfAppointment = dateOfAppointment;
        this.service = service;
        this.medicine = medicine;
        this.pstatus = false;
        this.consultationNotes = consultationNotes;
    }

    public AppointmentOutcome(int appointmentId, String dateOfAppointment, String service, String medicine, boolean status, String consultationNotes){
        this.appointmentId = appointmentId;
        this.dateOfAppointment = dateOfAppointment;
        this.service = service;
        this.medicine = medicine;
        this.pstatus = status;
        this.consultationNotes = consultationNotes;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public String getMedicine() {
        return medicine;
    }

    public boolean isPStatus() {
        return pstatus;
    }
    public void setPStatus(boolean status) {
        this.pstatus=status;
    }

    public String getDateOfAppointment() {
        return dateOfAppointment;
    }

    public String getService() {
        return service;
    }

    public String getConsultationNotes() {
        return consultationNotes;
    }
    
    

    public void printAppointmentOutcome(){
        System.out.printf("%-14d | %-10s | %-12s | %-16s | %-9s | %s%n",
                                          appointmentId,
                                          dateOfAppointment,
                                          service,
                                          medicine,
                                          pstatus ? "Dispensed" : "Pending",
                                          consultationNotes);
    }

    

    
    
}   