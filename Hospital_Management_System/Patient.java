public class Patient extends User {
    private String DOB;
    private String bloodType;
    private String contactInfo;
    private MedicalRecord medicalRecord;
    private AppointmentController appointmentController;

    public Patient(String id, String name, String password, String gender, String DOB, String bloodType, String contactInfo) {
        super(id, name, password, gender);
        this.DOB = DOB;
        this.bloodType = bloodType;
        this.contactInfo = contactInfo;
        this.medicalRecord = new MedicalRecord(this);
        this.appointmentController = new AppointmentController();
    }

    public String getDOB() {
        return DOB;
    }

    public String getBloodType() {
        return bloodType;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String newContactInfo) {
        this.contactInfo = newContactInfo;
        System.out.println("Contact information updated successfully.");
    }

    @Override
    public String getRole() {
        return "Patient";
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public AppointmentController getAppointmentController() {
        return appointmentController;
    }

    public void displayUI() {
        PatientUI patientUI = new PatientUI(this);
        patientUI.printMenu();
    }
}
